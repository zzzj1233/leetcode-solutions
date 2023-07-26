package com.zzzj.contest.dweek;

public class Leet2745 {

    public static void main(String[] args) {

        System.out.println(longestString(2, 5, 1));

        System.out.println(longestString(3, 2, 2));

    }

    public static int longestString(int x, int y, int z) {

        // AAA
        // BBB


        // x : y
        // y : x | z
        // z : x | z

        int ans = 0;

        while (y > 0 && x > 0) {
            y--;
            x--;
            ans += 4;
        }

        if (x > 0) { // y用完了
            // 再接一个x
            ans += 2;
        } else { // x用完了
            while (y > 0 && z > 0) {
                z--;
                y--;
                ans += 4;
            }
        }

        while (z > 0) {
            ans += 2;
            z--;
        }

        return ans;
    }

}
