package org.kcpranay.personal.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueueManager {
    private Map<String, Topic> topics;
    private Map<String, List<Subscriber>> subscribersMap;
    private QueueService queueService;

    public QueueManager() {
        this.topics = new HashMap<>();
        this.subscribersMap = new HashMap<>();
        queueService = QueueService.getInstance();
    }

    public void createTopic(String id) {
        Topic newTopic = new Topic(id);
        topics.put(id, newTopic);
        queueService.addQueue(id);
    }

    public Subscriber subscribe(String topic) {
        Subscriber subscriber = new Subscriber(topic);
        subscribersMap.putIfAbsent(topic, new ArrayList<>());
        subscribersMap.get(topic).add(subscriber);
        return subscriber;
    }
}
