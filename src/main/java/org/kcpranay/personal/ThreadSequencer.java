package org.kcpranay.personal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadSequencer {
    static class Worker implements Runnable {
        private final CyclicBarrier barrier;

        private final CountDownLatch finisherLatch;
        private final Integer first;
        public Worker(int first, CyclicBarrier barrier, CountDownLatch finisherLatch) {
            this.barrier = barrier;
            this.first = first;
            this.finisherLatch = finisherLatch;
        }

        @Override
        public void run() {
            try {
                String threadName = Thread.currentThread().getName();
                for(int start = first; start<210;start+=30) {
                    for(int next = 1;next<=10;next++) {
                        System.out.println("["+threadName+"]: " + (start+next));
                    }
                    System.out.println("["+threadName+"]: Waiting for other threads to finish counting");
                    barrier.await();
                    System.out.println("["+threadName+"]: Counting again...");
                }
                finisherLatch.countDown();
            } catch (BrokenBarrierException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()-> System.out.println("Barrier broken..."));
    private static final CountDownLatch finisherLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new Worker(0, cyclicBarrier, finisherLatch));
            Thread t2 = new Thread(new Worker(10, cyclicBarrier, finisherLatch));
            Thread t3 = new Thread(new Worker(20, cyclicBarrier, finisherLatch));
            t1.start();
            t2.start();
            t3.start();
            finisherLatch.await();
            System.out.println(Thread.currentThread().getName() + ": All threads finished work. Exiting...");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": Interrupted. Exiting...");
        }
    }
}
