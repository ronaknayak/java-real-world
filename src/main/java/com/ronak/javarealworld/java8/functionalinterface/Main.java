package com.ronak.javarealworld.java8.functionalinterface;

import java.math.BigDecimal;

/** Simple client code using domain-named lambdas. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        ProductValidationRule hasPositivePrice = product -> {
            if (product.price().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ProductValidationException("price must be greater than zero");
            }
        };
        ProductValidationRule hasSku = product -> {
            if (product.sku().isBlank()) {
                throw new ProductValidationException("SKU is required");
            }
        };

        var publicationRules = hasPositivePrice.named("price check")
                .andThen(hasSku.named("SKU check"));
        var validator = new ProductPublicationValidator(publicationRules);

        try {
            validator.validate(new Product("BOOK-17", "Java Handbook", new BigDecimal("29.99")));
            System.out.println("Product is ready to publish");
        } catch (ProductValidationException exception) {
            System.out.println("Product rejected: " + exception.getMessage());
        }
    }
}