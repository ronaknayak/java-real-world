package com.ronak.javarealworld.designpatterns.factory;

public final class BankTransferPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentMethod supportedMethod() {
        return PaymentMethod.BANK_TRANSFER;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult("BANK-" + request.orderNumber(), "PENDING_SETTLEMENT");
    }
}
