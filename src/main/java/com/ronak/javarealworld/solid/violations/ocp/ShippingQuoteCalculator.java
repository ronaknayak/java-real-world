package com.ronak.javarealworld.solid.violations.ocp;

import java.math.BigDecimal;

/** Deliberately violates OCP: every pricing rule requires editing this class. */
public final class ShippingQuoteCalculator {
    public BigDecimal calculate(String destinationCountry, boolean express) {
        if ("IN".equalsIgnoreCase(destinationCountry)) {
            return express ? new BigDecimal("250.00") : new BigDecimal("100.00");
        }
        if ("US".equalsIgnoreCase(destinationCountry)) {
            return express ? new BigDecimal("1800.00") : new BigDecimal("900.00");
        }
        return new BigDecimal("1500.00");
    }
}
