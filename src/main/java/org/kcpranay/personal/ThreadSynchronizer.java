package org.kcpranay.personal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadSynchronizer {

    static class Worker implements Runnable {
        private final CountDownLatch starterLatch;
        private final CountDownLatch finisherLatch;
        private final CountDownLatch processorLatch;

        public Worker(CountDownLatch starterLatch, CountDownLatch finisherLatch, CountDownLatch processorLatch) {
            this.starterLatch = starterLatch;
            this.finisherLatch = finisherLatch;
            this.processorLatch = processorLatch;
        }

        @Override
        public void run() {
            try {
                String threadName = Thread.currentThread().getName();
                starterLatch.countDown();
                System.out.println("[" + threadName + "] Waiting for other threads to start.");
                processorLatch.await();
                System.out.println("[" + threadName + "] Started...");
                Thread.sleep(1000 * (ThreadLocalRandom.current().nextInt(5) + 1));
                System.out.println("[" + threadName + "] Finished the task.");
                finisherLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static final CountDownLatch starterLatch = new CountDownLatch(3);
    private static final CountDownLatch processorLatch = new CountDownLatch(1);
    private static final CountDownLatch finisherLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new Worker(starterLatch, finisherLatch, processorLatch));
            Thread t2 = new Thread(new Worker(starterLatch, finisherLatch, processorLatch));
            Thread t3 = new Thread(new Worker(starterLatch, finisherLatch, processorLatch));
            t1.start();
            t2.start();
            t3.start();
            starterLatch.await();
            processorLatch.countDown();
            System.out.println(Thread.currentThread().getName() + ": Waiting for threads to finish work...");
            finisherLatch.await();
            System.out.println(Thread.currentThread().getName() + ": All threads finished work. Exiting...");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": Interrupted. Exiting...");
        }
    }
}
