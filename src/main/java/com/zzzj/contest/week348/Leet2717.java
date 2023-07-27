package com.zzzj.contest.week348;

/**
 * @author zzzj
 * @create 2023-07-27 17:35
 */
public class Leet2717 {

    public static void main(String[] args) {

        System.out.println(semiOrderedPermutation(new int[]{2, 1, 4, 3}));

        System.out.println(semiOrderedPermutation(new int[]{2, 4, 1, 3}));

        System.out.println(semiOrderedPermutation(new int[]{1, 3, 4, 2, 5}));

    }

    public static int semiOrderedPermutation(int[] nums) {

        int N = nums.length;

        int firstIndex = -1;

        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) {
                firstIndex = i;
                break;
            }
        }

        int lastIndex = -1;

        for (int i = N - 1; i >= 0; i--) {
            if (nums[i] == N) {
                lastIndex = i;
                break;
            }
        }

        return firstIndex + (N - lastIndex - 1) - (firstIndex > lastIndex ? 1 : 0);
    }

}
