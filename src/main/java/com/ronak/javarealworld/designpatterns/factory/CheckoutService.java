package com.ronak.javarealworld.designpatterns.factory;

public final class CheckoutService {
    private final PaymentProcessorFactory paymentProcessorFactory;

    public CheckoutService(PaymentProcessorFactory paymentProcessorFactory) {
        this.paymentProcessorFactory = paymentProcessorFactory;
    }

    public PaymentResult checkout(PaymentRequest request) {
        return paymentProcessorFactory.createFor(request.paymentMethod()).process(request);
    }
}
