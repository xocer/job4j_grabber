package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.util.function.Predicate;

@ThreadSafe
public class ParseFile {
    @GuardedBy("this")
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        return getText(i -> i > -1);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getText(i -> i > -1 && i < 0x80);
    }

    private synchronized String getText(Predicate<Integer> predicate) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = reader.read()) != -1) {
                if (predicate.test(data)) {
                    builder.append((char) data);
                }
            }
        }
        return builder.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

    public static void main(String[] args) throws IOException {
        ParseFile parseFile = new ParseFile();
        parseFile.setFile(new File("test.txt"));
        System.out.println(parseFile.getContent());
        System.out.println(parseFile.getContentWithoutUnicode());
    }
}
