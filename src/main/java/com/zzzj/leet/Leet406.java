package com.zzzj.leet;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-05-19 14:43
 */
public class Leet406 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(reconstructQueue(LeetUtils.convertInts("[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]"))));
    }

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < people.length; i++) {
            queue.add(people[i][1], people[i]);
        }

        return queue.toArray(new int[people.length][]);
    }

}

