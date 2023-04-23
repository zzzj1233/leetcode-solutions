package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-04-21 18:16
 */
public class Leet2546 {

    public static void main(String[] args) {

        // 00011
        // 00001
        System.out.println(makeStringsEqual("11110", "00001"));

//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int K = 100;

            String s = LeetUtils.randomString(K, "01");
            String t = LeetUtils.randomString(K, "01");

            if (makeStringsEqual(s, t) != right(s, t)) {
                System.out.println("Error");
                System.out.println(s);
                System.out.println(t);
                System.out.println("my ans ");
                System.out.println(makeStringsEqual(s, t));
                System.out.println("right ans");
                System.out.println(right(s, t));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static boolean makeStringsEqual(String s, String target) {

        int N = s.length();

        // i |= j
        // j ^= i

        // 不相同的情况
        // 1. s == 1 ---> s要变成0 , 那么s只能去^1 , 被亦或的元素 | 1 , 1 | 1 = 1 , 那么只要s存在别的1就ok
        // 2. s == 0 ---> s要变成1 , 那么s只能去|1 , 被或的元素 ^ 0 , 任何元素 ^ 0都 = 自己, 那么只要s存在1就OK


        // 1. 不同的情况

        // 0可以直接变成1, 只要有1

        // 1可以变成0, 前提是还有别的1

        if (!s.equals(target) && s.indexOf('1') < 0) {
            return false;
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '1') cnt++;
            else if (s.charAt(i) != target.charAt(i)) cnt++;
        }

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != target.charAt(i))
                if (s.charAt(i) == '1') {
                    if (cnt <= 1) return false;
                    else cnt--;
                } else {
                    if (cnt == 0) return false;
                }
        }

        return true;
    }

    public static boolean right(String s, String target) {
        if (!s.contains("1")) return !target.contains("1");
        return target.contains("1");
    }

}
