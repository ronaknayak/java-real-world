package com.ronak.javarealworld.designpatterns.factory;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class PaymentProcessorFactory {
    private final Map<PaymentMethod, PaymentProcessor> processors;

    public PaymentProcessorFactory(List<PaymentProcessor> processors) {
        this.processors = new EnumMap<>(PaymentMethod.class);
        processors.forEach(processor -> {
            PaymentProcessor previous = this.processors.put(processor.supportedMethod(), processor);
            if (previous != null) {
                throw new IllegalArgumentException("Duplicate processor for " + processor.supportedMethod());
            }
        });
    }

    public PaymentProcessor createFor(PaymentMethod paymentMethod) {
        PaymentProcessor processor = processors.get(paymentMethod);
        if (processor == null) {
            throw new IllegalArgumentException("No payment processor configured for " + paymentMethod);
        }
        return processor;
    }
}
