package com.ronak.javarealworld.designpatterns.factory;

import java.math.BigDecimal;
import java.util.List;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var factory = new PaymentProcessorFactory(List.of(
                new CardPaymentProcessor(),
                new BankTransferPaymentProcessor(),
                new WalletPaymentProcessor()));
        var checkoutService = new CheckoutService(factory);
        var result = checkoutService.checkout(
                new PaymentRequest("ORD-4821", new BigDecimal("2499.00"), PaymentMethod.BANK_TRANSFER));
        System.out.println(result);
    }
}
