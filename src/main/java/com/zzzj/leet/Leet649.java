package com.zzzj.leet;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * @author Zzzj
 * @create 2022-07-31 22:12
 */
public class Leet649 {

    public static void main(String[] args) {

        System.out.println(predictPartyVictory("RD"));
        System.out.println(predictPartyVictory("RDD"));

//        System.exit(0);

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

    public static final String R = "Radiant";

    public static final String D = "Dire";

    public static String predictPartyVictory(String senate) {

        int N = senate.length();

        TreeSet<Integer> dset = new TreeSet<>();

        TreeSet<Integer> rset = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            if (senate.charAt(i) == 'R') rset.add(i);
            else dset.add(i);
        }

        while (!dset.isEmpty() && !rset.isEmpty()) {

            for (int i = 0; i < N; i++) {

                if (senate.charAt(i) == 'R') {

                    if (!rset.contains(i)) continue;

                    Integer ceiling = dset.ceiling(i);

                    if (ceiling == null) dset.pollFirst();
                    else dset.remove(ceiling);

                } else {

                    if (!dset.contains(i)) continue;

                    Integer ceiling = rset.ceiling(i);

                    if (ceiling == null) rset.pollFirst();
                    else rset.remove(ceiling);

                }

            }

        }


        return dset.isEmpty() ? R : D;
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
