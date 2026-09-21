package com.ronak.javarealworld.multithreading.lockapi.stampedlock;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.locks.StampedLock;

/**
 * Holds the latest delivery location for a read-heavy tracking endpoint.
 */
public final class DeliveryLocationTracker {

    private final StampedLock locationLock = new StampedLock();
    private DeliveryLocation latestLocation;

    public DeliveryLocationTracker(DeliveryLocation initialLocation) {
        this.latestLocation = Objects.requireNonNull(initialLocation, "Initial location cannot be null.");
    }

    /**
     * Uses an optimistic read for the common case. If a writer changed the
     * location while it was read, it falls back to a regular read lock.
     */
    public DeliveryLocation latestLocation() {
        long stamp = locationLock.tryOptimisticRead();
        DeliveryLocation location = latestLocation;
        if (!locationLock.validate(stamp)) {
            stamp = locationLock.readLock();
            try {
                location = latestLocation;
            } finally {
                locationLock.unlockRead(stamp);
            }
        }
        return location;
    }

    /**
     * Publishes a new driver position under the exclusive write lock.
     */
    public void recordLocation(DeliveryLocation location) {
        Objects.requireNonNull(location, "Location cannot be null.");
        long stamp = locationLock.writeLock();
        try {
            latestLocation = location;
        } finally {
            locationLock.unlockWrite(stamp);
        }
    }

    /**
     * Updates an old location only when the new report is newer. It attempts a
     * read-to-write conversion first, then safely falls back to a write lock.
     */
    public boolean refreshIfStale(DeliveryLocation replacement, Duration maximumAge) {
        Objects.requireNonNull(replacement, "Replacement location cannot be null.");
        validateMaximumAge(maximumAge);

        long stamp = locationLock.readLock();
        boolean writeLocked = false;
        try {
            if (!canReplace(replacement, maximumAge)) {
                return false;
            }

            long writeStamp = locationLock.tryConvertToWriteLock(stamp);
            if (writeStamp == 0L) {
                locationLock.unlockRead(stamp);
                stamp = locationLock.writeLock();
            } else {
                stamp = writeStamp;
            }
            writeLocked = true;

            if (!canReplace(replacement, maximumAge)) {
                return false;
            }
            latestLocation = replacement;
            return true;
        } finally {
            if (writeLocked) {
                locationLock.unlockWrite(stamp);
            } else {
                locationLock.unlockRead(stamp);
            }
        }
    }

    private boolean canReplace(DeliveryLocation replacement, Duration maximumAge) {
        Instant staleBefore = Instant.now().minus(maximumAge);
        return latestLocation.reportedAt().isBefore(staleBefore)
                && replacement.reportedAt().isAfter(latestLocation.reportedAt());
    }

    private static void validateMaximumAge(Duration maximumAge) {
        Objects.requireNonNull(maximumAge, "Maximum age cannot be null.");
        if (maximumAge.isNegative() || maximumAge.isZero()) {
            throw new IllegalArgumentException("Maximum age must be positive.");
        }
    }
}
