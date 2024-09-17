package org.kcpranay.personal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LoanManagementSystem {

    Map<String, Set<String>> merchantLoanMap = new TreeMap<>();
    Map<String, Long> loans = new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger(LoanManagementSystem.class);
    public void process() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Start entering operations. Enter \"/\" to finsih input.");
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("/")){
                break;
            }

            String operation = input.split(":")[0];
            String[] params = input.split(":")[1].strip().split(",");

            switch (operation) {
                case Operation.CREATE_LOAN:
                    createLoan(params[0], params[1], params[2]);
                    break;
                case Operation.PAY_LOAN:
                    payLoan(params[0], params[1], params[2]);
                    break;
                case Operation.INCREASE_LOAN:
                    increaseLoan(params[0], params[1], params[2]);
                    break;
                case Operation.TRANSACTION_PROCESSED:
                    processTransaction(params[0], params[1], params[2], params[3]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Operation encountered.");
            }
        }

        printLoanBalances();
    }

    public void createLoan(String merchantId, String id, String amount) {
        Long amountValue = Long.parseLong(amount);
        String loanId = merchantId + "-" + id;
        loans.put(loanId, amountValue);
        merchantLoanMap.putIfAbsent(merchantId, new HashSet<>());
        merchantLoanMap.get(merchantId).add(loanId);
        printLoanBalances();
    }

    public void payLoan(String merchantId, String id, String amount) {
        Long repayAmount = Long.parseLong(amount);
        String loanId = merchantId + "-" + id;
        Long loanAmount = loans.get(loanId);
        loanAmount = loanAmount - repayAmount;
        if(loanAmount > 0) {
            loans.put(loanId, loanAmount);
        } else {
            loans.remove((loanId));
            merchantLoanMap.get(merchantId).remove(loanId);
        }
        printLoanBalances();
    }

    public void increaseLoan(String merchantId, String id, String amount) {
        Long increasedValue = Long.parseLong(amount);
        String loanId = merchantId + "-" + id;
        loans.put(loanId,loans.get(loanId)+increasedValue);
        printLoanBalances();
    }

    public void processTransaction(String merchantId, String id, String amount, String repayment_percentage) {
        String loanId = merchantId + "-" + id;
        Long repaymentAmount = (Long.parseLong(repayment_percentage) * Long.parseLong(amount))/100;
        Long loanAmount = loans.get(loanId);
        loanAmount = loanAmount - Math.min(repaymentAmount, loanAmount);
        if(loanAmount > 0) {
            loans.put(loanId, loanAmount);
        } else {
            loans.remove((loanId));
            merchantLoanMap.get(merchantId).remove(loanId);
        }
        printLoanBalances();
    }

    public  void printLoanBalances() {
        merchantLoanMap.forEach((k,v)-> {
            Long pendingLoan = 0L;
            for (String loanId : v) {
                Long amount = loans.get(loanId);
                pendingLoan += amount;
            }
            if(pendingLoan > 0) {
                LOGGER.info(k+", "+pendingLoan);
            }
        });
    }
}
