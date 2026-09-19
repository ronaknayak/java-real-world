package com.ronak.javarealworld.solid.violations.lsp;

import java.math.BigDecimal;

/** Store credit has no original external payment method, so it cannot honor the parent contract. */
public final class StoreCreditRefundMethod implements RefundMethod {
    @Override
    public String refundToOriginalMethod(BigDecimal amount) {
        throw new UnsupportedOperationException("Store credit must be returned as store credit");
    }
}
