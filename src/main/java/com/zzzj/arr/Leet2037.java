package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-05-12 16:14
 */
public class Leet2037 {

    public static int minMovesToSeat(int[] seats, int[] students) {

        Arrays.sort(seats);
        Arrays.sort(students);

        int N = students.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            ans += Math.abs(students[i] - seats[i]);
        }

        return ans;
    }

}
