package ru.job4j.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        while (!s.equals("финиш")) {
            for (int i = 0; i < 1000; i++) {
                list.add(new Person(i, "N" + i));
            }
            System.out.println("добавлено");
            s = scanner.nextLine();
        }
    }
}
