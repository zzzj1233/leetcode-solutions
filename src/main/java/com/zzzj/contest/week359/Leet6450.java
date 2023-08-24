package com.zzzj.contest.week359;

public class Leet6450 {

    public static void main(String[] args) {

        System.out.println(minimumSum(5, 4));

        System.out.println(minimumSum(2, 6));

        System.out.println(minimumSum(50, 49));

    }

    public static int minimumSum(int n, int k) {

        boolean[] used = new boolean[101];

        int ans = 0;

        for (int i = 1, cnt = 0; cnt < n; i++) {
            if (k - i >= 0 && used[k - i]) continue;
            ans += i;
            used[i] = true;
            cnt++;
        }

        return ans;
    }

}
