package com.zzzj.contest.week345;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-01 14:32
 */
public class Leet2682 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(circularGameLosers(5, 2)));

        System.out.println(Arrays.toString(circularGameLosers(4, 4)));

        System.out.println(Arrays.toString(circularGameLosers(2, 1)));

        System.out.println(Arrays.toString(circularGameLosers(5, 3)));

    }

    public static int[] circularGameLosers(int n, int k) {

        boolean[] visited = new boolean[n + 1];

        int cur = 1;

        int step = 1;

        while (!visited[cur]) {
            visited[cur] = true;
            cur += step * k;
            step++;
            if (cur > n) {
                cur %= n;
                if (cur == 0) cur = n;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) list.add(i);
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

}
