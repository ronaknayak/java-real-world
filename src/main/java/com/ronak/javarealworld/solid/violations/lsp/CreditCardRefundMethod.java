package com.ronak.javarealworld.solid.violations.lsp;

import java.math.BigDecimal;

public final class CreditCardRefundMethod implements RefundMethod {
    @Override
    public String refundToOriginalMethod(BigDecimal amount) {
        return "Refunded " + amount + " to the credit card";
    }
}
