package com.zzzj.contest.week368;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Q3 {

    public static void main(String[] args) {
//
        System.out.println(minGroupsForValidAssignment(new int[]{1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3}));

        System.exit(0);

        System.out.println(minGroupsForValidAssignment(new int[]{3, 2, 3, 2, 3}));

        System.out.println(minGroupsForValidAssignment(new int[]{10, 10, 10, 3, 1, 1}));

        // [1,1,1,3]
        System.out.println(minGroupsForValidAssignment(new int[]{1, 1, 1, 3}));
        // [1,1,1,3,1,2,2,2,3]
        // 4
        System.out.println(minGroupsForValidAssignment(new int[]{1, 1, 1, 1, 2, 2, 2, 3, 3}));

        // [1,1,2,1,1,3,2,1,2,2,3,1]
        System.out.println(minGroupsForValidAssignment(new int[]{1, 1, 2, 1, 1, 3, 2, 1, 2, 2, 3, 1}));
    }

    public static int minGroupsForValidAssignment(int[] nums) {

        Arrays.sort(nums);

        System.out.println("nums = " + Arrays.toString(nums));

        Map<Integer, Integer> group = new HashMap<>();

        int maxGroup = Integer.MAX_VALUE;

        for (int num : nums) {
            int cnt = group.getOrDefault(num, 0) + 1;
            group.put(num, cnt);
        }

        for (Map.Entry<Integer, Integer> entry : group.entrySet())
            maxGroup = Math.min(maxGroup, entry.getValue() + 1);

        Collection<Integer> values = group.values();

        int ans = nums.length;

        while (maxGroup > 1) {

            final int expect = maxGroup;

            if (values.stream()
                    .allMatch(cnt -> cnt % expect == 0 || cnt % (expect - 1) == 0 || cnt % expect == expect - 1)) {
                ans = Math.min(ans, groupCnt(values, expect));
            }

            maxGroup--;
        }

        return ans;
    }

    public static int groupCnt(Collection<Integer> values, int expect) {
        return values.stream().mapToInt(cnt -> {
            if (cnt % expect == 0)
                return cnt / expect;
            if (cnt % expect == expect - 1)
                return cnt / expect + 1;
            return cnt / (expect - 1);
        }).sum();
    }


}
