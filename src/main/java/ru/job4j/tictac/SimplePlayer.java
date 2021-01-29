package ru.job4j.tictac;

public class SimplePlayer implements Player {
    private Logic logic;
    private String symbol;

    public SimplePlayer(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean makeMove(Board array, int x, int y) {
        if (logic.checkFields(x, y)) {
            add(array, x, y);
            return true;
        }
        return false;
    }

    @Override
    public void add(Board array, int x, int y) {
        array.setArray(x, y, symbol);
    }
}
