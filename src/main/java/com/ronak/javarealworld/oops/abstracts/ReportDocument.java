package com.ronak.javarealworld.oops.abstracts;

import java.util.Objects;

/** A report model shared by all formatter implementations. */
public record ReportDocument(String title, String body) {
    public ReportDocument {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(body, "body must not be null");
        if (title.isBlank()) throw new IllegalArgumentException("title must not be blank");
    }
}