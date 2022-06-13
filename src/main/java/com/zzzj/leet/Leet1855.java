package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-10 15:16
 */
public class Leet1855 {

    public static void main(String[] args) {
        System.out.println(maxDistance(new int[]{55, 30, 5, 4, 2}, new int[]{100, 20, 10, 10, 5}));
        System.out.println(maxDistance(new int[]{2, 2, 2}, new int[]{10, 10, 1}));
        System.out.println(maxDistance(new int[]{30, 29, 19, 5}, new int[]{25, 25, 25, 25, 25}));
    }

    /**
     * //输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
     * //输出：2
     * //解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
     * //最大距离是 2 ，对应下标对 (2,4) 。
     * //
     * //
     * // 示例 2：
     * //
     * //
     * //输入：nums1 = [2,2,2], nums2 = [10,10,1]
     * //输出：1
     * //解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
     * //最大距离是 1 ，对应下标对 (0,1) 。
     * //
     * // 示例 3：
     * //
     * //
     * //输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
     * //输出：2
     * //解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
     * //最大距离是 2 ，对应下标对 (2,4) 。
     */
    public static int maxDistance(int[] nums1, int[] nums2) {
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        int ans = 0;

        while (i >= 0) {
            while (nums1[i] > nums2[j]) {
                j--;
                if (j < 0) {
                    return ans;
                }
            }
            ans = Math.max(ans, j - i);
            i--;
        }

        return ans;
    }

}
