package com.zzzj.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-09-22 18:18
 */
public class Leet2260 {

    public static void main(String[] args) {
        System.out.println(minimumCardPickup(new int[]{3, 4, 2, 3, 4, 7}));
    }

    public static int minimumCardPickup(int[] cards) {

        Map<Integer, Integer> position = new HashMap<>();

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < cards.length; i++) {
            int card = cards[i];
            if (position.containsKey(card)) {
                ans = Math.min(ans, i - position.get(card) + 1);
            }
            position.put(card, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
