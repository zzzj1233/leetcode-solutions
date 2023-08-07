package com.zzzj.bit;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-28 14:47
 */
public class Leet600 {


    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {

            if (findIntegers(i) != violence(i)) {
                System.out.println("Error");

                System.out.println("i = " + i);
                System.out.println("findIntegers(i) = " + findIntegers(i));
                System.out.println("violence(i) = " + violence(i));

                return;
            }

        }

        System.out.println("Ok");
    }


    public static boolean hasConsecutiveOnes(int num) {
        return (num & (num << 1)) != 0;
    }


    public static int violence(int n) {

        int ans = 0;

//        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i <= n; i++) {
            if (!hasConsecutiveOnes(i)) {
//                set.add(i);
                ans++;
            }
        }

//        System.out.println(set);

        return ans;
    }

    // 二进制中不存在连续的1
    public static int findIntegers(int n) {

        String value = Integer.toBinaryString(n);

        int[][] memo = new int[value.length()][2];

        for (int i = 0; i < value.length(); i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(value, 0, false, true, memo);
    }

    public static int dfs(String value, int index, boolean preOne, boolean isLimit, int[][] memo) {

        if (index >= value.length()) {
            return 1;
        }

        int secIndex = preOne ? 1 : 0;

        if (!isLimit && memo[index][secIndex] != -1) return memo[index][secIndex];

        int limit = Character.digit(value.charAt(index), 10);

        if (preOne) {
            int res = dfs(value, index + 1, false, isLimit && limit == 0, memo);
            if (!isLimit) memo[index][secIndex] = res;
            return res;
        }

        int result = 0;

        if (isLimit) {

            for (int i = 0; i < limit; i++) {
                result += dfs(value, index + 1, i % 2 != 0, false, memo);
            }

            result += dfs(value, index + 1, limit % 2 != 0, true, memo);

        } else {

            for (int i = 0; i <= 1; i++) {
                result += dfs(value, index + 1, i % 2 != 0, false, memo);
            }

            memo[index][secIndex] = result;
        }

        return result;
    }

}
