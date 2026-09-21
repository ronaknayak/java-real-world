package com.ronak.javarealworld.multithreading.staticsynchronized;

/**
 * Allocates unique settlement-batch numbers for every instance of this service.
 */
public final class SettlementBatchSequence {

    private static long nextBatchNumber = 101;

    /**
     * Locks SettlementBatchSequence.class. Therefore, calls made through
     * different instances still share the same lock and cannot allocate the
     * same batch number.
     */
    public static synchronized long nextBatchNumber() {
        return nextBatchNumber++;
    }
}
