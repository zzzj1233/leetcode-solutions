package com.zzzj.window;

/**
 * @author zzzj
 * @create 2022-02-21 18:46
 */
public class Leet995 {

    public static void main(String[] args) {
        System.out.println(minKBitFlips(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
    }

    public static int minKBitFlips(int[] nums, int k) {
        int ans = 0;
        int r = 0;
        int l = 0;

        while (r < nums.length) {
            if (nums[r] == 0) {
                ans++;
                // 反转K
                int end = Math.min(nums.length - 1, r + k - 1);

                boolean init = false;

                for (int i = r; i <= end; i++) {
                    if (nums[i] == 1) {
                        if (!init) {
                            r = i;
                            l = r;
                            init = true;
                        }
                    }
                    nums[i] = nums[i] == 1 ? 0 : 1;
                }

                if (!init) {
                    r = end + 1;
                }
                continue;
            }

            r++;
        }

        return -1;
    }

}
