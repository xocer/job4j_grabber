package ru.job4j.gc.jmh;

import org.apache.commons.lang3.ArrayUtils;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class Article {

    public static boolean generateBy(String origin, String line) {
        String[] split = origin.split("[^a-zA-Zа-яёА-ЯЁ]");
        String[] newText = line.split("[^a-zA-Zа-яёА-ЯЁ]");
        for (int i = 0; i < newText.length; i++) {
            if (!ArrayUtils.contains(split, newText[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean generateBy2(String origin, String line) {
        String[] split = origin.split("[^a-zA-Zа-яёА-ЯЁ]");
        String[] newText = line.split("[^a-zA-Zа-яёА-ЯЁ]");
        int count = 0;
        for (int i = 0; i < newText.length; i++) {
            for (int j = 0; j < split.length; j++) {
                if (split[j].equals(newText[i])) {
                    count = 0;
                    break;
                }
                count++;
                if (count == split.length) {
                    return false;
                }
            }
        }
        return true;
    }

    @Benchmark
    public void testBenchmark() {
        generateBy("Мама мыла раму и окно", "мыла пол");
    }

    @Benchmark
    public void testBenchmark2() {
        generateBy2("Мама мыла раму и окно",
                "мыла пол");
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Main.main(args);
    }
}
