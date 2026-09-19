package com.ronak.javarealworld.oops.interfaces;

import java.util.List;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        var dispatcher = new ReportDispatcher(List.of(
                new EmailReportChannel("ops@example.test"),
                new SlackReportChannel("operations"),
                new AuditFileReportChannel("monthly-audit.log")));
        var report = new Report("Daily fulfillment summary", "42 shipments completed; 0 exceptions.");
        dispatcher.dispatch(report).forEach(System.out::println);
    }
}