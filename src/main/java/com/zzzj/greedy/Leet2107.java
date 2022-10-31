package com.zzzj.greedy;

import java.util.HashMap;
import java.util.Map;

public class Leet2107 {

    public static int shareCandies(int[] candies, int k) {

        int N = candies.length;

        if (k >= N) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int candy : candies) {
            map.put(candy, map.getOrDefault(candy, 0) + 1);
        }

        int cur = map.size();

        for (int i = 0; i < k; i++) {
            int candy = candies[i];
            Integer count = map.get(candy);
            map.put(candy, count - 1);
            if (count == 1) {
                cur--;
            }
        }

        int ans = cur;

        for (int i = k; i < N; i++) {
            int candy = candies[i];

            int newCount = map.getOrDefault(candies[i - k], 0) + 1;

            map.put(candies[i - k], newCount);

            if (newCount == 1){
                cur++;
            }

            Integer count = map.get(candy);

            map.put(candy, count - 1);

            if (count == 1) {
                cur--;
            }

            ans = Math.max(ans, cur);
        }

        return ans;
    }

}
