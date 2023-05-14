package com.zzzj.greedy;

import java.util.Arrays;
import java.util.LinkedList;

public class Leet1111 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(maxDepthAfterSplit("(()())")));

        System.out.println(Arrays.toString(maxDepthAfterSplit("((()))")));

    }

    public static int[] maxDepthAfterSplit(String seq) {

        int l1 = 0;
        int l2 = 0;

        int N = seq.length();

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            if (seq.charAt(i) == '(') {
                if (l1 <= l2) {
                    l1++;
                } else {
                    l2++;
                    ans[i] = 1;
                }
            } else {
                if (l1 >= l2) {
                    l1--;
                } else {
                    l2--;
                    ans[i] = 1;
                }
            }
        }

        return ans;
    }

}
