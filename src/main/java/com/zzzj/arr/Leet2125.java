package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-11-23 11:18
 */
public class Leet2125 {

    public static int numberOfBeams(String[] bank) {

        int M = bank.length;

        int N = bank[0].length();

        int pre = 0;

        int ans = 0;

        for (int i = 0; i < M; i++) {

            int count = 0;

            for (int j = 0; j < N; j++) {
                if (bank[i].charAt(j) == '1') {
                    count++;
                }
            }

            if (count > 0) {
                ans += count * pre;
                pre = count;
            }

        }

        return ans;
    }

}
