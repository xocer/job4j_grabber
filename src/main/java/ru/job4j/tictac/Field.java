package ru.job4j.tictac;

public interface Field {
    /* true - если есть 3 одинаковые клетки по одной линии, либо диагонали, false - если нет */
    boolean isFinish(int[][] fieldsArray);
}
