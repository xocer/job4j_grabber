package ru.job4j.tictac;

public class Board {
    private String[][] array;

    public Board(int a, int b) {
        this.array = new String[a][b];
    }

    public void setArray(int a, int b, String s) {
        array[a][b] = s;
    }
}
