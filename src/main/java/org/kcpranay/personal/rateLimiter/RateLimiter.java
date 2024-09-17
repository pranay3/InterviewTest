package org.kcpranay.personal.rateLimiter;

import java.net.http.HttpRequest;

public interface RateLimiter {

    public boolean shouldAllowRequest(HttpRequest req);
}
