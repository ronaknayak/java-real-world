package com.ronak.javarealworld.java8.functionalinterface;

import java.util.Objects;

/** Engine that applies a composed product-validation rule. */
public final class ProductPublicationValidator {
    private final ProductValidationRule rule;

    public ProductPublicationValidator(ProductValidationRule rule) {
        this.rule = Objects.requireNonNull(rule, "rule must not be null");
    }

    public void validate(Product product) throws ProductValidationException {
        Objects.requireNonNull(product, "product must not be null");
        rule.validate(product);
    }
}