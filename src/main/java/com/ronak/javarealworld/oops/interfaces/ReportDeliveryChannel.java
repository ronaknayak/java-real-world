package com.ronak.javarealworld.oops.interfaces;
public interface ReportDeliveryChannel {
    String channelName();
    DeliveryReceipt deliver(Report report);
}