package org.kcpranay.personal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kcpranay.personal.queue.Message;
import org.kcpranay.personal.queue.Publisher;
import org.kcpranay.personal.queue.QueueManager;
import org.kcpranay.personal.queue.Subscriber;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    public static void main(String[] args) throws InterruptedException {
        QueueManager queueManager = new QueueManager();

        queueManager.createTopic("topic1");
        Subscriber subscriber = queueManager.subscribe("topic1");

        Publisher publisher1 = new Publisher();
        Publisher publisher2 = new Publisher();
        Publisher publisher3 = new Publisher();

        Thread s = new Thread(()-> {while(true) {
            String data = subscriber.poll();
            LOGGER.info("Received message: " + data);
        }
        });

        Thread.sleep(5000);
        Thread t1 = new Thread(()->publisher1.publish("topic1", new Message("123", "datahbvh jhvo")));
        Thread t2 = new Thread(()->publisher2.publish("topic1", new Message("245", "eii 834yihfg7hy  jhvo")));
        Thread t3 = new Thread(()->publisher3.publish("topic1", new Message("749", "day347 iguhtahbvh jhvo")));

        s.start();
        t1.start();
        t2.start();
        t3.start();

        s.join();
    }
}
