package com.zzzj.leet;

import org.omg.CORBA.IRObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-06-06 18:22
 */
public class Leet2100 {

    public static void main(String[] args) {
        System.out.println(goodDaysToRobBank(new int[]{5, 3, 3, 3, 5, 6, 2}, 2));
        System.out.println(goodDaysToRobBank(new int[]{1, 2, 3, 4}, 1));
    }

    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int N = security.length;

        int[] desc = new int[N];
        int[] incr = new int[N];

        for (int i = N - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                incr[i] = incr[i + 1] + 1;
            }
        }

        for (int i = 1; i < N; i++) {
            if (security[i] <= security[i - 1]) {
                desc[i] = desc[i - 1] + 1;
            }
        }

        int end = N - time;

        List<Integer> ans = new ArrayList<>();

        for (int i = time; i < end; i++) {
            if (desc[i] >= time && incr[i] >= time) {
                ans.add(i);
            }
        }

        return ans;
    }


}
