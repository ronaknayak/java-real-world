package com.ronak.javarealworld.java8.functionalinterface;

/** Checked failure raised when a product cannot be published. */
public class ProductValidationException extends Exception {
    public ProductValidationException(String message) {
        super(message);
    }

    public ProductValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}