package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-28 17:44
 */
public class Leet37 {

    public static void main(String[] args) {
        final char[][] chars = LeetUtils.convertChars("[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"],[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"],[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"],[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"],[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"],[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"],[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"],[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]");

        solveSudoku(chars);

        System.out.println(Arrays.deepToString(chars));
    }

    public static Map<Integer, Set<Integer>> ROW;

    public static Map<Integer, Set<Integer>> COL;

    public static Map<Integer, Set<Integer>> BLOCK;

    public static int blockIndex(int i, int j) {
        return (i / 3) * 3 + j / 3;
    }

    public static void solveSudoku(char[][] board) {

        // 数字 1-9 在每一行只能出现一次。
        // 数字 1-9 在每一列只能出现一次。
        // 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
        List<Integer> allow = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        ROW = new HashMap<>(18);
        COL = new HashMap<>(18);
        BLOCK = new HashMap<>(18);


        for (int i = 0; i < 9; i++) {
            BLOCK.put(i, new HashSet<>(allow));
        }

        // 初始化ROW和COL
        for (int i = 0; i < board.length; i++) {
            ROW.put(i, new HashSet<>(allow));
            COL.put(i, new HashSet<>(allow));

            Set<Integer> rowAllow = ROW.get(i);
            Set<Integer> colAllow = COL.get(i);

            for (int j = 0; j < board[i].length; j++) {
                Set<Integer> blockAllow = BLOCK.get(blockIndex(i, j));

                if (board[i][j] != '.') {
                    rowAllow.remove(Character.digit(board[i][j], 10));
                }
                if (board[j][i] != '.') {
                    colAllow.remove(Character.digit(board[j][i], 10));
                }
                // 初始化BLOCK
                if (blockIndex(i, j) != '.') {
                    blockAllow.remove(Character.digit(board[i][j], 10));
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    process(board, i, j);
                    return;
                }
            }
        }
    }

    public static boolean process(char[][] board, int i, int j) {

        char c = board[i][j];

        int bi = blockIndex(i, j);

        Set<Integer> rowAllow = ROW.get(i);
        Set<Integer> colAllow = COL.get(j);
        Set<Integer> blockAllow = BLOCK.get(bi);

        boolean into = false;

        OUTER:
        for (int k = 1; k < 10; k++) {
            into = false;

            board[i][j] = Character.forDigit(k, 10);

            if (!rowAllow.contains(k) || !colAllow.contains(k) || !blockAllow.contains(k)) {
                continue;
            }

            rowAllow.remove(k);
            colAllow.remove(k);
            blockAllow.remove(k);

            for (int l = 0; l < board.length; l++) {

                for (int m = 0; m < board[l].length; m++) {
                    if (board[l][m] == '.') {
                        if (!process(board, l, m)) {
                            board[l][m] = '.';
                            rowAllow.add(k);
                            colAllow.add(k);
                            blockAllow.add(k);
                            continue OUTER;
                        }
                    }
                }

            }

            into = true;
        }

        return into;
    }


}
