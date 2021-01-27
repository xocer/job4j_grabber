package ru.job4j.tictac;

public class SimplePlayer implements Player {
    Specification specification;
    String symbol;

    public SimplePlayer(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean makeMove(String[][] array, int x, int y) {
        if (specification.checkFields(x, y)) {
            add(array, x, y);
            return true;
        }
        return false;
    }

    @Override
    public void add(String[][] array, int x, int y) {
        array[x][y] = symbol;
    }
}
