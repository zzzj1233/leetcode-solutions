package com.zzzj.window;

import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-01-17 12:03
 */
public class Leet2488 {

    /**
     * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
     * <p>
     * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
     * small = [1,5,9]
     * 输出: [7,10]
     * 示例 2:
     * <p>
     * 输入:
     * big = [1,2,3]
     * small = [4]
     * 输出: []
     */
    public static void main(String[] args) {
        final int[] big = ArrayUtil.generateArray(30, 0, 10);
        final int[] small = ArrayUtil.generateArray(3, 0, 10);

        System.out.println(Arrays.toString(big));
        System.out.println(Arrays.toString(small));

        System.out.println(Arrays.toString(shortestSeq(big, small)));
    }

    public static int[] shortestSeq(int[] big, int[] small) {
        // 另一种对蛮力方法的考虑是，我们取每个起始索引，在目标字符串中寻找每个元素的下一个出现位置。所有这些出现位置的最大值标志着子序列的尾部（该子序列包含所有目标字符）。这个算法的时间复杂度是多少？怎样才能使它更快呢？

        // 考虑一下前面解释的蛮力解法。瓶颈在于我们反复查询某个特定字符的下一个出现位置。有办法优化该过程么？你应该能在O(1)时间内完成。
        // 你能从每个索引中预先计算一个特定字符的出现位置吗？尝试使用一个多维数组。
        Map<Integer, TreeSet<Integer>> table = new HashMap<>(big.length);

        for (int i = 0; i < big.length; i++) {
            table.computeIfAbsent(big[i], k -> new TreeSet<>()).add(i);
        }

        Set<Integer> set = new HashSet<>(small.length);

        for (int i : small) {
            if (!table.containsKey(i)) {
                return new int[]{};
            }
            set.add(i);
        }


        int l = 0;
        int r = Integer.MAX_VALUE;

        Outer:
        for (int i = 0; i < big.length; i++) {
            int num = big[i];
            if (!set.contains(num)) {
                continue;
            }
            int left = i;
            int right = i;
            for (Integer it : set) {
                if (it == num) {
                    continue;
                }
                TreeSet<Integer> treeSet = table.get(it);

                Integer ceiling = treeSet.ceiling(left);

                if (ceiling == null) {
                    continue Outer;
                }
                right = Math.max(right, ceiling);
            }
            if (right - left < r - l) {
                r = right;
                l = left;
            }
        }

        return new int[]{l, r};
    }

}
