package com.ronak.javarealworld.designpatterns.singleton;

/**
 * Preferred singleton form when an enum fits the API. The JVM provides
 * thread-safety and protects its single constant from reflection and serialization attacks.
 */
public enum NotificationSettings {
    INSTANCE;

    public String senderAddress() {
        return "notifications@acme.test";
    }
}