package org.kcpranay.personal.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueService {
    private Map<String, BlockingQueue<String>> queues;
    private static QueueService queueServiceSingleton;
    private QueueService() {
        this.queues = new HashMap<>();
    }

    public static QueueService getInstance() {
        if(queueServiceSingleton == null) {
            synchronized (QueueService.class) {
                if(queueServiceSingleton == null) {
                    return queueServiceSingleton = new QueueService();
                }
            }
        }
        return queueServiceSingleton;
    }

    public void addQueue(String topicId) {
        queues.put(topicId, new LinkedBlockingDeque<String>());
    }

    public String readMessage(String topicId) throws InterruptedException {
        return queues.get(topicId).take();
    }

    public void putMessage(String topicId, Message msg) throws InterruptedException {
        queues.get(topicId).put(msg.toString());
    }
}
