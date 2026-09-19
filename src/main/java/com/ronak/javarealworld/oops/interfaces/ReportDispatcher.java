package com.ronak.javarealworld.oops.interfaces;

import java.util.List;
import java.util.Objects;

public final class ReportDispatcher {
    private final List<ReportDeliveryChannel> channels;

    public ReportDispatcher(List<ReportDeliveryChannel> channels) {
        Objects.requireNonNull(channels, "channels must not be null");
        if (channels.isEmpty()) throw new IllegalArgumentException("at least one channel is required");
        this.channels = List.copyOf(channels);
    }

    public List<DeliveryReceipt> dispatch(Report report) {
        Objects.requireNonNull(report, "report must not be null");
        return channels.stream().map(channel -> channel.deliver(report)).toList();
    }
}