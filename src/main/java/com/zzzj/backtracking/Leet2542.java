package com.zzzj.backtracking;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-03-30 10:51
 */
public class Leet2542 {

    public static void main(String[] args) {
//        System.out.println(patternMatching("abba", "dogcatcatdog"));
//        System.out.println(patternMatching("abba", "dogcatcatfish"));
//        System.out.println(patternMatching("aaaa", "dogcatcatdog"));
        System.out.println(patternMatching("abba", "dogdogdogdog"));

//        System.out.println(patternMatching("abbaa", "dogdogdogdogdog"));
    }


    static class Pair {
        public int startIdx;
        public int endIdx;
        public int len;

        public Pair(int startIdx, int endIdx) {
            this.reset(startIdx, endIdx);
        }

        public void reset(int startIdx, int endIdx) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.len = endIdx - startIdx;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "startIdx=" + startIdx +
                    ", endIdx=" + endIdx +
                    ", len=" + len +
                    '}';
        }
    }

    public static boolean ans;

    public static boolean patternMatching(String pattern, String value) {
        ans = false;

        dfs(pattern.toCharArray(), value.toCharArray(), 0, 0, new Pair[26]);

        return ans;
    }

    public static boolean isRepeat(Pair[] path, char[] value, int index, Pair compare) {
        for (int i = 0; i < path.length; i++) {
            if (i == index) {
                continue;
            }
            Pair pair = path[i];
            if (pair != null && pair.len == compare.len) {
                int start = pair.startIdx;
                int end = pair.endIdx;
                int j = compare.startIdx;
                while (start < end) {
                    if (value[start] != value[j]) {
                        break;
                    }
                    start++;
                    j++;
                }
                if (start == end) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void dfs(char[] pattern, char[] value, int patIndex, int valIndex, Pair[] path) {
        if (ans) {
            return;
        }

        if (valIndex >= value.length) {
            ans = patIndex >= pattern.length;
            if (ans) {
                System.out.println(Arrays.toString(path));
            }
            return;
        }

        if (patIndex >= pattern.length) {
            return;
        }

        char c = pattern[patIndex];

        int index = c - 'a';

        Pair pat = path[index];
        // 字母第一次出现
        if (pat == null) {
            // 尝试
            for (int i = valIndex, j = 0; i < value.length - 1 && !ans; i++, j++) {
                if (pat == null) {
                    pat = new Pair(valIndex, valIndex + j);
                    path[index] = pat;
                } else {
                    pat.reset(valIndex, valIndex + j);
                }
                // pat的字符串不能重复
                if (isRepeat(path, value, index, pat)) {
                    path[index] = null;
                    continue;
                }
                dfs(pattern, value, patIndex + 1, valIndex + j + 1, path);
            }
            path[index] = null;
        } else {
            // 已经出现过了
            // 判断当前单词是否满足
            if (value.length - valIndex < pat.len) {
                return;
            }
            for (int i = 0; i < pat.len; i++) {
                if (value[i + valIndex] != value[pat.startIdx + i]) {
                    return;
                }
            }
            dfs(pattern, value, patIndex + 1, valIndex + pat.len, path);
        }
    }

}
