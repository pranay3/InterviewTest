package org.kcpranay.personal.rateLimiter;

public class Window {
    private Long starTime;
    private Long reqCount;

    private Long endTime;

    public Window(Integer windowSize) {
        this.starTime = System.currentTimeMillis();
        this.reqCount = 1L;
        this.endTime = this.starTime + (windowSize * 1000);
    }

    public Long getStarTime() {
        return starTime;
    }

    public void setStarTime(Long starTime) {
        this.starTime = starTime;
    }

    public Long getReqCount() {
        return reqCount;
    }

    public void setReqCount(Long reqCount) {
        this.reqCount = reqCount;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
