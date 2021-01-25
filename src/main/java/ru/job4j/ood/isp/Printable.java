package ru.job4j.ood.isp;

import java.io.File;

public interface Printable {
    void printToConsole(Menu menu);

    void printToFile(File file);
}
