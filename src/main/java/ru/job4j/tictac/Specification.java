package ru.job4j.tictac;

public interface Specification {
    // устанавливаем игрока, чей будет ход
    void setActivePlayer(Player player);

    //проверяем, что по указанным координатам можно добавить значение
    boolean checkFields(int x, int y);
}
