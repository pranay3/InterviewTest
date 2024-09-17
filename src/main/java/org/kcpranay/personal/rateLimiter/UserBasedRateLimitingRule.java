package org.kcpranay.personal.rateLimiter;

import java.net.http.HttpRequest;

public class UserBasedRateLimitingRule extends RateLimitingRule{


    public UserBasedRateLimitingRule(Integer limit, Integer windowSizeinSecs) {
        super(limit, windowSizeinSecs);
    }

    @Override
    public String getRuleKey(HttpRequest req) {
        return req.headers().map().get("UserId").get(0);
    }
}
