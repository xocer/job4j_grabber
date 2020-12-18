package ru.job4j.gc;

public class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Удален юзер %s %d%n", name, age);
    }

    public static void main(String[] args) throws InterruptedException {
        Runtime r = Runtime.getRuntime();
        long start = r.freeMemory();
        System.out.println("Память до создания объектов = " + start + " байт");
        for (int i = 0; i < 20; i++) {
            new User("Max" + i, i + 10);
        }
        long finish = r.freeMemory();
        System.out.println("Память после создания 20 объектов = " + finish + " байт");
        long resultAll = start - finish;
        System.out.printf("Количество памяти, потраченное на 20 объектов User = "
                + resultAll + " байт%n"
                + "Количество памяти на одного User = " + resultAll / 20 + " байт %n");
    }
}
