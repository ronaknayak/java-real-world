package com.ronak.javarealworld.oops.abstracts;

import java.time.Instant;
import java.util.Objects;

/**
 * Common base type for report formatters. It owns shared state, construction,
 * the formatting workflow, and protected helpers; subclasses provide only body rendering.
 */
public abstract class AbstractReportFormatter {
    private final String formatName;
    private final Instant createdAt;

    protected AbstractReportFormatter(String formatName) {
        this.formatName = Objects.requireNonNull(formatName, "formatName must not be null");
        this.createdAt = Instant.now();
    }

    public final String format(ReportDocument report) {
        Objects.requireNonNull(report, "report must not be null");
        return header(report) + renderBody(report) + footer();
    }

    protected abstract String renderBody(ReportDocument report);

    protected final String formatName() {
        return formatName;
    }

    protected final String createdAtText() {
        return createdAt.toString();
    }

    protected final String escapeHtml(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private String header(ReportDocument report) {
        return "[" + formatName + "] " + report.title() + "\nGenerated: " + createdAtText() + "\n";
    }

    private String footer() {
        return "\n-- End of " + formatName + " report --\n";
    }
}