package org.kcpranay.personal.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class Subscriber {
    private String id;

    private String topicId;
    private QueueService queueService;

    private static final Logger LOGGER = LogManager.getLogger(Subscriber.class);
    protected Subscriber(String topicId) {
        this.id = UUID.randomUUID().toString();
        this.topicId = topicId;
        this.queueService = QueueService.getInstance();
    }

    public String getId() {
        return id;
    }

    public String getTopicId() {
        return topicId;
    }

    public String poll() {
        String msg = "";
        try {
            msg = queueService.readMessage(topicId);
            LOGGER.info("Successfully read message from topic: " + topicId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return msg;
    }
}
