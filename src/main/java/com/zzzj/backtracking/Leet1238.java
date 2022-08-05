package com.zzzj.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-02-15 14:41
 */
public class Leet1238 {


    public static List<Integer> circularPermutation(int n, int start) {
        LinkedList<Integer> ans = new LinkedList<>();

        ans.add(start);

        process(ans, n, start, (int) (StrictMath.pow(2, n) - 1));

        return ans;
    }

    public static void process(LinkedList<Integer> ans, int n, int start, int special) {
        // int m = start ^ ( 1 << i );
        Integer last = ans.peekLast();

    }

}
