package com.zzzj.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-02-15 14:41
 */
public class Leet1238 {


    /**
     * 给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
     * <p>
     * p[0] = start
     * p[i] 和 p[i+1] 的二进制表示形式只有一位不同
     * p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2, start = 3
     * 输出：[3,2,0,1]
     * 解释：这个排列的二进制表示是 (11,10,00,01)
     * 所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
     */
    public static List<Integer> circularPermutation(int n, int start) {
        LinkedList<Integer> ans = new LinkedList<>();

        ans.add(start);

        process(ans, n, start, (int) (StrictMath.pow(2, n) - 1));

        return ans;
    }

    public static void process(LinkedList<Integer> ans, int n, int start, int special) {
        // int m = start ^ ( 1 << i );
        Integer last = ans.peekLast();

    }

}
