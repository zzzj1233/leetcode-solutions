package com.zzzj.arr;

public class Let2105 {

    public static int minimumRefill(int[] plants, int capacityA, int capacityB) {

        int N = plants.length;

        int left = 0;

        int right = N - 1;

        int alice = capacityA;
        int bob = capacityB;

        int ans = 0;

        while (left < right) {
            int p1 = plants[left];
            int p2 = plants[right];

            if (alice < p1) {
                alice = capacityA - p1;
                ans++;
            } else {
                alice -= p1;
            }

            if (bob < p2) {
                bob = capacityB - p2;
                ans++;
            } else {
                bob -= p2;
            }

            left++;
            right--;
        }

        if (left == right) {
            if (Math.max(alice, bob) < plants[left]) {
                ans++;
            }
        }

        return ans;
    }

}
