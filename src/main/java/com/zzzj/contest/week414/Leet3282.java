package com.zzzj.contest.week414;

import com.zzzj.graph.bridge.FindBridge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-09-15 17:56
 */
public class Leet3282 {

    public static void main(String[] args) {

        System.out.println(findMaximumScore(
                Arrays.asList(1, 3, 1, 5)
        ));

        System.out.println(findMaximumScore(
                Arrays.asList(4, 3, 1, 3, 2)
        ));

    }

    public static long findMaximumScore(List<Integer> nums) {

        // 1. 只能往后跳
        // 2. 分数 = (j - i) * nums[i]
        // 3. 求最大总得分
        // 4. 最开始位于下标0

        // [1 ,3 ,1 ,5]

        // 0 -> 1 + 1 > 3 = 1 * 1 + 2 * 3

        // [4,3,1,3,2]
        // 0 - 4 = 4 * 4 = 16

        // x -> y 的得分为 : (y - x) * nums[x]
        // 考虑什么情况下不应该跳到y?
        // x -> y -> z
        // 因为: x - z的另一条路径分数更大

        // 考虑: x - z 的总下标距离不变
        // 那么: nums[x]越大, 分数越大
        // 假设 x - z , nums[x]是最大的数, 那么直接跳动到z
        // 否则跳动到下一个比nums[x]更大的数上 ; 如果数字相同, 则下标越小越好

        // 每次查询比nums[x]更大的一个数, 返回下标

        int N = nums.size();

        // 右边第一个比当前元素大的索引
        // 1. 平衡树
        // 2. 单调栈

        int[] max = new int[N];

        Arrays.fill(max, N - 1);

        LinkedList<Integer> stack = new LinkedList<>();

        stack.addLast(N - 1);

        for (int i = N - 2; i >= 0; i--) {

            while (!stack.isEmpty() && nums.get(stack.peekLast()) <= nums.get(i)) {
                stack.removeLast();
            }

            if (!stack.isEmpty())
                max[i] = stack.peekLast();

            stack.addLast(i);

        }

        long ans = 0;

        for (int i = 0; i < N - 1; ) {
            ans += (long) (max[i] - i) * nums.get(i);
            i = max[i];
        }

        return ans;
    }

}
