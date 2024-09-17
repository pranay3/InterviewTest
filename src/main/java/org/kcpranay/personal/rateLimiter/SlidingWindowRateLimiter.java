package org.kcpranay.personal.rateLimiter;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter{
    Map<String, Queue<Long>> ruleQueues = new HashMap<>();
    List<RateLimitingRule> rules;
    public SlidingWindowRateLimiter(List<RateLimitingRule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean shouldAllowRequest(HttpRequest req) {
        Long now = System.currentTimeMillis();
        for(RateLimitingRule rule: rules) {
            String key = rule.getRuleKey(req);
            if(ruleQueues.containsKey(rule.getRuleKey(req))) {
                Long curWindowStart = now - rule.getWindowSizeinSecs()*1000;
                while(!ruleQueues.get(key).isEmpty() && ruleQueues.get(key).peek()<curWindowStart) {
                    ruleQueues.get(key).poll();
                }
            } else {
                ruleQueues.put(key,new ConcurrentLinkedQueue<>());
            }
            ruleQueues.get(key).add(now);
            if(ruleQueues.get(key).size() > rule.getLimit()) {
                return false;
            }
        }
        return true;
    }
}
