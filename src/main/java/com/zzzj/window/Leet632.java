package com.zzzj.window;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-12-15 17:49
 */
public class Leet632 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(smallestRange(Arrays.asList(Arrays.asList(4, 10, 15, 24, 26), Arrays.asList(0, 9, 12, 20), Arrays.asList(5, 18, 22, 30)))));
    }

    /**
     * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
     * 输出：[20,24]
     * 解释：
     * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
     * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
     * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
     */

    private static class Info implements Comparable<Info> {
        private final int val;
        private final int group;

        private Info(int val, int group) {
            this.val = val;
            this.group = group;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.val, o.val);
        }

        @Override
        public String toString() {
            return String.format("{%d = %d}", this.val, this.group);
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int[] answer = new int[2];

        if (nums == null || nums.isEmpty()) {
            return answer;
        }

        List<Info> list = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {
            List<Integer> item = nums.get(i);
            for (Integer num : item) {
                list.add(new Info(num, i));
            }
        }

        Collections.sort(list);

        int k = nums.size();

        int l = 0;
        int r = k - 1;

        Set<Integer> window = new HashSet<>(k << 1);

        // 初始化window
        // 直到window包含0~k的元素


        return answer;
    }

}
