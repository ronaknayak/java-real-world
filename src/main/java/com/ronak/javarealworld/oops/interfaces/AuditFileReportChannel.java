package com.ronak.javarealworld.oops.interfaces;

public final class AuditFileReportChannel implements ReportDeliveryChannel {
    private final String archiveName;

    public AuditFileReportChannel(String archiveName) {
        if (archiveName == null || archiveName.isBlank())
            throw new IllegalArgumentException("archiveName must not be blank");
        this.archiveName = archiveName;
    }

    public String channelName() {
        return "audit-file";
    }

    public DeliveryReceipt deliver(Report report) {
        return new DeliveryReceipt(channelName(), "Archived '" + report.title() + "' in " + archiveName);
    }
}