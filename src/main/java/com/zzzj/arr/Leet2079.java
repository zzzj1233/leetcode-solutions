package com.zzzj.arr;

public class Leet2079 {

    public static int wateringPlants(int[] plants, int capacity) {

        int N = plants.length;

        int cur = capacity;

        int ans = N;

        for (int i = 0; i < N; i++) {
            int plant = plants[i];
            if (cur >= plant) {
                cur -= plant;
            } else {
                cur = capacity - plant;
                ans += (i << 1) + 1;
            }
        }

        return ans;
    }

}
