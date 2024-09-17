package org.kcpranay.personal;

import java.util.*;

public class PointsManagementSystem {
    class UserPoints {
        private String id;
        private Long points;
        private Long timestamp;
        private String payer;

        public UserPoints(Long points, Long timestamp, String payer) {
            this.id = UUID.randomUUID().toString();
            this.points = points;
            this.timestamp = timestamp;
            this.payer = payer;
        }

        public Long getPoints() {
            return points;
        }

        public String getPayer() {
            return payer;
        }

        public void updatePoints(Long update) {
            this.points += update;
        }

        public String getId() {
            return id;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setPoints(Long points) {
            this.points = points;
        }
    }

    Map<String, PriorityQueue<UserPoints>> payerHeapMap = new HashMap<>();
    PriorityQueue<UserPoints> userPointsHeap = new PriorityQueue<>((a,b)->(int)(a.getTimestamp()-b.getTimestamp()));
    public void addTransaction(Transaction t) {
        String payer = t.getPayer();
        Long amount = t.getAmount();
        if(amount > 0) {
            UserPoints points = new UserPoints(amount, t.getTimestamp(),payer);
            userPointsHeap.add(points);
            payerHeapMap.putIfAbsent(payer, new PriorityQueue<>((a,b)->
                 (int)(a.getTimestamp() -  b.getTimestamp())));
            payerHeapMap.get(payer).add(points);
        } else {
            amount=-amount;
            while(amount > 0) {
                UserPoints pointsToRemove = payerHeapMap.get(payer).poll();
                userPointsHeap.remove(pointsToRemove);
                Long deduction = Math.min(pointsToRemove.getPoints(), amount);
                amount-=deduction;
                if(pointsToRemove.getPoints() - deduction > 0) {
                    UserPoints updatedPoints = new UserPoints(pointsToRemove.getPoints() - deduction,
                            pointsToRemove.getTimestamp(), pointsToRemove.getPayer());
                    payerHeapMap.get(payer).add(updatedPoints);
                    userPointsHeap.add(updatedPoints);
                }
            }
        }
    }

    public void spendPoints(Long amount) {
        while(amount > 0) {
            UserPoints pointsToUse =  userPointsHeap.poll();
            payerHeapMap.get(pointsToUse.getPayer()).remove(pointsToUse);
            Long deduction = Math.min(pointsToUse.getPoints(), amount);
            amount-=deduction;
            if(pointsToUse.getPoints() - deduction > 0) {
                UserPoints updatedPoints = new UserPoints(pointsToUse.getPoints() - deduction,
                        pointsToUse.getTimestamp(), pointsToUse.getPayer());
                payerHeapMap.get(pointsToUse.getPayer()).add(updatedPoints);
                userPointsHeap.add(updatedPoints);
            }
        }
    }

    public String getBalance() {
        Map<String, Long> payerBalance = new HashMap<>();
        payerHeapMap.forEach((k,v) -> {
            Iterator<UserPoints> it = v.iterator();
            Long sum = 0L;
            while(it.hasNext()){
                sum+=it.next().getPoints();
            }
            payerBalance.put(k, sum);
        });
        return payerBalance.toString();
    }


}
