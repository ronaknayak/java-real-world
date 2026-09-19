package com.ronak.javarealworld.java8.defaultmethod;

import java.util.ArrayList;
import java.util.List;

/** Small audit dependency shared by the default workflow. */
public final class AuditTrail {
    private final List<String> events = new ArrayList<>();

    public void record(String notificationId, String channel) {
        events.add(notificationId + " delivered via " + channel);
    }

    public List<String> events() {
        return List.copyOf(events);
    }
}