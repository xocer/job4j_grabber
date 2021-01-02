package ru.job4j.gc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CacheSoftRef {
    private final Map<String, SoftReference<String>> cash = new HashMap<>();
    private final String nameDir;

    public CacheSoftRef(String nameDir) {
        this.nameDir = nameDir;
    }

    public SoftReference<String> get(String key) {
        SoftReference<String> result = null;
        if (cash.containsKey(key)) {
            result = cash.get(key);
        } else {
            result = add(key);
        }

        return result;
    }

    public SoftReference<String> add(String key) {
        Path path = Paths.get(nameDir + "\\" + key);
        StringBuilder builder = new StringBuilder();
        SoftReference<String> result = null;

        if (Files.isRegularFile(path)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
                while (reader.ready()) {
                    builder.append(reader.readLine());
                }
                result = new SoftReference<>(builder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cash.put(nameDir, result);
        return result;
    }
}
