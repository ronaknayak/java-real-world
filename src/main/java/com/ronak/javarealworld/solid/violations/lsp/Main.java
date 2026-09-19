package com.ronak.javarealworld.solid.violations.lsp;

import java.math.BigDecimal;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        RefundMethod refundMethod = new StoreCreditRefundMethod();
        try {
            System.out.println(refundMethod.refundToOriginalMethod(new BigDecimal("499.00")));
        } catch (UnsupportedOperationException exception) {
            System.out.println("Violation: " + exception.getMessage());
        }
    }
}
