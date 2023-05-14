package com.zzzj.greedy;

public class Leet2555 {

    public static void main(String[] args) {
        System.out.println(maximizeWin(new int[]{1, 1, 2, 2, 3, 3, 5}, 2));
        System.out.println(maximizeWin(new int[]{1, 2, 3, 4}, 0));
    }

    public static int maximizeWin(int[] prizePositions, int k) {

        int N = prizePositions.length;

        int[] front = new int[N];

        front[0] = 1;

        for (int i = 1; i < N; i++) {
            front[i] = Math.max(front[i - 1], frontSum(i, k, prizePositions));
        }

        int[] back = new int[N];

        back[N - 1] = 1;

        for (int i = N - 2; i >= 0; i--) {
            back[i] = Math.max(back[i + 1], backSum(i, k, prizePositions));
        }

        int ans = front[0];

        for (int i = 0; i < N - 1; i++) {
            ans = Math.max(ans, front[i] + back[i + 1]);
        }

        return ans;
    }

    public static int backSum(int index, int k, int[] prizePositions) {
        int tar = prizePositions[index] + k;

        int left = index;
        int right = prizePositions.length - 1;

        int tarIdx = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (prizePositions[mid] <= tar) {
                tarIdx = mid;
                left = mid + 1;
            } else right = mid - 1;
        }

        return (tarIdx - index) + 1;
    }

    public static int frontSum(int index, int k, int[] prizePositions) {
        int tar = prizePositions[index] - k;

        int left = 0;
        int right = index;

        int tarIdx = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (prizePositions[mid] >= tar) {
                tarIdx = mid;
                right = mid - 1;
            } else left = mid + 1;
        }

        return index - tarIdx + 1;
    }

}
