package com.zzzj.leet;


/**
 * @author zzzj
 * @create 2023-10-03 22:59
 */
public class Leet1803 {

    public static void main(String[] args) {

        System.out.println(countPairs(new int[]{1, 4, 2, 7}, 2, 6));

        System.out.println(countPairs(new int[]{9, 8, 4, 2, 1}, 5, 14));

    }

    public static int countPairs(int[] nums, int low, int high) {

        Trie root = new Trie();

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            int cnt = search(nums[i], root, high) - search(nums[i], root, low - 1);

            ans += cnt;

            append(nums[i], root);
        }

        return ans;
    }

    public static int search(int num, Trie root, int high) {

        int limit = 31;

        for (; limit >= 0; limit--) {
            if ((num & (1 << limit)) != 0 || ((high) & (1 << limit)) != 0)
                break;
            root = root.zero;
            if (root == null)
                return 0;
        }


        int cnt = 0;

        for (int i = limit; i >= 0 && root != null; i--) {

            if ((high & (1 << i)) != 0) {

                // high :  xxx1xxx
                //  num :  xxx0xxx
                // trie :  xxx0ccc
                if ((num & (1 << i)) == 0) {

                    cnt += root.zero == null ? 0 : root.zero.cnt;

                    root = root.one;
                } else {
                    // high :  xxx1xxx
                    //  num :  xxx1xxx
                    // trie :  xxx1ccc
                    cnt += root.one == null ? 0 : root.one.cnt;

                    root = root.zero;
                }

            } else {
                // high :  xxx0xxx
                //  num :  xxx0xxx
                if ((num & (1 << i)) == 0) {
                    root = root.zero;
                } else {
                    root = root.one;
                }
            }

        }

        return (root == null ? 0 : root.cnt) + cnt;
    }

    public static void append(int num, Trie root) {
        int limit = 31;

        for (; limit >= 0; limit--) {
            if ((num & (1 << limit)) != 0)
                break;
        }

        for (int i = 31; i >= 0; i--) {
            if ((num & (1 << i)) == 0) {
                if (root.zero == null)
                    root.zero = new Trie();
                root = root.zero;
            } else {
                if (root.one == null)
                    root.one = new Trie();
                root = root.one;
            }
            root.cnt++;
        }

    }

    private static class Trie {
        Trie zero;
        Trie one;
        int cnt;
    }

}
