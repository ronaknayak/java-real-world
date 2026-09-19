package com.ronak.javarealworld.java8.staticmethod;

/** Concrete implementation uses the interface static validation explicitly. */
public final class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentReceipt process(PaymentRequest request) {
        PaymentProcessor.validateRequest(request);
        return new PaymentReceipt(request.reference(), PaymentMethod.CARD, "Card payment authorized");
    }
}