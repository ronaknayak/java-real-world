package com.ronak.javarealworld.solid.violations.srp;

import java.math.BigDecimal;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        new InvoiceManager().process("INV-1042", "billing@acme.example", new BigDecimal("12500.00"));
        System.out.println("Violation: one class changes for tax, database, and email-provider changes.");
    }
}
