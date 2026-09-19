package com.ronak.javarealworld.designpatterns.factory;

public final class WalletPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentMethod supportedMethod() {
        return PaymentMethod.WALLET;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult("WALLET-" + request.orderNumber(), "AUTHORIZED");
    }
}
