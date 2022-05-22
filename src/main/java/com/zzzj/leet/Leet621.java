package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-05-22 16:38
 */
public class Leet621 {

    public static void main(String[] args) {
        // System.out.println(leastInterval(LeetUtils.convertChars1("[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"G\"]"), 2));
        System.out.println(leastInterval(LeetUtils.convertChars1("[\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"]"), 2));
    }

    public static int leastInterval(char[] tasks, int free) {
        int N = tasks.length;

        int maxCount = 0;

        int[] table = new int[256];

        for (int i = 0; i < tasks.length; i++) {
            maxCount = Math.max(maxCount, ++table[tasks[i]]);
        }

        int maxTaskCount = 0;

        for (int i = 0; i < table.length; i++) {
            if (table[i] == maxCount) {
                maxTaskCount++;
            }
        }

        // 剩余任务数量
        int remainTaskCount = N - (maxTaskCount * maxCount);

        if (remainTaskCount == 0 && maxTaskCount > free) {
            return N;
        }

        // 剩余空间
        int freeSpace = (free - maxTaskCount + 1) * (maxCount - 1);

        return Math.max(N, N + freeSpace - remainTaskCount);
    }

}
