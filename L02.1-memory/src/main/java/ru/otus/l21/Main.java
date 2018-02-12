package ru.otus.l21;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.lang.instrument.Instrumentation;

/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 * jconsole, connect to pid
 */
@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    public static void main(String... args) throws InterruptedException, InstantiationException, IllegalAccessException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
        MemUsage(new String(""));
        MemUsage(new String("abcdef"));
        MemUsage(new String(new char[0]));
        MemUsage(new HashMap<String,String>());
        MemUsage(new MyClass());
    }

    public static class MyClass implements Serializable {
        private int i = 0;
        private long l = 1;
    }

    private static <T extends Serializable> void MemUsage(T obj) throws InterruptedException, IllegalAccessException, InstantiationException {
        int size = 200_000;

        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem =
                runtime.totalMemory()
                        - runtime.freeMemory();


        Object[] array = new Object[size];


        for (int i = 0; i < size; i++) {
            array[i] = SerializationUtils.clone(obj);
        }

        System.gc();
        Thread.sleep(10);

        System.out.println("Created " + size + " objects.");
        long mem2 = runtime.totalMemory()
                        - runtime.freeMemory();

        System.out.println("Estimated object size is " + (mem2 - mem)/size);

    }
}

