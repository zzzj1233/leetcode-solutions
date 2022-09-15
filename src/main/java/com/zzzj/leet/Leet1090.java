package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-09-07 20:37
 */
public class Leet1090 {

    public static void main(String[] args) {
        // [3,2,3,2,1]
        //[1,0,2,2,1]
        //2
        //1

        System.out.println(largestValsFromLabels(new int[]{3, 2, 3, 2, 1}, new int[]{1, 0, 2, 2, 1}, 2, 1));
    }

    public static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
// 有一些物品，每个物品都有一个价值value以及该物品的对应编号label，我们需要从中拿出来num_wanted个物品，希望物品价值最大，并且每一类物品不超过use_limit个。

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < values.length; i++) {
            queue.add(new int[]{values[i], labels[i]});
        }

        int ans = 0;

        int count = 0;

        Map<Integer, Integer> labelUsed = new HashMap<>();

        while (count < numWanted && !queue.isEmpty()) {
            int[] max =queue.remove();

            Integer value = max[0];

            Integer label = max[1];

            Integer used = labelUsed.getOrDefault(label, 0);

            if (used + 1 > useLimit) {
                continue;
            }

            labelUsed.put(label, used + 1);

            count++;

            ans += value;
        }


        return ans;
    }

}
