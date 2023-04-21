package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-02-28 15:27
 */
public class Leet2086 {

    public static void main(String[] args) {

        System.out.println(minimumBuckets("H..H"));

        System.out.println(minimumBuckets(".H.H."));

        System.out.println(minimumBuckets(".HHH."));

        System.out.println(minimumBuckets("H"));

        System.out.println(minimumBuckets("."));

    }

    public static int minimumBuckets(String street) {

        int N = street.length();

        // 每个H旁边至少有一个水桶

        // 如果有H旁边没法放水桶,那么返回-1

        final char HOUSE = 'H';

        int lastBucketIndex = Integer.MIN_VALUE;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            char c = street.charAt(i);

            if (c == HOUSE) {
                // 左边已经有桶了
                if (lastBucketIndex == i - 1) {
                    continue;
                }

                // 只能往左放
                if (i + 1 == N || street.charAt(i + 1) == HOUSE) {

                    // 左边放不了
                    if (i == 0 || street.charAt(i - 1) == HOUSE) {
                        return -1;
                    }

                    lastBucketIndex = i - 1;
                    ans++;
                } else {
                    // 尝试往右边放
                    lastBucketIndex = i + 1;
                    ans++;
                }

            }

        }


        return ans;
    }

}
