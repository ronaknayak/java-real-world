package com.ronak.javarealworld.multithreading.staticsynchronized;

/**
 * Demonstrates that static synchronized methods lock the class, not an instance.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        SettlementBatchSequence firstWorkerService = new SettlementBatchSequence();
        SettlementBatchSequence secondWorkerService = new SettlementBatchSequence();

        Thread firstWorker = new Thread(
                () -> allocateBatch(firstWorkerService, "settlement-worker-1"),
                "settlement-worker-1"
        );
        Thread secondWorker = new Thread(
                () -> allocateBatch(secondWorkerService, "settlement-worker-2"),
                "settlement-worker-2"
        );

        firstWorker.start();
        secondWorker.start();

        firstWorker.join();
        secondWorker.join();
    }

    private static void allocateBatch(SettlementBatchSequence sequence, String workerName) {
        long batchNumber = sequence.nextBatchNumber();
        System.out.printf("%s allocated settlement batch %d%n", workerName, batchNumber);
    }
}
