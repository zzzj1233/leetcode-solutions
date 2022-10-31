package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-12-08 12:18
 */
public class Leet1732 {

    /**
     * 有一个自行车手打算进行一场公路骑行，这条路线总共由n + 1个不同海拔的点组成。自行车手从海拔为 0的点0开始骑行。
     * <p>
     * 给你一个长度为 n的整数数组gain，其中 gain[i]是点 i和点 i + 1的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：gain = [-5,1,5,0,-7]
     * 输出：1
     * 解释：海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
     * 示例 2：
     * <p>
     * 输入：gain = [-4,-3,-2,-1,4,3,2]
     * 输出：0
     * 解释：海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
     */
    public static int largestAltitude(int[] gain) {
        int[] sum = new int[gain.length + 1];

        sum[1] = gain[0];

        for (int i = 1; i < gain.length; i++) {
            sum[i + 1] = gain[i] + sum[i];
        }

        int max = 0;

        for (int i : sum) {
            max = Math.max(i, max);
        }

        return max;
    }

}
