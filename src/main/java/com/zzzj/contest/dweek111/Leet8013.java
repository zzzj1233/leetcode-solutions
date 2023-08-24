package com.zzzj.contest.dweek111;

import com.zzzj.graph.leetcode.FarmerAndSheep;

import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;

public class Leet8013 {

    public static void main(String[] args) {

        System.out.println(numberOfBeautifulIntegers(10, 20000, 3));
        System.out.println(check(10, 20000, 3));

        System.out.println(numberOfBeautifulIntegers(36, 60, 3));
        System.out.println(check(36, 60, 3));

        System.out.println(numberOfBeautifulIntegers(10, 20, 3));
        System.out.println(check(10, 20, 3));

        System.out.println(numberOfBeautifulIntegers(1, 10, 1));
        System.out.println(check(1, 10, 1));

        System.out.println(numberOfBeautifulIntegers(5, 5, 2));
        System.out.println(check(5, 5, 2));

    }

    public static int check(int low, int high, int k) {

        int ans = 0;

        for (int i = low; i <= high; i++) {
            if (i % k == 0 && isEvenOddSumEqual(i))
                ans++;
        }

        return ans;
    }

    public static boolean isEvenOddSumEqual(int num) {
        int evenSum = 0; // 用于存储偶数位数字之和
        int oddSum = 0;  // 用于存储奇数位数字之和

        while (num > 0) {
            int digit = num % 10; // 获取当前位的数字
            if (digit % 2 == 0) {
                evenSum++; // 偶数位
            } else {
                oddSum++; // 奇数位
            }
            num /= 10; // 去掉最低位
        }

        // 检查奇数位和与偶数位和是否相等
        return evenSum == oddSum;
    }

    public static int numberOfBeautifulIntegers(int low, int high, int k) {
        return calc(high, k) - calc(low - 1, k);
    }

    public static int calc(int value, int k) {

        String str = String.valueOf(value);

        int len = str.length();

        int maxOddEven = (int) Math.ceil(str.length() / 2.0);

        int[][][][] memo = new int[len][maxOddEven + 1][maxOddEven + 1][k];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= maxOddEven; j++) {
                for (int l = 0; l <= maxOddEven; l++) {
                    Arrays.fill(memo[i][j][l], -1);
                }
            }
        }

        return dfs(str, 0, true, 0, 0, 0, false, k, memo, maxOddEven);
    }

    public static int dfs(String value,
                          int index,
                          boolean isLimit,
                          int evenCnt,
                          int oddCnt,
                          int divisible,
                          boolean isNum,
                          int k,
                          int[][][][] memo,
                          int maxOddEven
    ) {

        if (index >= value.length())
            return evenCnt == oddCnt && divisible % k == 0 ? 1 : 0;

        if (evenCnt > maxOddEven || oddCnt > maxOddEven)
            return 0;

        int result = 0;

        if (!isNum)
            result += dfs(value, index + 1, false, 0, 0, 0, false, k, memo, maxOddEven);

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            int mul = divisible * 10;

            for (int i = isNum ? 0 : 1; i <= limit; i++) {
                boolean isEven = i % 2 == 0;

                result += dfs(value, index + 1, i == limit, evenCnt + (isEven ? 1 : 0), oddCnt + (isEven ? 0 : 1), (mul + i) % k, true, k, memo, maxOddEven);
            }

        } else {

            if (memo[index][oddCnt][evenCnt][divisible] != -1)
                return memo[index][oddCnt][evenCnt][divisible];

            int mul = divisible * 10;

            for (int i = isNum ? 0 : 1; i < 10; i++) {
                boolean isEven = i % 2 == 0;

                result += dfs(value, index + 1, false, evenCnt + (isEven ? 1 : 0), oddCnt + (isEven ? 0 : 1), (mul + i) % k, true, k, memo, maxOddEven);
            }

            memo[index][oddCnt][evenCnt][divisible] = result;
        }

        return result;
    }

}
