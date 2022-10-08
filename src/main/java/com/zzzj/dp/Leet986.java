package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-10-08 17:17
 */
public class Leet986 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(intervalIntersection(LeetUtils.convertInts("[[3,5],[9,12]]"), LeetUtils.convertInts("[[5,8],[9,12]]"))));
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;

        while (index1 < firstList.length && index2 < secondList.length) {
            int[] list1 = firstList[index1];
            int[] list2 = secondList[index2];

            int l1Start = list1[0];
            int l1End = list1[1];

            int l2Start = list2[0];
            int l2End = list2[1];

            if (l2Start > l1End) {
                index1++;
            } else if (l1Start > l2End) {
                index2++;
            } else {
                list.add(new int[]{Math.max(l1Start, l2Start), Math.min(l1End, l2End)});
                if (l1End == l2End) {
                    index1++;
                    index2++;
                } else if (l1End < l2End) {
                    index1++;
                } else {
                    index2++;
                }
            }
        }

        int size = list.size();

        int[][] ans = new int[size][];

        for (int i = 0; i < size; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

}
