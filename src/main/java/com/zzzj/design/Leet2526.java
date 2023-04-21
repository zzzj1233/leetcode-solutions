package com.zzzj.design;

/**
 * @author zzzj
 * @create 2023-04-13 15:57
 */
public class Leet2526 {


    private static class DataStream {

        private final int value;

        private final int k;

        private int seq;

        public DataStream(int value, int k) {
            this.value = value;
            this.k = k;
        }

        public boolean consec(int num) {
            if (num == value) {
                seq++;
            } else {
                seq = 0;
            }
            return seq >= k;
        }

    }

}
