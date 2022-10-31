package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-10-28 18:43
 */
public class Leet1870 {

    public static void main(String[] args) {
        System.out.println(100000 / 100001);
//        System.out.println(minSpeedOnTime(new int[]{1, 1, 100000}, 2.01));
    }

    public static int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length - 1 >= hour) return -1;

        int low = 1;
        int high = (int) 1e7;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (check(dist, hour, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return high + 1;
    }

    public static boolean check(int[] dist, double hour, int speed) {
        double total = 0;

        for (int i = 0; i < dist.length - 1; i++) {
            double spend = ((double) dist[i]) / speed;
            total += Math.ceil(spend);
            if (total > hour) {
                return false;
            }
        }

        total += ((double) dist[dist.length - 1]) / speed;

        return total <= hour;
    }

}
