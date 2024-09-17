package org.kcpranay.personal;

public class Transaction {
    String payer;
    Long amount;
    Long timestamp;

    public Transaction(String payer, Long amount, Long timestamp) {
        this.payer = payer;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getPayer() {
        return payer;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
