package com.ronak.javarealworld.multithreading.lockapi.stampedlock;

import java.time.Instant;
import java.util.Objects;

/**
 * Immutable position data reported by a delivery driver application.
 */
public record DeliveryLocation(double latitude, double longitude, Instant reportedAt) {

    public DeliveryLocation {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90.");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180.");
        }
        Objects.requireNonNull(reportedAt, "Reported time cannot be null.");
    }
}
