package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-08-04 18:33
 */
public class Leet2564 {

    public static void main(String[] args) {

//        System.out.println(Arrays.deepToString(solution("101101", LeetUtils.convertInts("[[0,5],[1,2]]"))));
//
//        System.out.println(Arrays.deepToString(solution("0101", LeetUtils.convertInts("[[12,8]]"))));
//
//        System.out.println(Arrays.deepToString(solution("1", LeetUtils.convertInts("[[4,5]]"))));

        System.out.println(Arrays.deepToString(solution("111010110", LeetUtils.convertInts("[[4,2],[3,3],[6,4],[9,9],[10,28],[0,470],[5,83],[10,28],[8,15],[6,464],[0,3],[5,8],[7,7],[8,8],[6,208],[9,15],[2,2],[9,95]]"))));

    }

    public static int[][] solution(String s, int[][] queries) {

        Map<Integer, int[]> map = new HashMap<>();

        int firstZero = s.indexOf('0');

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '0') continue;

            int num = 0;

            int end = Math.min(32, s.length() - i);

            for (int j = 0; j < end; j++) {
                num = (num << 1) | (s.charAt(j + i) - '0');
                map.putIfAbsent(num, new int[]{i, j + i});
            }

        }

        int N = queries.length;

        int[][] ans = new int[N][];

        int[] notFound = {-1, -1};

        for (int i = 0; i < N; i++) {
            int xor = queries[i][0] ^ queries[i][1];
            if (xor == 0) {
                ans[i] = firstZero >= 0 ? new int[]{firstZero, firstZero} : notFound;
            } else {
                ans[i] = map.getOrDefault(xor, notFound);
            }

        }

        return ans;
    }

    public static int[][] substringXorQueries(String s, int[][] queries) {

        Trie root = new Trie();

        int N = s.length();

        int[][] ans = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            ans[i][0] = -1;
            ans[i][1] = -1;
        }

        int zeroIndex = s.indexOf('0');

        for (int i = 0; i < queries.length; i++) {

            int xor = queries[i][0] ^ queries[i][1];

            if (xor == 0) {
                if (zeroIndex >= 0) {
                    ans[i][0] = zeroIndex;
                    ans[i][1] = zeroIndex;
                }
            } else {
                buildTrie(root, xor, i);
            }
        }

        for (int i = 0; i < N; i++) {

            Trie cur = root;

            int end = Math.min(32, N - i);

            if (s.charAt(i) != '1') continue;

            for (int j = 0; j < end && j < N; j++) {

                if (s.charAt(j + i) == '1') {
                    cur = cur.one;
                } else {
                    cur = cur.zero;
                }

                if (cur == null) break;

                if (cur.isEnd()) {

                    for (Integer ansIndex : cur.ansIndex) {
                        ans[ansIndex][0] = i;
                        ans[ansIndex][1] = j + i;
                    }

                    cur.ansIndex = null;

                }

            }

        }

        return ans;
    }

    private static void buildTrie(Trie root, int value, int index) {
        Trie cur = root;

        String binaryString = Integer.toBinaryString(value);

        for (int i = 0; i < binaryString.length(); i++) {

            if (binaryString.charAt(i) == '0') {
                if (cur.zero == null)
                    cur.zero = new Trie();
                cur = cur.zero;
            } else {
                if (cur.one == null)
                    cur.one = new Trie();
                cur = cur.one;
            }

        }

        if (cur.ansIndex == null)
            cur.ansIndex = new ArrayList<>();

        cur.ansIndex.add(index);
    }

    private static class Trie {

        Trie zero;

        Trie one;

        List<Integer> ansIndex;

        public boolean isEnd() {
            return ansIndex != null;
        }

    }

}
