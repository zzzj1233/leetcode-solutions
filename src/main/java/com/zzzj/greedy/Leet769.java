package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-29 12:01
 */
public class Leet769 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int N = 10;
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = j;
            }
            LeetUtils.shuffle(arr);

            ArrayCopyIterator arrayCopyIterator = ArrayCopyIterator.fromArray(arr);

            if (maxChunksToSorted(arrayCopyIterator.next()) != right(arrayCopyIterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arrayCopyIterator.next()));
                System.out.println(maxChunksToSorted(arrayCopyIterator.next()));
                System.out.println(right(arrayCopyIterator.next()));
                return;
            }
        }
        System.out.println("Ok");
    }

    // [0,2,1]
    public static int maxChunksToSorted(int[] arr) {
        int N = arr.length;

        int ans = 0;

        int[] min = new int[N];

        min[N - 1] = N - 1;

        for (int i = N - 2; i >= 0; i--) {
            if (arr[i] < arr[min[i + 1]]) {
                min[i] = i;
            } else {
                min[i] = min[i + 1];
            }
        }

        // 1. 找到最后一个比当前元素小的位置

        // 2. 如果在过程中更新了Max,那么需要找到最后一个比max小的元素的位置

        for (int i = 0; i < N; i++) {

            ans++;

            if (arr[i] == i) {
                continue;
            }

            int max = arr[i];

            int j = i;

            for (; j < N; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                } else { // < 看看后面还有没有更小的
                    if (j + 1 == N || arr[min[j + 1]] > max) {
                        break;
                    }
                }
            }

            i = j;
        }

        return ans;
    }

    public static int right(int[] arr) {
        //当遍历到第i个位置时，如果可以切分为块，那前i个位置的最大值一定等于i。
        //否则，一定有比i小的数划分到后面的块，那块排序后，一定不满足升序。
        int res = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);//统计前i个位置的最大元素
            if (max == i) res++;
        }
        return res;
    }

}
