package com.ronak.javarealworld.designpatterns.factory;

public interface PaymentProcessor {
    PaymentMethod supportedMethod();

    PaymentResult process(PaymentRequest request);
}
