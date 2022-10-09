package com.zzzj.greedy;

import com.zzzj.util.Unresolved;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-09-25 19:42
 */
@Unresolved
public class Leet955 {


    public static void main(String[] args) {
        System.out.println(minDeletionSize(new String[]{"xga", "xfb", "yfa"}));
    }

    public static int minDeletionSize(String[] strs) {

        List<int[]> sameIndexes = new ArrayList<>();

        int M = strs[0].length();

        int N = strs.length;

        sameIndexes.add(new int[]{0, M - 1});


        for (int[] sameIndex : sameIndexes) {

            int start = sameIndex[0];
            int end = sameIndex[1];

            while (start <= end) {

                for (int i = 0; i < N; i++) {
                    String str = strs[i];

                    char c = str.charAt(start);

                    // 如果 c < pre , 那么这行需要被删除
                }

                start++;
            }

        }

        return -1;
    }

}
