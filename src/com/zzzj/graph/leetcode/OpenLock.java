package com.zzzj.graph.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * @author Zzzj
 * @create 2021-05-02 19:26
 */

// 752. 打开转盘锁
public class OpenLock {

    private Function<String, String>[] branches;

    public void init() {
        this.branches = new Function[8];
        this.branches[0] = s -> ((Integer.parseInt(s.substring(0, 1)) + 1) >= 10 ? "0" : (Integer.parseInt(s.substring(0, 1)) + 1)) + s.substring(1);
        this.branches[1] = s -> s.substring(0, 1) + ((Integer.parseInt(s.substring(1, 2)) + 1) >= 10 ? "0" : (Integer.parseInt(s.substring(1, 2)) + 1)) + s.substring(2);
        this.branches[2] = s -> s.substring(0, 2) + ((Integer.parseInt(s.substring(2, 3)) + 1) >= 10 ? "0" : (Integer.parseInt(s.substring(2, 3)) + 1)) + s.substring(3);
        this.branches[3] = s -> s.substring(0, 3) + ((Integer.parseInt(s.substring(3, 4)) + 1) >= 10 ? "0" : (Integer.parseInt(s.substring(3, 4)) + 1));
        this.branches[4] = s -> ((Integer.parseInt(s.substring(0, 1)) - 1) < 0 ? "9" : (Integer.parseInt(s.substring(0, 1)) - 1)) + s.substring(1);
        this.branches[5] = s -> s.substring(0, 1) + ((Integer.parseInt(s.substring(1, 2)) - 1) < 0 ? "9" : (Integer.parseInt(s.substring(1, 2)) - 1)) + s.substring(2);
        this.branches[6] = s -> s.substring(0, 2) + ((Integer.parseInt(s.substring(2, 3)) - 1) < 0 ? "9" : (Integer.parseInt(s.substring(2, 3)) - 1)) + s.substring(3);
        this.branches[7] = s -> s.substring(0, 3) + ((Integer.parseInt(s.substring(3, 4)) - 1) < 0 ? "9" : (Integer.parseInt(s.substring(3, 4)) - 1));
    }

    public int openLock(String[] deadends, String target) {
        init();
        final String START = "0000";
        HashSet<String> dead = new HashSet<>(Arrays.asList(deadends));
        HashMap<String, Integer> step = new HashMap<>();

        if (dead.contains(START) || dead.contains(target)) {
            return -1;
        }

        if (target.equals(START)) {
            return 0;
        }

        LinkedList<String> queue = new LinkedList<>();
        queue.add(START);
        step.put(START, 0);

        while (!queue.isEmpty()) {
            String first = queue.pollFirst();
            Integer preStep = step.get(first);
            for (Function<String, String> branchFun : branches) {
                String branch = null;
                branch = branchFun.apply(first);
                if (!dead.contains(branch) && step.get(branch) == null) {
                    queue.addLast(branch);
                    if (branch.equals(target)) {
                        return preStep + 1;
                    }
                    step.put(branch, preStep + 1);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] arr = {"0201", "0101", "0102", "1212", "2002"};
        System.out.println(new OpenLock().openLock(arr, "0202"));
    }

}
