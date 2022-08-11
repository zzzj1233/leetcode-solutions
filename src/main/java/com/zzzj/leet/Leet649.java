package com.zzzj.leet;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Zzzj
 * @create 2022-07-31 22:12
 */
public class Leet649 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString(100, "RD");

            if (!predictPartyVictory(str).equals(right(str))) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(predictPartyVictory(str));
                System.out.println(right(str));
                System.out.println(i);
                return;
            }
        }

        System.out.println("Ok");
    }

    public static String predictPartyVictory(String senate) {

        boolean[] removed = new boolean[senate.length()];

        LinkedList<Integer> list = new LinkedList<>();

        list.add(0);

        Integer rIndex = senate.indexOf('R');
        Integer dIndex = senate.indexOf('D');

        while (list.size() > 1) {
            int index = list.peekFirst();

            if (index >= senate.length()) {
                list.removeLast();
                break;
            }

            if (removed[index]) {
                list.removeLast();
                list.add(index + 1);
                continue;
            }

            char c = senate.charAt(index);

            // 可以删除下一个D
            if (c == 'R') {
                if (dIndex >= 0) {
                    removed[dIndex] = true;
                    dIndex = senate.indexOf('D', dIndex + 1);
                    list.remove(dIndex);
                }
            } else {
                if (rIndex >= 0) {
                    removed[rIndex] = true;
                    rIndex = senate.indexOf('R', rIndex + 1);
                    list.remove(rIndex);
                }
            }

            list.add(index + 1);
        }

        return senate.charAt(list.peekLast()) == 'R' ? "Radiant" : "Dire";
    }


    public static String right(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<Integer>();
        Queue<Integer> dire = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }

}
