package org.kcpranay.personal.rateLimiter;


import java.net.http.HttpRequest;
import java.util.*;

public class FixedWindowRateLimiter implements RateLimiter{

    Map<String, Window> ruleWindows = new HashMap<>();
    List<RateLimitingRule> rules;
    public FixedWindowRateLimiter(List<RateLimitingRule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean shouldAllowRequest(HttpRequest req) {
        Long now = System.currentTimeMillis();
        for(RateLimitingRule rule: rules) {
            String key = rule.getRuleKey(req);
            if(ruleWindows.containsKey(rule.getRuleKey(req))) {
                if(ruleWindows.get(key).getEndTime() < now){
                    ruleWindows.put(key, new Window(rule.getWindowSizeinSecs()));
                } else {
                    ruleWindows.get(key).setReqCount(1+ ruleWindows.get(key).getReqCount());
                }
            } else {
                ruleWindows.put(key,new Window(rule.getWindowSizeinSecs()));
            }
            if(ruleWindows.get(key).getReqCount() > rule.getLimit()) {
                return false;
            }
        }
        return true;
    }
}
