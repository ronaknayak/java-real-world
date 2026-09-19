package com.ronak.javarealworld.oops.interfaces;
import java.util.Objects;
public record Report(String title, String body) {
    public Report {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(body, "body must not be null");
        if (title.isBlank()) throw new IllegalArgumentException("title must not be blank");
    }
}