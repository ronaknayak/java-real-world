package com.ronak.javarealworld.multithreading.deadlock;

import java.math.BigDecimal;

public final class BankTransferService {

    public void transfer(BankAccount source, BankAccount destination, BigDecimal amount) {

        /*
         * WARNING:
         * This implementation intentionally demonstrates
         * a deadlock-prone locking strategy.
         */
        synchronized (source) {

            System.out.printf("[%s] Locked source account: %s%n",
                    Thread.currentThread().getName(), source.accountId());

            sleep(500);

            synchronized (destination) {

                System.out.printf("[%s] Locked destination account: %s%n",
                        Thread.currentThread().getName(), destination.accountId());

                source.debit(amount);
                destination.credit(amount);
            }
        }
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Transfer interrupted.", e);
        }
    }
}