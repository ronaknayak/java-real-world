package com.ronak.javarealworld.multithreading.deadlock;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {

        BankAccount accountA = new BankAccount("ACC-A", new BigDecimal("1000"));

        BankAccount accountB = new BankAccount("ACC-B", new BigDecimal("1000"));

        BankTransferService transferService = new BankTransferService();

        CountDownLatch startSignal = new CountDownLatch(1);

        Thread transferAtoB = new Thread(() -> {
            await(startSignal);

            transferService.transfer(accountA, accountB, new BigDecimal("100"));
        }, "transfer-A-to-B");

        Thread transferBtoA = new Thread(() -> {
            await(startSignal);

            transferService.transfer(accountB, accountA, new BigDecimal("50"));
        }, "transfer-B-to-A");

        transferAtoB.start();
        transferBtoA.start();

        /*
         * Start both transfers at approximately the same time.
         */
        startSignal.countDown();

        /*
         * WARNING:
         * These threads are intentionally expected to become
         * deadlocked, so don't join() indefinitely.
         */
        transferAtoB.join(3000);
        transferBtoA.join(3000);

        if (transferAtoB.isAlive() || transferBtoA.isAlive()) {
            System.out.println();
            System.out.println("Possible deadlock detected.");
            System.out.println("Thread A alive: " + transferAtoB.isAlive());
            System.out.println("Thread B alive: " + transferBtoA.isAlive());
        }
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread interrupted while waiting to start.", e);
        }
    }
}