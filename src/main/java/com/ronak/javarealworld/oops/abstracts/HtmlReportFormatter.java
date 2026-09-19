package com.ronak.javarealworld.oops.abstracts;

/** Renders the report body as HTML while reusing base formatter state and helpers. */
public final class HtmlReportFormatter extends AbstractReportFormatter {
    public HtmlReportFormatter() {
        super("HTML");
    }

    @Override
    protected String renderBody(ReportDocument report) {
        return "<h1>" + escapeHtml(report.title()) + "</h1><p>"
                + escapeHtml(report.body()) + "</p>\n";
    }
}