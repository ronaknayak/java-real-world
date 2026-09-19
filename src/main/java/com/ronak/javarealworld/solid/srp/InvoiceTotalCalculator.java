package com.ronak.javarealworld.solid.srp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class InvoiceTotalCalculator {
    private static final BigDecimal GST_RATE = new BigDecimal("0.18");

    public BigDecimal calculate(Invoice invoice) {
        return invoice.subtotal()
                .multiply(BigDecimal.ONE.add(GST_RATE))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
