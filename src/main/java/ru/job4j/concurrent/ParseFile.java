package ru.job4j.concurrent;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append(reader.readLine());
        }
        return builder.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        int data;
        while ((data = reader.read()) != -1) {
            if (data < 0x80) {
                builder.append((char) data);
            }
        }
        return builder.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
    }
}
