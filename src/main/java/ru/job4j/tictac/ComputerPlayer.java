package ru.job4j.tictac;

public class ComputerPlayer implements Player {
    private String symbol;

    public ComputerPlayer(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean makeMove(Board array, int x, int y) {
        return false;
    }

    @Override
    public void add(Board array, int x, int y) {

    }
}
