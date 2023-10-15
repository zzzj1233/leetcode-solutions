package com.zzzj.contest.week360;

import java.util.HashSet;
import java.util.Set;

public class Leet8022 {

    public static void main(String[] args) {
        System.out.println(minimumPossibleSum(2, 3));
        System.out.println(minimumPossibleSum(3, 3));
        System.out.println(minimumPossibleSum(1, 1));
    }

    public static long minimumPossibleSum(int n, int target) {

        long ans = 0;

        int len = 0;

        Set<Integer> exists = new HashSet<>();

        for (int i = 1; len < n; i++) {
            if (exists.contains(i))
                continue;
//            target -= i;
            exists.add(target - i);
            ans += i;
            len++;
        }

        return ans;
    }

}
