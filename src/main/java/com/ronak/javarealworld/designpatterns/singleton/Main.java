package com.ronak.javarealworld.designpatterns.singleton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/** Run target for the Singleton pattern example. */
public final class Main {
    private Main() { }

    public static void main(String[] args) throws Exception {
        demonstrateBreaks();
        demonstrateFixes();
        demonstrateThreadSafety();
    }

    private static void demonstrateBreaks() throws Exception {
        var original = VulnerableNotificationSettings.getInstance();
        System.out.println("Vulnerable singleton broken by reflection: "
                + (original != instantiate(VulnerableNotificationSettings.class)));
        System.out.println("Vulnerable singleton broken by serialization: "
                + (original != deserialize(serialize(original))));
        System.out.println("Vulnerable singleton broken by cloning: "
                + (original != cloneInstance(original)));
    }

    private static void demonstrateFixes() throws Exception {
        var settings = ThreadSafeNotificationSettings.getInstance();
        System.out.println("Fixed serialization retains identity: "
                + (settings == deserialize(serialize(settings))));
        System.out.println("Fixed singleton rejects cloning: " + rejectsClone(settings));
        System.out.println("Fixed singleton rejects later reflection: "
                + rejectsReflection(ThreadSafeNotificationSettings.class));
        System.out.println("Enum singleton sender: " + NotificationSettings.INSTANCE.senderAddress());
    }

    private static void demonstrateThreadSafety() throws InterruptedException {
        int callerCount = 64;
        Set<ThreadSafeNotificationSettings> instances = ConcurrentHashMap.newKeySet();
        CountDownLatch ready = new CountDownLatch(callerCount);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch complete = new CountDownLatch(callerCount);

        for (int i = 0; i < callerCount; i++) {
            Thread thread = new Thread(() -> {
                ready.countDown();
                try {
                    start.await();
                    instances.add(ThreadSafeNotificationSettings.getInstance());
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                } finally {
                    complete.countDown();
                }
            });
            thread.start();
        }

        ready.await();
        start.countDown();
        complete.await();
        System.out.println("Distinct instances across " + callerCount + " callers: " + instances.size());
    }

    private static <T> T instantiate(Class<T> type) throws Exception {
        Constructor<T> constructor = type.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    private static boolean rejectsReflection(Class<?> type) throws Exception {
        try {
            instantiate(type);
            return false;
        } catch (InvocationTargetException exception) {
            return exception.getCause() instanceof IllegalStateException;
        }
    }

    private static byte[] serialize(Object value) throws Exception {
        var bytes = new ByteArrayOutputStream();
        try (var output = new ObjectOutputStream(bytes)) {
            output.writeObject(value);
        }
        return bytes.toByteArray();
    }

    private static Object deserialize(byte[] bytes) throws Exception {
        try (var input = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return input.readObject();
        }
    }

    private static Object cloneInstance(Object value) throws Exception {
        Method clone = value.getClass().getDeclaredMethod("clone");
        clone.setAccessible(true);
        try {
            return clone.invoke(value);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof CloneNotSupportedException cloneException) {
                throw cloneException;
            }
            throw exception;
        }
    }

    private static boolean rejectsClone(Object value) throws Exception {
        try {
            cloneInstance(value);
            return false;
        } catch (CloneNotSupportedException exception) {
            return true;
        }
    }
}