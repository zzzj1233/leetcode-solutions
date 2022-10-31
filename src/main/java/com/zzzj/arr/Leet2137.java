package com.zzzj.arr;

public class Leet2137 {

    public static double equalizeWater(int[] buckets, int loss) {

        long sum = 0;

        for (int bucket : buckets) {
            sum += bucket;
        }

        double low = 0;
        double high = sum;

        while (low < high - 1e-9) {
            double mid = low + ((high - low) / 2);

            if (check(buckets, mid, loss)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static boolean check(int[] buckets, double m, int loss) {
        double nd = 0, sum = 0;
        for (int i = 0; i < buckets.length; ++i) {
            if (buckets[i] <= m) {
                nd += m - buckets[i];
            } else {
                sum += buckets[i] - m;
            }
        }
        return nd <= sum * (100 - loss) / 100;
    }


}
