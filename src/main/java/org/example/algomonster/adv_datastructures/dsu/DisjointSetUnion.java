package org.example.algomonster.adv_datastructures.dsu;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class DisjointSetUnion<T> {
    private Map<T, T> f = new HashMap<>();

    public T find(T x) {
        T y = f.getOrDefault(x, x);
        if (!y.equals(x)) { // x is part of a set;
            y = find(y);
            f.put(x, y);
        }
        return y;
    }

    public void union(T x, T y) {
        f.put(find(x), find(y));
    }
}
