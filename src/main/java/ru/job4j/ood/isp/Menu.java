package ru.job4j.ood.isp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Menu {
    boolean add(String parent, String child, Action action);

    Optional<Node> findBy(String value);

    @Data
    class Node {
        private String value;
        private final List<Node> children = new ArrayList<>();
        private final Action action;

        public Node(String value, Action action) {
            this.value = value;
            this.action = action;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
