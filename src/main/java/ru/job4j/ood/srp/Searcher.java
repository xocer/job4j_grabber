package ru.job4j.ood.srp;

import java.io.File;
import java.util.List;

public interface Searcher {
    void findFiles();

    boolean checkDir();

    void printFiles(List<File> list);
}
