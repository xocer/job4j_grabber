package ru.job4j.gc.jmh;

import org.apache.commons.lang3.ArrayUtils;
import org.openjdk.jmh.annotations.*;

public class Article {

    public Article() {
    }

    @Benchmark
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
}
