package org.kcpranay.personal.rateLimiter;

import java.net.http.HttpRequest;

public abstract class RateLimitingRule {

    private Integer limit;

    private Integer windowSizeinSecs;

    public RateLimitingRule(Integer limit, Integer windowSizeinSecs) {
        this.limit = limit;
        this.windowSizeinSecs = windowSizeinSecs;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getWindowSizeinSecs() {
        return windowSizeinSecs;
    }

    abstract String getRuleKey(HttpRequest req);
}
