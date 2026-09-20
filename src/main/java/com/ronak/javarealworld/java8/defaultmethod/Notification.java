package com.ronak.javarealworld.java8.defaultmethod;

import java.util.Objects;

/** Message delivered through a notification provider. */
public record Notification(String id, String recipient, String message) {
    public Notification {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(recipient, "recipient must not be null");
        Objects.requireNonNull(message, "message must not be null");
        if (id.isBlank() || recipient.isBlank() || message.isBlank()) {
            throw new IllegalArgumentException("notification fields must not be blank");
        }
    }
}