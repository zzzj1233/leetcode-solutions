package com.zzzj.matrix;

import com.zzzj.leet.LeetUtils;

import java.util.function.Consumer;

/**
 * @author zzzj
 * @create 2023-06-19 17:56
 */
public class Leet1958 {

    public static void main(String[] args) {
//        System.out.println(checkMove(LeetUtils.convertChars("[[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\"W\",\"B\",\"B\",\".\",\"W\",\"W\",\"W\",\"B\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"]]"), 4, 3, 'B'));

        System.out.println(checkMove(LeetUtils.convertChars("[[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\".\",\"B\",\".\",\".\",\"W\",\".\",\".\",\".\"],[\".\",\".\",\"W\",\".\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\"B\",\".\",\".\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\".\",\"B\",\"W\",\".\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\"W\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\"B\"]]"), 4, 4, 'W'));
    }

    public static boolean checkMove(char[][] board, int rMove, int cMove, char color) {

        return can(board, rMove, cMove, color, ints -> ints[0]++)
                ||
                can(board, rMove, cMove, color, ints -> ints[0]--)
                ||
                can(board, rMove, cMove, color, ints -> ints[1]++)
                ||
                can(board, rMove, cMove, color, ints -> ints[1]--)
                ||
                can(board, rMove, cMove, color, ints -> {
                    ints[0]++;
                    ints[1]--;
                })
                ||
                can(board, rMove, cMove, color, ints -> {
                    ints[0]++;
                    ints[1]++;
                })
                ||
                can(board, rMove, cMove, color, ints -> {
                    ints[0]--;
                    ints[1]++;
                })
                ||
                can(board, rMove, cMove, color, ints -> {
                    ints[0]--;
                    ints[1]--;
                });
    }

    public static boolean can(char[][] board, int x, int y, char color, Consumer<int[]> move) {

        int[] p = {x, y};

        move.accept(p);

        int distance = 1;

        while (p[0] >= 0 && p[1] >= 0 && p[0] < board.length && p[1] < board[x].length) {

            char c = board[p[0]][p[1]];

            if (c == color) {
                return distance >= 3;
            }

            if (c == '.') return false;

            distance++;
            move.accept(p);
        }

        return false;
    }


}
