package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2024-02-20 15:55
 */
public class Leet3040 {

    public static void main(String[] args) {

        System.out.println(maxOperations(new int[]{3, 2, 1, 2, 3, 4}));

        System.out.println(maxOperations(new int[]{3, 2, 6, 1, 4}));

    }

    public static int maxOperations(int[] nums) {

        int N = nums.length;

        if (N < 2)
            return 0;

        return Math.max(
                check(nums, 2, N - 1, nums[0] + nums[1]),
                Math.max(
                        check(nums, 0, N - 3, nums[N - 1] + nums[N - 2]),
                        check(nums, 1, N - 2, nums[0] + nums[N - 1])
                )
        );
    }

    public static int check(int[] nums, int l, int r, int v) {

        int cnt = 1;

        while (l < r) {
            if (nums[l] + nums[l + 1] == v) {
                cnt++;
                l += 2;
            } else if (nums[r] + nums[r - 1] == v) {
                cnt++;
                r -= 2;
            } else if (nums[l] + nums[r] == v) {
                cnt++;
                l++;
                r--;
            } else {
                return cnt;
            }
        }

        return cnt;
    }

}
