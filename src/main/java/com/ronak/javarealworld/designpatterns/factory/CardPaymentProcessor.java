package com.ronak.javarealworld.designpatterns.factory;

public final class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentMethod supportedMethod() {
        return PaymentMethod.CARD;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult("CARD-" + request.orderNumber(), "AUTHORIZED");
    }
}
