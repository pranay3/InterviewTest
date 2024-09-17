package org.kcpranay.personal.queue;

import java.util.HashMap;
import java.util.Map;

public class Topic {

    private final String topicId;

    private final Map<String,Subscriber> subscribers;

    public Topic(String topicId) {
        this.topicId = topicId;
        subscribers = new HashMap<>();
    }

    public boolean addSubscriber(Subscriber s) {
        subscribers.put(s.getId(), s);
        return true;
    }

    public boolean removeSubscriber(Subscriber s) {
        if(subscribers.containsKey(s.getId())) {
            subscribers.remove(s.getId());
            return true;
        }
        return false;
    }

    public Subscriber getSubsriber(String subscriberId) {
        return subscribers.get(subscriberId);
    }
}
