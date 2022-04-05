package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-17 19:52
 */
public class Leet171 {


    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
        System.out.println(titleToNumber("A"));
    }

    public static int titleToNumber(String columnTitle) {
        // A = 65
        // Z = 90
        int N = columnTitle.length();

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int num = columnTitle.charAt(i) - 'A' + 1;

            int curVal = (int) Math.pow(26, N - i - 1);

            ans += curVal * num;
        }

        return ans;
    }

}
