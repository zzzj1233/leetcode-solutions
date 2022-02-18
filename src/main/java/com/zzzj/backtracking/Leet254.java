package com.zzzj.backtracking;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-15 15:20
 */
public class Leet254 {

    public static void main(String[] args) {
        // System.out.println(getFactors(12));
        System.out.println(getFactors(32));
    }

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();

        process(ans, 2, n, new LinkedList<>());

        return ans;
    }

    public static void process(List<List<Integer>> ans, int factor, int n, LinkedList<Integer> path) {
        int end = 0;

        for (int i = factor; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                path.add(i);
                process(ans, i, n / i, path);
                path.removeLast();
            }
        }

        if (!path.isEmpty()){
            List<Integer> list = new ArrayList<>(path);
            list.add(n);
            ans.add(list);
        }

    }

}
