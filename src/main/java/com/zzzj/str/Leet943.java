package com.zzzj.str;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-10-25 11:55
 */
// super hard
public class Leet943 {

    public static void main(String[] args) {

        // zccxccd
        System.out.println(
                shortestSuperstring(new String[]{"catg", "ctaagt", "gcta", "ttca", "atgcatc"})
        );

        System.out.println(
                right(new String[]{"catg", "ctaagt", "gcta", "ttca", "atgcatc"})
        );

        System.exit(0);

        for (int i = 0; i < 1000; i++) {

            int M = 12;

            int K = M;

            Set<String> repeated = new HashSet<>();

            String[] words = new String[M];

            for (int j = 0; j < M; j++) {

                String str = LeetUtils.randomString(K, "abcdefhzxcv");

                while (!repeated.add(str))
                    str = LeetUtils.randomString(K, "abcdefhzxcv");

                words[j] = str;
            }

            String r = shortestSuperstring(words);

            String rr = right(words);

            if (r.length() != rr.length()) {
                System.out.println("Error");
                System.out.printf("r (%d) = %s %n", r.length(), r);
                System.out.printf("rr (%d) = %s %n", rr.length(), rr);
                System.out.println("words = " + LeetUtils.stringsToLeetCode(words));
                return;
            }

        }

        System.out.println("Ok");

    }

    public static String shortestSuperstring(String[] words) {

        int N = words.length;

        // help[i][j] = j作为后缀, i作为前缀: 后/前 缀长度
        int[][] help = new int[N][N];

        for (int i = 0; i < N; i++) {

            String word = words[i];

            for (int x = 0; x < word.length(); x++) {

                String suffix = word.substring(x);

                for (int j = 0; j < N; j++) {

                    if (i == j || help[j][i] != 0)
                        continue;

                    if (words[j].startsWith(suffix))
                        help[j][i] = suffix.length();

                }

            }

        }

        int limit = 1 << N;

        // []: 长度
        // [][]: 状态
        // [][][]: 以words[i]作为"状态"的最后一个单词
        int[][][] dp = new int[N + 1][limit][N];

        for (int i = 1; i <= N; i++)
            for (int j = 0; j < limit; j++)
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++) {

            for (int stat = 1; stat < limit; stat++) {

                if (Integer.bitCount(stat) == i) {

                    for (int x = 0; x < N; x++) {

                        if ((stat & (1 << x)) != 0) {

                            int prevStat = stat ^ (1 << x);

                            for (int preStatEnd = 0; preStatEnd < N; preStatEnd++) {

                                if (dp[i - 1][prevStat][preStatEnd] != Integer.MAX_VALUE) {

                                    dp[i][stat][x] = Math.min(
                                            dp[i][stat][x],
                                            dp[i - 1][prevStat][preStatEnd] + words[x].length()
                                    );

                                }

                            }

                            for (int y = 0; y < N; y++) {

                                if (x == y || (stat & (1 << y)) == 0)
                                    continue;

                                // y作为结尾
                                // x作为开头
                                dp[i][stat][x] = Math.min(
                                        dp[i][stat][x],
                                        dp[i - 1][prevStat][y] + (words[x].length() - help[x][y])
                                );

                            }

                        }

                    }

                }

            }

        }

        // 根据状态回溯字符串
        int len = Arrays.stream(dp[N][limit - 1]).min().orElse(0);

        int found = 0;

        int lastWord = -1;

        String ans = null;

        for (int i = 0; i < dp[N][limit - 1].length; i++) {

            if (dp[N][limit - 1][i] == len) {
                found = 1 << i;
                lastWord = i;
                len -= words[i].length();
                ans = words[i];
                break;
            }

        }

        for (int i = N - 1; i >= 1; i--) {

            for (int stat = 1; stat < limit; stat++) {

                if (Integer.bitCount(stat) == i && (stat & found) == 0) {

                    for (int end = 0; end < N; end++) {

                        if ((found & (1 << end)) == 0) {

                            if (dp[i][stat][end] - help[lastWord][end] == len) {
                                ans = words[end].substring(0, words[end].length() - help[lastWord][end]) + ans;
                                found |= 1 << end;
                                len -= words[end].length() - help[lastWord][end];
                                lastWord = end;
                            }

                        }

                    }

                }

            }

        }

        return ans;
    }


    public static String right(String[] A) {
        int N = A.length;

        // Populate overlaps
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (i != j) {
                    int m = Math.min(A[i].length(), A[j].length());
                    for (int k = m; k >= 0; --k)
                        if (A[i].endsWith(A[j].substring(0, k))) {
                            overlaps[i][j] = k;
                            break;
                        }
                }

        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[1 << N][N];
        int[][] parent = new int[1 << N][N];
        for (int mask = 0; mask < (1 << N); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < N; ++bit)
                if (((mask >> bit) & 1) > 0) {
                    // Let's try to find dp[mask][bit].  Previously, we had
                    // a collection of items represented by pmask.
                    int pmask = mask ^ (1 << bit);
                    if (pmask == 0) continue;
                    for (int i = 0; i < N; ++i)
                        if (((pmask >> i) & 1) > 0) {
                            // For each bit i in pmask, calculate the value
                            // if we ended with word i, then added word 'bit'.
                            int val = dp[pmask][i] + overlaps[i][bit];
                            if (val > dp[mask][bit]) {
                                dp[mask][bit] = val;
                                parent[mask][bit] = i;
                            }
                        }
                }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < N; ++j)
            if (dp[(1 << N) - 1][j] > dp[(1 << N) - 1][p])
                p = j;

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t / 2; ++i) {
            int v = perm[i];
            perm[i] = perm[t - 1 - i];
            perm[t - 1 - i] = v;
        }

        // Fill in remaining words not yet added
        for (int i = 0; i < N; ++i)
            if (!seen[i])
                perm[t++] = i;

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(A[perm[0]]);
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i - 1]][perm[i]];
            ans.append(A[perm[i]].substring(overlap));
        }

        return ans.toString();
    }

}
