package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-02-24 18:09
 */
public class Leet306 {

    public static void main(String[] args) {

        // System.out.println(isAdditiveNumber("355800363"));
        // System.out.println(isAdditiveNumber("112358"));
        // System.out.println(isAdditiveNumber("199100199"));

        System.out.println(isAdditiveNumber("765207652"));

        for (int i = 0; i < 10000; i++) {
            String num = LeetUtils.randomNumberString(30);
            if (isAdditiveNumber(num) != right(num)) {
                System.out.println(num);
                System.out.println(isAdditiveNumber(num));
                System.out.println(i);
                return;
            }
        }
    }

    public static boolean ans;

    public static boolean isAdditiveNumber(String num) {
        final int N = num.length();

        if (N < 3) {
            return false;
        }

        ans = false;

        char[] chars = num.toCharArray();

        for (int i = 0; i < N - 2; i++) {

            if (chars[0] == '0' && i > 0) {
                break;
            }

            for (int j = i + 1; j < N - 1; j++) {

                if (chars[i + 1] == '0' && j - (i + 1) > 0) {
                    break;
                }

                process(chars, j + 1, parseNum(chars, i + 1, j), parseNum(chars, 0, i));

                if (ans) {
                    return true;
                }

            }

        }

        return false;
    }

    public static void process(char[] chars, int cur, long prev, long prevPrev) {

        long target = prev + prevPrev;

        for (int i = cur; i < chars.length && !ans; i++) {
            if (chars[cur] == '0' && i + 1 - cur > 1) {
                break;
            }

            long curVal = parseNum(chars, cur, i);

            if (curVal > target) {
                break;
            }

            if (curVal == target) {
                if (i == chars.length - 1) {
                    ans = true;
                    return;
                }
                process(chars, i + 1, target, prev);
                break;
            }
        }
    }

    public static long parseNum(char[] chars, int i, int j) {

        long cur = 0;

        while (i <= j) {
            int num = chars[i] - '0';
            cur = cur * 10 + num;
            i++;
        }

        return cur;
    }


    public static boolean right(String num) {
        return dfs(num, 0, 0, 0, 0);
    }

    private static boolean dfs(String num, int index, int count, long prevprev, long prev) {
        if (index >= num.length()) {
            return count > 2;
        }

        long current = 0;
        for (int i = index; i < num.length(); i++) {
            char c = num.charAt(i);

            if (num.charAt(index) == '0' && i > index) {
                // 剪枝1：不能做为前导0，但是它自己是可以单独做为0来使用的
                return false;
            }

            current = current * 10 + c - '0';

            if (count >= 2) {
                long sum = prevprev + prev;
                if (current > sum) {
                    // 剪枝2：如果当前数比之前两数的和大了，说明不合适
                    return false;
                }
                if (current < sum) {
                    // 剪枝3：如果当前数比之前两数的和小了，说明还不够，可以继续添加新的字符进来
                    continue;
                }
            }

            // 当前满足条件了，或者还不到两个数，向下一层探索
            if (dfs(num, i + 1, count + 1, prev, current)) {
                return true;
            }
        }

        return false;
    }


}
