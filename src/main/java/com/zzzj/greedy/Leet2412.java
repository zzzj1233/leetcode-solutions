package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-09-05 16:06
 */
public class Leet2412 {

    public static void main(String[] args) {

//        LeetUtils.invokeAndExit(() -> {
//            System.out.println(minimumMoney(LeetUtils.convertInts("[[3, 1], [1, 2], [4, 3], [1, 4], [5, 4]]")));
//        });

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int M = 50;

            int[][] arr = new int[M][2];

            for (int j = 0; j < M; j++) {
                arr[j][0] = LeetUtils.random.nextInt(M) + 1;
                arr[j][1] = LeetUtils.random.nextInt(M) + 1;
            }

            CopyableIterator<int[][]> it = new CopyableIterator<>(arr, ArrayUtil::copy);

            long r1 = minimumMoney(it.next());

            long r2 = right(it.next());

            if (r1 != r2) {
                System.out.println("Error");
                System.out.println("r1 = " + r1);
                System.out.println("r2 = " + r2);
                return;
            }

        }

        System.out.println("Ok");

    }

    private static final int COST = 0;

    private static final int EARNING = 1;

    public static long minimumMoney(int[][] transactions) {

        int N = transactions.length;

        int curMoney = 0;

        int maxCost = 0;

        long ans = 0;

        Arrays.sort(transactions, (o1, o2) -> {
            int diff = o1[EARNING] - o2[EARNING];
            return diff == 0 ? diff : o2[COST] - o1[COST];
        });

        for (int i = 0; i < N; i++) {
            if (transactions[i][EARNING] >= transactions[i][COST])
                maxCost = Math.max(maxCost, transactions[i][COST]);
            else {
                ans += transactions[i][COST] - curMoney;
                curMoney = transactions[i][EARNING];
            }
        }

        return ans + Math.max(0, maxCost - curMoney);
    }

    public static long right(int[][] transactions) {
        int n = transactions.length;
        List<int[]> negative = new ArrayList<>();
        List<int[]> positive = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (transactions[i][1] - transactions[i][0] > 0)
                positive.add(transactions[i]);
            else
                negative.add(transactions[i]);
        }
        negative.sort((x, y) -> x[1] != y[1] ? x[1] - y[1] : y[0] - x[0]);
        positive.sort((x, y) -> x[0] != y[0] ? y[0] - x[0] : x[1] - y[1]);
        int current = 0;
        for (int[] x : negative)
            transactions[current++] = x;
        for (int[] x : positive)
            transactions[current++] = x;
        long res = 0;
        long remain = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                res = transactions[i][0];
                remain = transactions[i][1];
            } else {
                if (remain >= transactions[i][0])
                    remain = remain - transactions[i][0] + transactions[i][1];
                else {
                    res += (long) transactions[i][0] - remain;
                    remain = transactions[i][1];
                }
            }
        }
        return res;
    }

}
