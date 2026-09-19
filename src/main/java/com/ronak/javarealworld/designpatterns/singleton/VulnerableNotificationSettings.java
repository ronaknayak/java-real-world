package com.ronak.javarealworld.designpatterns.singleton;

import java.io.Serializable;

/** Deliberately incomplete singleton, used to demonstrate common attacks. */
public final class VulnerableNotificationSettings implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private static final VulnerableNotificationSettings INSTANCE = new VulnerableNotificationSettings();

    private VulnerableNotificationSettings() { }

    public static VulnerableNotificationSettings getInstance() {
        return INSTANCE;
    }

    public String senderAddress() {
        return "notifications@acme.test";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}