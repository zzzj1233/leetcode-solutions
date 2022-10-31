package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

public class Leet1947 {


    public static void main(String[] args) {
        int[][] s = LeetUtils.convertInts("[[0,0,1,1,1,0,1],[0,1,1,0,0,0,0],[0,0,1,1,1,1,1],[0,1,0,0,1,0,1],[1,0,1,1,1,1,1]]");

        int[][] m = LeetUtils.convertInts("[[0,1,1,0,0,0,0],[0,1,0,0,0,0,1],[0,1,0,1,0,0,1],[1,0,0,0,1,0,1],[1,1,1,1,1,0,0]]");

        System.out.println(maxCompatibilitySum(s, m));
    }

    public static int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int N = students.length;

        int[][] score = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                score[i][j] = score(students[i], mentors[j]);
            }
        }

        return dfs(score, new boolean[N], 0);
    }

    public static int dfs(int[][] score, boolean[] used, int index) {

        int result = 0;

        int N = used.length;

        for (int j = 0; j < N; j++) {
            if (used[j]) {
                continue;
            }
            used[j] = true;
            result = Math.max(result, score[index][j] + dfs(score, used, index + 1));
            used[j] = false;
        }

        return result;
    }

    public static int score(int[] student, int[] mentor) {
        int result = 0;
        for (int i = 0; i < student.length; i++) {
            if (student[i] == mentor[i]) {
                result++;
            }
        }
        return result;
    }

}
