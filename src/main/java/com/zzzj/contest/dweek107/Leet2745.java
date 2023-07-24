package com.zzzj.contest.dweek107;

/**
 * @author zzzj
 * @create 2023-07-24 14:48
 */
public class Leet2745 {

    public static void main(String[] args) {

        System.out.println(longestString(2, 5, 1));

        System.out.println(longestString(3, 2, 2));

        // expect: 34
        System.out.println(longestString(1, 39, 14));
    }

    // 不允许出现 AAA | BBB
    // x = AA
    // y = BB
    // z = AB

    // x后面只能接: y      : AA + BB (AABB)
    // y后面可以接: x | z  : BB + AA (BBAA) | BB + AB (BBAB)
    // z后面可以接: x | z  : AB + AA (ABAA) | AB + AB (ABAB)
    public static int longestString(int x, int y, int z) {

        int ans = 0;

        // 先把全部的X都消耗完, 因为x后面只能接y
        while (y > 0 && x > 0) {
            y--;
            x--;
            ans += 4;
        }

        if (x > 0 || y > 0) {
            ans += 2;
        }

        while (z > 0) {
            ans += 2;
            z--;
        }

        return ans;
    }

}
