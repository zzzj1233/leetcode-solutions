package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2021-10-28 17:21
 */
public class Leet740 {

    /**
     * 给你一个整数数组nums，你可以对它进行一些操作。
     * <p>
     * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。
     * <p>
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,4,2]
     * 输出：6
     * 解释：
     * 删除 4 获得 4 个点数，因此 3 也被删除。
     * 之后，删除 2 获得 2 个点数。总共获得 6 个点数 。
     * <p>
     * 示例2：
     * <p>
     * 输入：nums = [2,2,3,3,3,4]
     * 输出：9
     * 解释：
     * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
     * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
     * 总共获得 9 个点数。
     */
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(5, 0, 10);
            if (deleteAndEarn(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(deleteAndEarn(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int deleteAndEarn(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);
            if (value == null) {
                map.put(nums[i], nums[i]);
            } else {
                map.put(nums[i], value + nums[i]);
            }
        }

        if (map.size() == 1) {
            return map.firstEntry().getValue();
        }

        int[] dp = new int[map.size()];

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();

        Map.Entry<Integer, Integer> first = iterator.next();
        Map.Entry<Integer, Integer> second = iterator.next();

        dp[0] = first.getValue();
        dp[1] = second.getKey() == first.getKey() + 1 ? Math.max(first.getValue(), second.getValue()) : first.getValue() + second.getValue();

        Map.Entry<Integer, Integer> prev = second;

        int i = 2;

        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getKey() == prev.getKey() + 1) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + next.getValue());
            } else {
                dp[i] = next.getValue() + dp[i - 1];
            }
            i++;
            prev = next;
        }

        return dp[dp.length - 1];
    }

    public static int right(int[] nums) {
        int[] trans = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            trans[nums[i]] += nums[i];
        }

        int[] dp = new int[10001];

        dp[0] = 0;
        dp[1] = trans[1];
        for (int i = 2; i < trans.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + trans[i]);
        }

        return dp[dp.length - 1];
    }

}
