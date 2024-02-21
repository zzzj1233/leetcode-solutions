package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2024-02-23 15:55
 */
public class Leet1707 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(maximizeXor(new int[]{0, 1, 2, 3, 4}, LeetUtils.convertInts("[[3,1],[1,3],[5,6]]"))));

    }

    static final int INDEX_XI = 0;

    static final int INDEX_MI = 1;

    static final int INDEX_IDX = 2;

    public static int[] maximizeXor(int[] nums, int[][] queries) {

        int N = nums.length;

        int M = queries.length;

        int[][] combined = new int[M][3];

        for (int i = 0; i < M; i++) {
            combined[i][INDEX_XI] = queries[i][INDEX_XI];
            combined[i][INDEX_MI] = queries[i][INDEX_MI];
            combined[i][INDEX_IDX] = i;
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[INDEX_MI]));

        Arrays.sort(nums);

        Trie root = new Trie();

        int[] ans = new int[M];

        for (int i = 0, j = 0; i < M; i++) {

            int num = combined[i][INDEX_MI];

            while (j < N && nums[j] <= num) {
                append(nums[j], root);
                j++;
            }

            ans[combined[i][INDEX_IDX]] = search(combined[i][INDEX_XI], root);
        }

        return ans;
    }

    public static int search(int num, Trie root) {

        Trie node = root;

        int res = 0;

        for (int i = 31; i >= 0 && node != null; i--) {

            if ((num & (1 << i)) != 0) {
                if (node.zero != null) {
                    res |= 1 << i;
                    node = node.zero;
                } else {
                    node = node.one;
                }
            } else {
                if (node.one != null) {
                    res |= 1 << i;
                    node = node.one;
                } else {
                    node = node.zero;
                }
            }

        }

        return res == 0 ? -1 : res;
    }

    public static void append(int num, Trie root) {

        Trie node = root;

        for (int i = 31; i >= 0; i--) {

            if ((num & (1 << i)) != 0) {
                if (node.one == null)
                    node.one = new Trie();
                node = node.one;
            } else {
                if (node.zero == null)
                    node.zero = new Trie();
                node = node.zero;
            }

        }

    }

    private static class Trie {
        Trie one;
        Trie zero;
    }

}
