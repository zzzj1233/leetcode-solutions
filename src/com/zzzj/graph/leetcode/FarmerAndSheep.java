package com.zzzj.graph.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zzzj
 * @create 2021-05-03 15:18
 */
public class FarmerAndSheep {

    private final boolean[] visited = new boolean[255];

    private final char[] pre = new char[255];

    private static final char START = 0b11110000;

    private static final char END = 0b00001111;

    public void solution() {
        // 1 = 农夫
        // 2 = 狼
        // 3 = 羊
        // 4 = 草

        // 高四位 = 河左边 , 低四位 = 河右边
        // 11110000

        // 狼吃羊
        // 011x

        // 羊吃草
        // 0x11
        bfs();
    }

    private void bfs() {
        Queue<Character> queue = new LinkedList<>();
        queue.add(START);

        while (!queue.isEmpty()) {
            Character first = queue.remove();
            visited[first] = true;
            // 每次变更,第一位要么在左要么在右,同时可以携带当前边的任意一位,总共有6种可能
            ArrayList<Character> changes = new ArrayList<>(3);

            // 农夫在左边
            if ((first & 0b10000000) == 0b10000000) {
                // 带蛇去右边
                changes.add((char) ((first & 0b00111111) | 0b00001100));
                // 带羊去右边
                changes.add((char) ((first & 0b01011111) | 0b00001010));
                // 带草去右边
                changes.add((char) ((first & 0b01101111) | 0b00001001));
                // 农夫在右边
            } else {
                // 带蛇去左边
                changes.add((char) ((first & 0b11110011) | 0b11000000));
                // 带羊去左边
                changes.add((char) ((first & 0b11110101) | 0b10100000));
                // 带草去左边
                changes.add((char) ((first & 0b11110110) | 0b10010000));
            }

            for (Character change : changes) {
                if (visited[change]) {
                    continue;
                }
                visited[change] = true;
                pre[change] = first;
                // 达成目标
                if (change == END) {
                    return;
                    // 1. 狼吃羊
                    // 2. 羊吃草
                } else if ((change & 0b00001110) == 0b00000110 ||
                        (change & 0b00001011) == 0b00000011 ||
                        (change & 0b11100000) == 0b01100000 ||
                        (change & 0b10110000) == 0b00110000
                ) {
                    continue;
                } else {
                    queue.add(change);
                }
            }
        }
    }

    public Collection<Integer> getPre() {
        List<Integer> list = new LinkedList<>();
        int cur = END;
        while (cur != START) {
            list.add(cur);
            cur = pre[cur];
        }
        list.add((int) START);
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) {
        FarmerAndSheep farmerAndSheep = new FarmerAndSheep();
        farmerAndSheep.solution();
        System.out.println(farmerAndSheep.getPre());
    }

}
