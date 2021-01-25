package ru.job4j.ood.isp;

import lombok.Data;

import java.io.File;
import java.util.*;

@Data
public class SimpleMenu implements Printable, Action, Menu {
    final Node root;

    public SimpleMenu(Node root) {
        this.root = root;
    }

    @Override
    public boolean add(String parent, String child) {
        boolean rsl = false;
        Optional<Node> tmp = findBy(parent);
        if (tmp.isPresent() && !findBy(child).isPresent()) {
            tmp.get().getChildren().add(new Node(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node> findBy(String value) {
        Optional<Node> rsl = Optional.empty();
        Queue<Node> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node el = data.poll();
            if (el.getValue().equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    @Override
    public void order(String menuItem) {

    }

    @Override
    public void giveOrder() {

    }

    @Override
    public void printToConsole(Menu menu) {

    }

    @Override
    public void printToFile(File file) {

    }

    public static void main(String[] args) {
        SimpleMenu menu = new SimpleMenu(new Node("Задача 0"));
        menu.add("Задача 0", "Задача 1.");
        menu.add("Задача 1.", "Задача 1.1.");
        menu.add("Задача 1.", "Задача 1.2.");
        menu.add("Задача 1.2.", "Задача 1.2.1.");
        menu.add("Задача 1.1.", "Задача 1.1.1.");
        menu.add("Задача 0", "Задача 2.");
        menu.printToConsole(menu);
    }
}
