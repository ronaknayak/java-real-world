package com.ronak.javarealworld.java8.functionalinterface;

import java.util.Objects;

/** Domain-specific capability for validating a product before publication. */
@FunctionalInterface
public interface ProductValidationRule {
    void validate(Product product) throws ProductValidationException;

    /** Run this rule and then the next rule when both checks pass. */
    default ProductValidationRule andThen(ProductValidationRule next) {
        Objects.requireNonNull(next, "next rule must not be null");
        return product -> {
            validate(product);
            next.validate(product);
        };
    }

    /** Add a useful business name to a validation failure. */
    default ProductValidationRule named(String ruleName) {
        Objects.requireNonNull(ruleName, "ruleName must not be null");
        if (ruleName.isBlank()) throw new IllegalArgumentException("ruleName must not be blank");
        return product -> {
            try {
                validate(product);
            } catch (ProductValidationException exception) {
                throw new ProductValidationException(ruleName + ": " + exception.getMessage(), exception);
            }
        };
    }
}