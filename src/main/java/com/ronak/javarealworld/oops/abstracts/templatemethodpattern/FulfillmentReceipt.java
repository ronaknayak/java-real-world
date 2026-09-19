package com.ronak.javarealworld.oops.abstracts.templatemethodpattern;

import java.util.List;

/** Outcome of a completed fulfillment workflow. */
public record FulfillmentReceipt(String orderId, String serviceLevel, List<String> steps) {
    public FulfillmentReceipt {
        steps = List.copyOf(steps);
    }
}