package com.ronak.javarealworld.oops.abstracts;

import java.util.List;

/** Run target for the abstract-class example. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var report = new ReportDocument("Daily fulfillment summary", "42 shipments completed; 0 exceptions.");
        List<AbstractReportFormatter> formatters = List.of(
                new HtmlReportFormatter(),
                new PlainTextReportFormatter());

        formatters.stream()
                .map(formatter -> formatter.format(report))
                .forEach(System.out::println);
    }
}