package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-02-28 14:11
 */
public class Leet2554 {

    public static void main(String[] args) {


//        System.out.println(maxCount(
//                new int[]{4, 6, 6, 9, 10},
//                5,
//                14
//        ));
//
//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int len = 500;

            int limit = 10000;

            int[] arr = ArrayUtil.generateArray(len, 1, limit);

            ArrayCopyIterator iterator = new ArrayCopyIterator(arr, true);

            int count = LeetUtils.random.nextInt(limit) + LeetUtils.random.nextInt(limit);

            int max = LeetUtils.random.nextInt(limit) * LeetUtils.random.nextInt(10);

            if (maxCount(iterator.next(), count, max) != right(iterator.next(), count, max)) {
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println(count);
                System.out.println(max);
                System.out.println("my ans : " + maxCount(iterator.next(), count, max));
                System.out.println("right ans : " + right(iterator.next(), count, max));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int maxCount(int[] banned, int n, int maxSum) {

        Arrays.sort(banned);

        int N = banned.length;

        int sum = 0;

        int left = 1;

        int ans = 0;

        for (int i = 0; i < N && banned[i] <= n && left <= n; i++) {

            int num = banned[i];

            if (num == left) {
                left = num + 1;
                continue;
            }

            if (num - 1 < left) {
                continue;
            }

            // num不可选, 可选范围为 num - 1 ~ left, 然后left = num + 1
            int segSum = AP(banned, left, num - 1);

            if (sum + segSum > maxSum) {
                int search = binSearch(banned, left, num - 1, maxSum - sum);

                if (search != -1) {
                    ans += search - left + 1;
                }

                return ans;
            } else {
                sum += segSum;
                ans += num - left;
            }

            left = num + 1;
        }

        if (left <= n) {
            int segSum = AP(banned, left, n);

            if (sum + segSum > maxSum) {
                int search = binSearch(banned, left, n, maxSum - sum);

                if (search != -1) {
                    ans += search - left + 1;
                    return ans;
                }

            } else {
                ans += n - left + 1;
            }

        }

        return ans;
    }

    public static int binSearch(int[] banned, int left, int right, int remain) {
        int ans = -1;

        int l = left;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            int midSum = AP(banned, l, mid);

            if (midSum <= remain) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return ans;
    }

    public static int AP(int[] banned, int left, int right) {
        return ((right + left) * (right - left + 1)) / 2;
    }

    public static int right(int[] banned, int n, int maxSum) {
        int[] set = new int[10010];
        for (int num : banned) set[num] = 1;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (i > maxSum) break;
            if (set[i] == 0) {
                maxSum -= i;
                ans++;
            }
        }
        return ans;
    }

}
