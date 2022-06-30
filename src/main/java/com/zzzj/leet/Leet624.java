package com.zzzj.leet;

import java.util.List;

/**
 * @author zzzj
 * @create 2022-06-24 16:28
 */
public class Leet624 {

    public static int maxDistance(List<List<Integer>> arrays) {

        // 升序数组
        List<Integer> list1 = arrays.get(0);
        int max = list1.get(list1.size() - 1);
        int min = list1.get(0);

        int ans = 0;

        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> item = arrays.get(i);
            int curMax = item.get(item.size() - 1);
            int curMin = item.get(0);

            ans = Math.max(ans, max - curMin);
            ans = Math.max(ans, curMax - min);

            max = Math.max(max, curMax);
            min = Math.min(min, curMin);
        }

        return ans;
    }

}
