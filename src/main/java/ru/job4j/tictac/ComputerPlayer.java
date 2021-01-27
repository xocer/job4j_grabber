package ru.job4j.tictac;

public class ComputerPlayer implements Player{
    String symbol;

    public ComputerPlayer(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean makeMove(String[][] array, int x, int y) {
        return false;
    }

    @Override
    public void add(String[][] array, int x, int y) {

    }
}
