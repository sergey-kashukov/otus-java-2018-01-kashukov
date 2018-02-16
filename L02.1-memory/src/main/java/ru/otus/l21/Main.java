package ru.otus.l21;

import java.lang.management.ManagementFactory;
import java.util.HashMap;

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
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 20_000_000;

        memUsageEmptyObject(size);
        memUsageEmptyString(size);
        memUsageNonEmptyString(size);
        memUsageEmptyStringChar(size);
        memUsageEmptyHashMap(size);
        memUsageMyClass(size);

    }

    private static class MyClass {
        private int i = 0;
        private long l = 1;
    }

    private static void memUsageEmptyObject(int arraysize) throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(mem);

        Object[] array = new Object[arraysize];

        System.out.println("New array of size: " + array.length + " created");
        for (int i = 0; i < arraysize; i++) {
            array[i] = new Object();
        }

        long mem2 = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Object has estimated size of " + (mem2 - mem)/arraysize);
        System.out.println();

        Thread.sleep(1000); //wait for 1 sec
    }

    private static void memUsageEmptyString(int arraysize) throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(mem);

        Object[] array = new Object[arraysize];



        System.out.println("New array of size: " + array.length + " created");
        for (int i = 0; i < arraysize; i++) {
            array[i] = new String("");
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Empty string with pool usage has estimated size of " + (mem2 - mem)/arraysize);
        System.out.println();

        Thread.sleep(1000); //wait for 1 sec
    }

    private static void memUsageNonEmptyString(int arraysize) throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(mem);

        Object[] array = new Object[arraysize];

        System.out.println("New array of size: " + array.length + " created");
        for (int i = 0; i < arraysize; i++) {
            array[i] = new String("abcdef");
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Not empty string has estimated size of " + (mem2 - mem)/arraysize);
        System.out.println();

        Thread.sleep(1000); //wait for 1 sec
    }

    private static void memUsageEmptyStringChar(int arraysize) throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(mem);

        Object[] array = new Object[arraysize];

        System.out.println("New array of size: " + array.length + " created");
        for (int i = 0; i < arraysize; i++) {
            array[i] = new String(new char[0]);
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Empty string without pool usage has estimated size of " + (mem2 - mem)/arraysize);
        System.out.println();

        Thread.sleep(1000); //wait for 1 sec
    }

    private static void memUsageEmptyHashMap(int arraysize) throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(mem);

        Object[] array = new Object[arraysize];

        System.out.println("New array of size: " + array.length + " created");
        for (int i = 0; i < arraysize; i++) {
            array[i] = new HashMap<String,String>();
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Empty hash map has estimated size of " + (mem2 - mem)/arraysize);
        System.out.println();

        Thread.sleep(1000); //wait for 1 sec
    }

    private static void memUsageMyClass(int arraysize) throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(mem);

        Object[] array = new Object[arraysize];


        System.out.println("New array of size: " + array.length + " created");
        for (int i = 0; i < arraysize; i++) {
            array[i] = new MyClass();
        }
        long mem2 = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("MyClass object has estimated size of " + (mem2 - mem)/arraysize);
        System.out.println();

        Thread.sleep(1000); //wait for 1 sec
    }
}
