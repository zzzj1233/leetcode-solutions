package com.zzzj.design;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2022-08-25 19:57
 */
public class Leet855 {

    public static void main(String[] args) {
        ExamRoom room = new ExamRoom(10);

        System.out.println(room.seat());
        System.out.println(room.seat());
        System.out.println(room.seat());

    }

    private static class ExamRoom {

        private final int n;
        int latest = -1;
        TreeSet<Integer> set = new TreeSet<>();

        public ExamRoom(int n) {
            this.n = n;
        }

        public int seat() {
            if (latest == -1) {
                latest = 0;
                set.add(0);
                return latest;
            }

            Integer ceiling = set.ceiling(latest);

            if (ceiling == null) {
                ceiling = n - 1;
            }

            Integer floor = set.floor(latest);

            if (floor == null) {
                floor = 0;
            }

            if (n - 1 - latest > latest - floor) { // latest ~ n
                latest = (n + latest) / 2;
                set.add(latest);
            } else { // 0 ~ latest
                latest = (latest - floor) / 2;
                set.add(latest);
            }

            return latest;
        }


        public void leave(int p) {
            set.remove(p);
        }
    }

}
