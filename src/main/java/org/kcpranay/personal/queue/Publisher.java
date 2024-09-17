package org.kcpranay.personal.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class Publisher {
    private String publisherId;
    private QueueService queueService;

    private static final Logger LOGGER = LogManager.getLogger(Publisher.class);
    public Publisher() {
        this.publisherId = UUID.randomUUID().toString();
        this.queueService = QueueService.getInstance();
    }

    public void publish(String topic, Message message){
        try {
            this.queueService.putMessage(topic, message);
            LOGGER.info("Publishing message to: " + topic);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
