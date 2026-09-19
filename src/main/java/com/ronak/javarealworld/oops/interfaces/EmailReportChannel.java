package com.ronak.javarealworld.oops.interfaces;

public final class EmailReportChannel implements ReportDeliveryChannel {
    private final String recipient;

    public EmailReportChannel(String recipient) {
        if (recipient == null || recipient.isBlank()) throw new IllegalArgumentException("recipient must not be blank");
        this.recipient = recipient;
    }

    public String channelName() {
        return "email";
    }

    public DeliveryReceipt deliver(Report report) {
        return new DeliveryReceipt(channelName(), "Emailed '" + report.title() + "' to " + recipient);
    }
}