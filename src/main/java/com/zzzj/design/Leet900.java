package com.zzzj.design;

public class Leet900 {

    public static void main(String[] args) {

        RLEIterator it = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5});

        System.out.println(it.next(5));
        System.out.println(it.next(1));
        System.out.println(it.next(1));
        System.out.println(it.next(2));
    }

    private static class RLEIterator {

        private final int[] encoding;

        private int index;

        private int curNum;

        private int curCount;

        public RLEIterator(int[] encoding) {
            this.encoding = encoding;
        }

        public int next(int n) {
            while (curCount - n < 0) {
                if (index >= encoding.length) {
                    curCount = 0;
                    return -1;
                }
                curCount += encoding[index];
                index++;
                curNum = encoding[index];
                index++;
            }
            curCount -= n;
            return curNum;
        }

    }

}
