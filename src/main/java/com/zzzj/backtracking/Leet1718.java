package com.zzzj.backtracking;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-02-14 15:17
 */
public class Leet1718 {

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            final int[] ans1 = constructDistancedSequence(i);
            final int[] ans2 = backtrack(i);

            if (!Arrays.equals(ans1, ans2)) {
                System.out.println(i);
                return;
            }
        }
    }

    public static int[] constructDistancedSequence(int n) {
        int[] ans = new int[((n - 1) << 1) + 1];

        Arrays.fill(ans, -1);

        process(ans, new boolean[n + 1], n, 0);

        return ans;
    }

    // [6,4,2,5,2,4,6,3,5,1,3]
    public static boolean process(int[] ans, boolean[] visited, int n, int idx) {
        if (idx == ans.length) {
            return true;
        }

        for (int i = n; i >= 1; i--) {

            if (ans[idx] != -1) {
                return process(ans, visited, n, idx + 1);
            }

            if (visited[i]) {
                continue;
            }

            if (i != 1) {
                if (idx + i >= ans.length) {
                    continue;
                }
                if (ans[idx + i] != -1) {
                    continue;
                }
                ans[idx + i] = i;
            }


            ans[idx] = i;

            visited[i] = true;

            if (process(ans, visited, n, idx + 1)) {
                return true;
            }

            ans[idx] = -1;

            if (i != 1) {
                ans[idx + i] = -1;
            }

            visited[i] = false;
        }

        return false;
    }

    public static int[] backtrack(int pos, int n, boolean[] visted, int[] res) {
        // 结束条件
        if (pos == res.length) {
            return res;
        }
        if (res[pos] != -1) {
            return backtrack(pos + 1, n, visted, res);
        }
        for (int i = n; i >= 1; i--) {
            // 是否已经被访问过
            if (visted[i - 1]) {
                continue;
            }
            // 是否越界
            if (i > 1 && pos + i >= res.length) {
                continue;
            }
            // pos+i位置是否已经被使用过
            if (i > 1 && res[pos + i] != -1) {
                continue;
            }
            if (i == 1) {
                res[pos] = i;
            } else {
                res[pos] = res[pos + i] = i;
            }
            visted[i - 1] = true;
            int[] r = backtrack(pos + 1, n, visted, res);
            if (r != null) {
                return r;
            }
            if (i == 1) {
                res[pos] = -1;
            } else {
                res[pos] = res[pos + i] = -1;
            }
            visted[i - 1] = false;
        }
        return null;
    }

    public static int[] backtrack(int n) {
        int[] res = new int[2 * n - 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        backtrack(0, n, new boolean[n], res);
        return res;
    }


}
