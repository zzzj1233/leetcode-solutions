package com.zzzj.contest.week385;

public class Leet3043 {

    public static int longestCommonPrefix(int[] arr1, int[] arr2) {

        Trie root = new Trie();

        for (int num : arr1)
            append(num, root);

        int ans = 0;

        for (int num : arr2)
            ans = Math.max(ans, search(num, root));

        return ans;
    }

    public static int search(int num, Trie root) {

        int res = 0;

        Trie node = root;

        String str = String.valueOf(num);

        for (int i = 0; i < str.length(); i++) {

            int v = Character.digit(str.charAt(i), 10);

            if (node.next[v] == null)
                break;

            node = node.next[v];
            res++;
        }

        return res;
    }

    public static void append(int num, Trie root) {

        Trie node = root;

        String str = String.valueOf(num);

        for (int i = 0; i < str.length(); i++) {

            int v = Character.digit(str.charAt(i), 10);

            if (node.next[v] == null)
                node.next[v] = new Trie();
            node = node.next[v];
        }

    }

    public static class Trie {
        Trie[] next = new Trie[10];
    }

}
