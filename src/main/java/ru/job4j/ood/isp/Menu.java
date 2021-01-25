package ru.job4j.ood.isp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Menu {
    boolean add(String parent, String child);

    Optional<Node> findBy(String value);

    @Data
    class Node {
        String value;
        final List<Node> children = new ArrayList<>();

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
