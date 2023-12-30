package com.zzzj.contest.week371;

import java.util.Arrays;

public class Q4 {

    public static void main(String[] args) {

        System.out.println(maximumStrongPairXor(new int[]{1, 2, 3, 4, 5}));

        System.out.println(maximumStrongPairXor(new int[]{10, 100}));

        System.out.println(maximumStrongPairXor(new int[]{500, 520, 2500, 3000}));

    }

    public static int maximumStrongPairXor(int[] nums) {

        Arrays.sort(nums);

        Trie root = new Trie();

        int N = nums.length;

        int right = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            while (right < N && nums[right] - nums[i] <= nums[i]) {
                append(root, nums[right]);
                right++;
            }

            ans = Math.max(ans, findMax(root, nums[i]));

            remove(root, nums[i]);
        }

        return ans;
    }

    private static void remove(Trie root, int num) {
        Trie node = root;

        for (int i = 31; i >= 0; i--) {
            if ((num & (1 << i)) != 0) {
                node = node.one;
            } else {
                node = node.zero;
            }
            node.cnt--;
        }
    }

    private static int findMax(Trie root, int num) {

        Trie node = root;

        int res = 0;

        for (int i = 31; i >= 0; i--) {

            if ((num & (1 << i)) != 0) {

                if (node.zero != null && node.zero.cnt > 0) {
                    node = node.zero;
                    res |= 1 << i;
                } else {
                    node = node.one;
                }

            } else {
                if (node.one != null && node.one.cnt > 0) {
                    node = node.one;
                    res |= 1 << i;
                } else {
                    node = node.zero;
                }
            }

        }

        return res;
    }

    private static void append(Trie root, int num) {

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
            node.cnt++;
        }

    }

    private static class Trie {
        Trie zero;
        Trie one;
        int cnt;
    }

}
