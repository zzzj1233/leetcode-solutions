package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-03-02 17:01
 */
public class Leet1703 {

    // [1,0,0,1,0,1,1,1,0,1,1]
    // 7
    // 9 和 6
    public static void main(String[] args) {

//        System.out.println(right(new int[]{1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1}, 7));
//        System.out.println(minMoves(new int[]{1, 1, 1, 0, 1, 0, 1}, 5));
//        System.out.println(right(new int[]{1, 1, 1, 0, 1, 0, 1}, 5));
//
//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int K = 1000;

            int[] coins = ArrayUtil.generateArray(K, 0, 2);

            ArrayCopyIterator it = new ArrayCopyIterator(coins);

            int k = LeetUtils.random.nextInt(K);

            if (minMoves(it.next(), k) != right(it.next(), k)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(it.next()));
                System.out.println(k);
                System.out.println("My ans : " + minMoves(it.next(), k));
                System.out.println(right(it.next(), k));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int minMoves(int[] coins, int k) {

        int N = coins.length;

        int cur = 0;

        int left = 0;
        int right = 0;

        int ans = Integer.MAX_VALUE;

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + (coins[i - 1] == 1 ? 1 : 0);
        }

        int[] leftZC = new int[N];
        int[] rightZC = new int[N];

        leftZC[0] = coins[0];

        for (int i = 1; i < N; i++) {
            leftZC[i] = leftZC[i - 1] + coins[i];
        }

        rightZC[N - 1] = coins[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            rightZC[i] = rightZC[i + 1] + coins[i];
        }

        // 1. 已经窗口内的1的个数刚好 == k
        // 2. 窗口内的每个0, cost += min(leftOne, rightOne)


        long zcl = 0;
        int zclc = 0;

        long zcr = 0;
        int zcrc = 0;

        while (right < N) {
            if (coins[right] == 1) {
                cur++;

                while (cur > k) { // 缩小窗口
                    if (coins[left] == 1) {
                        cur--;
                    } else {
                        if (leftZC[left] <= rightZC[left]) {
                            zcl -= leftZC[left];
                            zclc--;
                        } else {
                            zcr -= rightZC[left];
                            zcrc--;
                        }
                    }

                    left++;
                }

                while (left < N && coins[left] == 0) {
                    if (leftZC[left] <= rightZC[left]) {
                        zcl -= leftZC[left];
                        zclc--;
                    } else {
                        zcr -= rightZC[left];
                        zcrc--;
                    }
                    left++;
                }

                if (cur == k) {

                    int leftZeroCount = left - 1 < 0 ? 0 : leftZC[left - 1];
                    int rightZeroCount = right + 1 == N ? 0 : rightZC[right + 1];

                    int cost = (int) ((zcl - zclc * leftZeroCount) + (zcr - zcrc * rightZeroCount));

                    ans = Math.min(ans, cost);
                }

            } else {
                if (leftZC[right] <= rightZC[right]) {
                    zcl += leftZC[right];
                    zclc++;
                } else {
                    zcr += rightZC[right];
                    zcrc++;
                }
            }

            right++;
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static int right(int[] nums, int k) {
        int n = nums.length, l = 0, idx = 0;
        int res = Integer.MAX_VALUE, cnt = 0, t = 1, mid = k >> 1;
        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) if (nums[i] == 1) pos[idx++] = i;
        while (t < k) cnt += (pos[l + t] - pos[l + t - 1] - 1) * Math.min(t, k - t++);//计算第一个窗口大小
        while (l + k <= idx) {
            res = Math.min(res, cnt);
            cnt -= pos[l + mid] - pos[l];
            cnt += pos[l + k] - pos[l + k - mid];
            l++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
