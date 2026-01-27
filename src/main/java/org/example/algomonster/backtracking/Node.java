package org.example.algomonster.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    public T val;
    public List<Node<T>> children;

    public Node(T val) {
        this(val, new ArrayList<>());
    }

    public Node(T val, List<Node<T>> children) {
        this.val = val;
        this.children = children;
    }
}