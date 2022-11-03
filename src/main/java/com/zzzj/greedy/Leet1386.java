package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-11-02 17:09
 */
public class Leet1386 {

    public static void main(String[] args) {
        System.out.println(maxNumberOfFamilies(3, LeetUtils.convertInts("[[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]")));
    }

    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // 两组的可能 2345 + 6789
        // 一组的可能 2345 | 4567 | 6789

        Map<Integer, List<Integer>> reversed = new HashMap<>();

        for (int[] reservedSeat : reservedSeats) {
            reversed.computeIfAbsent(reservedSeat[0], integer -> new ArrayList<>())
                    .add(reservedSeat[1]);
        }

        int count = reversed.size();

        int extra = 0;

        final int EMPTY = 0b1111111111;

        final int TWO = 0b0111111110;

        final int ONW1 = 0b0111100000;

        final int ONW2 = 0b0001111000;

        final int ONW3 = 0b0000011110;

        for (List<Integer> seats : reversed.values()) {
            // 看看这一行最多能坐几个人
            int info = EMPTY;

            for (Integer it : seats) {
                info &= ~(1 << (it - 1));
            }

            // System.out.println(LeetUtils.toBinaryString(info));

            if ((info & TWO) == TWO) {
                extra += 2;
            } else if ((info & ONW1) == ONW1) {
                extra += 1;
            } else if ((info & ONW2) == ONW2) {
                extra += 1;
            } else if ((info & ONW3) == ONW3) {
                extra += 1;
            }
        }

        return ((n - count) << 1) + extra;
    }

}
