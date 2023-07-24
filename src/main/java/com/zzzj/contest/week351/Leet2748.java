package com.zzzj.contest.week351;

/**
 * @author zzzj
 * @create 2023-07-24 12:28
 */
public class Leet2748 {

    public static void main(String[] args) {

        System.out.println(countBeautifulPairs(new int[]{2, 5, 1, 4}));

        System.out.println(countBeautifulPairs(new int[]{11, 21, 12}));

    }

    public static int countBeautifulPairs(int[] nums) {

        int N = nums.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                if (gcd(firstNum(nums[i]), LastNum(nums[j])) == 1)
                    ans++;

            }

        }

        return ans;
    }

    public static int firstNum(int num) {
        return Character.digit(String.valueOf(num).charAt(0), 10);
    }

    public static int LastNum(int num) {
        String value = String.valueOf(num);
        return Character.digit(value.charAt(value.length() - 1), 10);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
