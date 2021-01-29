package ru.job4j.ood.isp;

import lombok.Data;

import java.util.*;

@Data
public class SimpleMenu implements Printable, Menu {
    final Node root;

    public SimpleMenu(Node root) {
        this.root = root;
    }

    @Override
    public boolean add(String parent, String child, Action action) {
        boolean rsl = false;
        Optional<Node> tmp = findBy(parent);
        if (tmp.isPresent() && !findBy(child).isPresent()) {
            tmp.get().getChildren().add(new Node(child, action));
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
    public void print(Node node, int level) {
        if (!node.getChildren().isEmpty()) {
            for (Node tmp : node.getChildren()) {
                if (level != 0) {
                    String name = tmp.getValue();
                    StringBuilder newName = new StringBuilder();
                    for (int i = 0; i < level; i++) {
                        newName.append("----");
                    }
                    newName.append(name);
                    tmp.setValue(newName.toString());
                }
                System.out.println(tmp);
                print(tmp, ++level);
                level--;
            }
        }
    }

    public static void main(String[] args) {
        SimpleMenu menu = new SimpleMenu(new Node("Задача 0", new SimpleAction()));
        menu.add("Задача 0", "Задача 1.", new SimpleAction());
        menu.add("Задача 1.", "Задача 1.1.", new SimpleAction());
        menu.add("Задача 1.", "Задача 1.2.", new SimpleAction());
        menu.add("Задача 1.2.", "Задача 1.2.1.", new SimpleAction());
        menu.add("Задача 1.1.", "Задача 1.1.1.", new SimpleAction());
        menu.add("Задача 0", "Задача 2.", new SimpleAction());
        menu.print(menu.getRoot(), 0);
    }
}
