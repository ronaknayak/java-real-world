package com.ronak.javarealworld.solid.ocp;

import java.math.BigDecimal;
import java.util.List;

public final class ShippingQuoteService {
    private final List<ShippingRatePolicy> ratePolicies;

    public ShippingQuoteService(List<ShippingRatePolicy> ratePolicies) {
        this.ratePolicies = List.copyOf(ratePolicies);
    }

    public BigDecimal quoteFor(Shipment shipment) {
        return ratePolicies.stream()
                .filter(policy -> policy.supports(shipment))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No rate policy for shipment"))
                .calculate(shipment);
    }
}
