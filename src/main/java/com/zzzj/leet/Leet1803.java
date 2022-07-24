package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-24 17:44
 */
public class Leet1803 {

    public static void main(String[] args) {
        System.out.println(countPairs(new int[]{3, 2, 2}, 1, 1));
//        System.out.println(countPairs(new int[]{9, 8, 4, 2, 1}, 5, 14));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 1, 1000);
            int high = LeetUtils.random.nextInt(1000) + 1;
            int low = LeetUtils.random.nextInt(high) + 1;
//
//            int[] arr = ArrayUtil.generateArray(3, 1, 5);
//            int high = LeetUtils.random.nextInt(5) + 1;
//            int low = LeetUtils.random.nextInt(high) + 1;

            if (countPairs(arr, low, high) != right(arr, low, high)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(low);
                System.out.println(high);
                System.out.println("myAns    = " + countPairs(arr, low, high));
                System.out.println("rightAns = " + right(arr, low, high));
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int countPairs(int[] nums, int low, int high) {
        Trie root = new Trie();

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            Trie node = root;

            int end = 0;

            for (int j = 15; j >= 0; j--) {
                if (((num >> j) & 1) == 1) {
                    end = j;
                    break;
                }
            }

            for (int j = 0; j < 15; j++) {
                boolean one = ((num >> j) & 1) == 1;
                if (one) {
                    if (node.one == null) {
                        node.one = new Trie();
                    }
                    node = node.one;
                } else {
                    if (node.zero == null) {
                        node.zero = new Trie();
                    }
                    node = node.zero;
                }
                if (end == j) {
                    node.count++;
                }
            }

            ans += dfs(num, 0, root, low, high, 0);
        }


        return ans;
    }

    public static int dfs(int num, int index, Trie root, int low, int high, int cur) {
        if (index >= 15) {
            return 0;
        }

        // 0100
        // 0010
        // 0110
        boolean one = ((num >> index) & 1) == 1;

        int cleared = ((num >> index) << index) | cur;

        int result = root.count > 0 && cleared >= low && cleared <= high ? root.count : 0;

        if (one) {
            if (root.zero != null && (cur | 1 << index) <= high) {
                result += dfs(num, index + 1, root.zero, low, high, (cur | 1 << index));
            }
            if (root.one != null) {
                result += dfs(num, index + 1, root.one, low, high, cur);
            }
        } else {
            if (root.one != null && (cur | 1 << index) <= high) {
                result += dfs(num, index + 1, root.one, low, high, (cur | 1 << index));
            }
            if (root.zero != null) {
                result += dfs(num, index + 1, root.zero, low, high, cur);
            }
        }

        return result;
    }

    private static class Trie {
        Trie zero;
        Trie one;
        int count;
    }

    public static int right(int[] nums, int low, int high) {
        int ans = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;
        int[] freq = new int[20001];
        for (int num : nums) {
            freq[num]++;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        for (int j = min; j <= max; j++) {
            if (freq[j] == 0) continue;
            for (int i = low; i <= high; i++) {
                int xor = i ^ j;
                if (xor >= 1 && xor <= 20000) {
                    ans += freq[j] * freq[xor];
                }
            }
        }
        return ans >> 1;
    }


}
