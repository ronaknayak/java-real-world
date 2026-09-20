package com.ronak.javarealworld.java8.staticmethod;

import java.math.BigDecimal;

/** Run target for static methods declared inside an interface. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        PaymentMethod method = PaymentProcessor.parseMethod("card");
        PaymentRequest request = new PaymentRequest("ORD-4821", new BigDecimal("2499.00"));

        PaymentProcessor processor = method == PaymentMethod.CARD
                ? new CardPaymentProcessor()
                : new BankTransferPaymentProcessor();
        System.out.println(processor.process(request));
        System.out.println("Parsed by interface static method: " + method);
    }
}