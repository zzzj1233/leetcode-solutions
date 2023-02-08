package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-01-23 15:55
 */
public class Leet1868 {

    public static void main(String[] args) {
        System.out.println(findRLEArray(
                LeetUtils.convertInts("[[1,3],[2,3]]"),
                LeetUtils.convertInts("[[6,3],[3,3]]")
        ));

        System.out.println(findRLEArray(
                LeetUtils.convertInts("[[1,3],[2,1],[3,2]]"),
                LeetUtils.convertInts("[[2,3],[3,3]]")
        ));
    }

    public static List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {

        int l1 = 0;

        int l2 = 0;

        int lastNum = -1;

        LinkedList<List<Integer>> ans = new LinkedList<>();
        ans.add(Arrays.asList(-1, -1));

        int N = encoded1.length;

        int M = encoded2.length;

        int count = 0;

        while (l1 < N) {
            int[] en1 = encoded1[l1];
            int[] en2 = encoded2[l2];

            if (en1[0] * en2[0] != ans.peekLast().get(0)) {
                ans.peekLast().set(1, count);
                ans.add(Arrays.asList(en1[0] * en2[0], 0));
                count = 0;
            }

            int min = Math.min(en1[1], en2[1]);

            count += min;

            en1[1] = Math.max(0, en1[1] - min);
            en2[1] = Math.max(0, en2[1] - min);

            if (en1[1] == 0) {
                l1++;
            }

            if (en2[1] == 0) {
                l2++;
            }
        }

        ans.peekLast().set(1, count);
        ans.removeFirst();

        return ans;
    }


}
