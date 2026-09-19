package com.ronak.javarealworld.java8.staticmethod;

/** Another implementation that shares helpers through the interface name. */
public final class BankTransferPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentReceipt process(PaymentRequest request) {
        PaymentProcessor.validateRequest(request);
        return new PaymentReceipt(request.reference(), PaymentMethod.BANK_TRANSFER, "Bank transfer initiated");
    }
}