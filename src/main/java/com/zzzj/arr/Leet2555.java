package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.Unresolved;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2023-04-20 15:28
 */
@Unresolved
public class Leet2555 {


    public static void main(String[] args) {

//        // 0 1 2 3 4 5
//        System.out.println(maximizeWin(
//                new int[]{1, 1, 2, 2, 3, 3, 5},
//                2
//        ));
//
//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int k = 10;

            int[] arr = ArrayUtil.generateArray(k, 0, 5);

            Arrays.sort(arr);

            int times = LeetUtils.random.nextInt(k);

            if (maximizeWin(arr, times) != right(arr, times)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(times);
                System.out.println("==========================================");
                System.out.println(maximizeWin(arr, times));
                System.out.println("right ans: ");
                System.out.println(right(arr, times));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int maximizeWin(int[] prizePositions, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int prizePosition : prizePositions)
            map.put(prizePosition, map.getOrDefault(prizePosition, 0) + 1);

        int N = map.size();

        int[] prefix = new int[N + 1];

        

        return -1;
    }


    public static int right(int[] prizePositions, int k) {
        int left = 0, right = 0, res = 0;
        int[] sum = new int[prizePositions.length + 1];
        for (; right < prizePositions.length; ++right) {
            //如果此时线段长度已经超过了k，就将将left向前移动，直到线段长度<=k
            while (prizePositions[right] - prizePositions[left] > k) left++;
            //更新一下答案
            res = Math.max(res, right - left + 1 + sum[left]);
            //更新一下sum数组
            sum[right + 1] = Math.max(sum[right], right - left + 1);
        }
        return res;
    }

}
