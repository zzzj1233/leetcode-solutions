package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.CopyIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-29 11:19
 */
public class Leet765 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int N = 100;
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = j;
            }
            LeetUtils.shuffle(arr);

            CopyIterator iterator = CopyIterator.fromArray(arr);

            if (minSwapsCouples(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println(minSwapsCouples(iterator.next()));
                System.out.println(right(iterator.next()));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int minSwapsCouples(int[] row) {
        int N = row.length;

        int ans = 0;

        for (int i = 1; i < N; i += 2) {
            int prev = row[i - 1];
            int cur = row[i];

            int index;
            if (prev % 2 == 0) { // 找大的
                if (cur == prev + 1) {
                    continue;
                } else {
                    index = findIndex(row, i + 1, prev + 1);
                }
            } else { // 找小的
                if (cur == prev - 1) {
                    continue;
                } else {
                    index = findIndex(row, i + 1, prev - 1);
                }
            }
            ans++;
            swap(row, i, index);
        }

        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int findIndex(int[] row, int start, int target) {
        for (int i = start; i < row.length; i++) {
            if (row[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int right(int[] row) {
        int res = 0;
        for (int i = 0; i < row.length; i += 2) {
            int cp1 = row[i];
            //确定cp_1对应的（男/女）朋友的值
            int cp2 = cp1 % 2 == 0 ? cp1 + 1 : cp1 - 1;
            //判断cp1右边的人是不是他的情侣
            if (row[i + 1] == cp2) {
                continue;
            }
            //否则从下下个人开始往后寻找cp1的情侣，找到后和row[i+1]交换
            for (int j = i + 2; j < row.length; j++) {
                if (row[j] == cp2) {
                    row[j] = row[i + 1];
                    row[i + 1] = cp2;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

}
