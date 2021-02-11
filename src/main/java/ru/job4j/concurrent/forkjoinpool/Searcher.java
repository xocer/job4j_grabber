package ru.job4j.concurrent.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Searcher extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int object;

    public Searcher(int[] array, int from, int to, int object) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.object = object;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            return lineSearch();
        }

        int mid = (from + to) / 2;
        Searcher left = new Searcher(array, from, mid, object);
        Searcher right = new Searcher(array, mid + 1, to, object);

        left.fork();
        right.fork();
        Integer a = left.join();
        Integer b = right.join();

        return (Integer) Math.max(a, b);
    }

    private int lineSearch() {
        for (int i = from; i < to; i++) {
            if (array[i] == object) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 22, 33, 11, 2312,
                454654, 34, 9, 99, 988, 7665, 6565, 45345, 34534578, 232346, 121112, 66, 77};
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(new Searcher(array, 0, array.length, 988));
        System.out.println(result);
    }
}
