package com.ronak.javarealworld.designpatterns.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Lazy, thread-safe singleton based on JVM class initialization.
 * readResolve preserves identity during deserialization and cloning is rejected.
 */
public final class ThreadSafeNotificationSettings implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private static boolean constructed;

    private ThreadSafeNotificationSettings() {
        synchronized (ThreadSafeNotificationSettings.class) {
            if (constructed) {
                throw new IllegalStateException("Use getInstance() to obtain notification settings");
            }
            constructed = true;
        }
    }

    private static class Holder {
        private static final ThreadSafeNotificationSettings INSTANCE = new ThreadSafeNotificationSettings();
    }

    public static ThreadSafeNotificationSettings getInstance() {
        return Holder.INSTANCE;
    }

    public String senderAddress() {
        return "notifications@acme.test";
    }

    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("A singleton cannot be cloned");
    }
}