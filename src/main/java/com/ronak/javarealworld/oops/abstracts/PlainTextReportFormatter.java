package com.ronak.javarealworld.oops.abstracts;

/** Renders the report body as plain text while sharing the base workflow. */
public final class PlainTextReportFormatter extends AbstractReportFormatter {
    public PlainTextReportFormatter() {
        super("Plain text");
    }

    @Override
    protected String renderBody(ReportDocument report) {
        return report.body() + "\n";
    }
}