package com.zzzj.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-08-19 15:52
 */
public class Leet1557 {

    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        Set<Integer> ans = new HashSet<>(n);

        for (int i = 0; i < n; i++) {
            ans.add(i);
        }

        for (List<Integer> edge : edges) {
            ans.remove(edge.get(1));
        }

        return new ArrayList<>(ans);
    }

}
