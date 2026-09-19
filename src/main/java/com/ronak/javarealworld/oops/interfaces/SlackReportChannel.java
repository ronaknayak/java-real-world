package com.ronak.javarealworld.oops.interfaces;

public final class SlackReportChannel implements ReportDeliveryChannel {
    private final String channel;

    public SlackReportChannel(String channel) {
        if (channel == null || channel.isBlank()) throw new IllegalArgumentException("channel must not be blank");
        this.channel = channel;
    }

    public String channelName() {
        return "slack";
    }

    public DeliveryReceipt deliver(Report report) {
        return new DeliveryReceipt(channelName(), "Posted '" + report.title() + "' to #" + channel);
    }
}