package ru.job4j.tictac;

import java.io.OutputStream;

public class Game implements Field, ConsoleHelper{
    final Mark<OutputStream> markO = new ConsoleMarkO();
    final Mark<OutputStream> markX = new ConsoleMarkX();
    final String[][] array = new String[3][3];
    Player activePlayer;

    @Override
    public boolean isFinish(int[][] fieldsArray) {
        return false;
    }

    @Override
    public String getCoordinate() {
        return null;
    }

    public void game(Player first, Player second) {
        // тут будет такая логика - реализую метод isFinish, запущу цикл, пока этот метод не даст true
        // дальше делаем запрос к пользователю ввести координаты, вызываем у активного игрока метод makeMove, передаем туда координаты
        // делаем еще раз проверку isFinish, если false, меняем активного пользователя и ход делает второй игрок
    }

    public static void main(String[] args) {
        Game game = new Game();
        Player playerX = new SimplePlayer("X");
        Player playerO = new ComputerPlayer("O");
        game.game(playerX, playerO);
    }
}
