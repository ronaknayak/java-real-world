package com.ronak.javarealworld.oops.abstracts.templatemethodpattern;

import java.util.Objects;

/** Input for a fulfillment workflow. */
public record FulfillmentRequest(String orderId, String customerName, int itemCount) {
    public FulfillmentRequest {
        Objects.requireNonNull(orderId, "orderId must not be null");
        Objects.requireNonNull(customerName, "customerName must not be null");
        if (orderId.isBlank() || customerName.isBlank()) {
            throw new IllegalArgumentException("orderId and customerName must not be blank");
        }
        if (itemCount <= 0) {
            throw new IllegalArgumentException("itemCount must be positive");
        }
    }
}