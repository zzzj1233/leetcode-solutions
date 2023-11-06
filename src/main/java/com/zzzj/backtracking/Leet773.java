package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-11-02 14:37
 */
public class Leet773 {

    public static void main(String[] args) {
//
        System.out.println(slidingPuzzle(LeetUtils.convertInts("[[1,2,3],[4,0,5]]")));

        System.out.println(slidingPuzzle(LeetUtils.convertInts("[[1,2,3],[5,4,0]]")));

        System.out.println(slidingPuzzle(LeetUtils.convertInts("[[4,1,2],[5,0,3]]")));

        System.out.println(slidingPuzzle(LeetUtils.convertInts("[[3,2,4],[1,5,0]]")));

    }

    private static final String EXPECTANCY = "123450";

    public static int slidingPuzzle(int[][] board) {

        int M = board.length;

        int N = board[0].length;

        final int[] DIRS = {
                N,
                -N,
                1,
                -1
        };

        LinkedList<Node> queue = new LinkedList<>();

        queue.addLast(new Node(board, 0));

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {

            Node node = queue.removeFirst();

            String stat = node.stat;

            if (stat.equals(EXPECTANCY))
                return node.step;

            int index = stat.indexOf('0');

            for (int dir : DIRS) {

                int next = index + dir;

                if (index == 2 && next == 3)
                    continue;

                if (index == 3 && next == 2)
                    continue;

                if (next >= 0 && next <= 5) {

                    char[] chars = stat.toCharArray();
                    chars[index] = chars[next];
                    chars[next] = '0';

                    String nextBoard = new String(chars);

                    if (visited.add(nextBoard)) {
                        queue.addLast(new Node(nextBoard, node.step + 1));
                    }

                }

            }


        }

        return -1;
    }

    private static class Node {
        String stat;
        int step;

        public Node(int[][] board, int step) {
            this(
                    new String(new char[]{
                            Character.forDigit(board[0][0], 10),
                            Character.forDigit(board[0][1], 10),
                            Character.forDigit(board[0][2], 10),
                            Character.forDigit(board[1][0], 10),
                            Character.forDigit(board[1][1], 10),
                            Character.forDigit(board[1][2], 10),
                    }),
                    step
            );
        }

        public Node(String stat, int step) {
            this.stat = stat;
            this.step = step;
        }

    }

}
