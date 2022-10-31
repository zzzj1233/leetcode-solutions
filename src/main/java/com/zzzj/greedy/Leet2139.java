package com.zzzj.greedy;

public class Leet2139 {

    public static void main(String[] args) {
        System.out.println(minMoves(10, 4));
    }

    public static int minMoves(int target, int maxDoubles) {

        int cur = target;

        int ans = 0;

        while (maxDoubles > 0 && target > 1) {
            if (target % 2 != 0) {
                target--;
                ans++;
            }
            target /= 2;
            maxDoubles -= 1;
            ans++;
        }

        return ans + (target - 1);
    }

}
