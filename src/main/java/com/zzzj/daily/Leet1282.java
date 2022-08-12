package com.zzzj.daily;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-12 12:07
 */
public class Leet1282 {

    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        /**
         * 其实这道题是要我们将数组中值相等的索引放到一个分组中。
         *
         * 输入：groupSizes = [2,1,3,3,3,2]
         * 输出：[[1],[0,5],[2,3,4]]
         * 数组的索引对应的是用户的 ID，用上面的代码来举例。例如用户 0 所在组大小为 2，也就是说这个组有 2 个人。然后索引 5，也就是用户 5 所在的用户组也是 2。说明用户 0 和用户 5 是在一个组。索引 1，也就是用户 1 所在的组为 1，只有它自己。用户 2 3 4 为一组，因为组大小为 3，有 3 个人。
         *
         * 这样就能看出来有 3 个组了，所以答案是 [[1],[0,5],[2,3,4]]。
         */

        List<List<Integer>> ans = new ArrayList<>();

        Map<Integer, List<Integer>> curMap = new HashMap<>();

        for (int i = 0; i < groupSizes.length; i++) {
            // i = 人
            // groupSizes[i] = 组大小
            int gs = groupSizes[i];
            if (gs == 1) {
                ans.add(Collections.singletonList(i));
            } else {
                List<Integer> list = curMap.computeIfAbsent(gs, ignore -> new ArrayList<>(gs));
                list.add(i);
                if (list.size() == gs) {
                    ans.add(new ArrayList<>(list));
                    list.clear();
                }
            }
        }

        return ans;
    }

}
