package com.zzzj.bit;

public class Leet2166 {


    public static void main(String[] args) {
        Bitset bitset = new Bitset(5);
        bitset.fix(3);
        bitset.fix(1);

        System.out.println(bitset);
    }

    private static class Bitset {
        private final int size;
        private final int end;
        long[] words;

        final int len;

        int one;

        int zero;

        int flip;

        public Bitset(int size) {
            len = size % 64 == 0 ? size / 64 : size / 64 + 1;
            words = new long[len];
            zero = size;
            this.size = size;
            this.end = size % 64 == 0 ? 64 : size % 64;
        }

        public void fix(int idx) {
            int wordIdx = idx / 64;

            // 1. 未反转,当前为0
            // 2. 已反转,当前为1
            if (flip % 2 == 0) {
                if (!getBit(words[wordIdx], idx % 64)) {
                    // 置为1
                    words[wordIdx] = setBit(words[wordIdx], idx % 64);
                    one++;
                    zero--;
                }
            } else {
                if (getBit(words[wordIdx], idx % 64)) {
                    // 置为0
                    words[wordIdx] = resetBit(words[wordIdx], idx % 64);
                    zero++;
                    one--;
                }
            }
        }

        public void unfix(int idx) {
            int wordIdx = idx / 64;

            // 1. 未反转,当前为1
            // 2. 已反转,当前为0
            if (flip % 2 == 0) {
                if (getBit(words[wordIdx], idx % 64)) {
                    // 置为0
                    words[wordIdx] = resetBit(words[wordIdx], idx % 64);
                    zero++;
                    one--;
                }
            } else {
                if (!getBit(words[wordIdx], idx % 64)) {
                    // 置为1
                    words[wordIdx] = setBit(words[wordIdx], idx % 64);
                    one++;
                    zero--;
                }
            }
        }

        public void flip() {
            flip++;
        }

        public boolean all() {
            if (flip % 2 == 0) {
                return one == size;
            }
            return zero == size;
        }

        public boolean one() {
            if (flip % 2 == 0) {
                return one > 0;
            }
            return zero > 0;
        }

        public int count() {
            if (flip % 2 == 0) {
                return one;
            }
            return zero;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();

            boolean reverse = flip % 2 != 0;

            for (int i = 0; i < len - 1; i++) {
                long word = words[i];
                for (int j = 0; j < Long.SIZE; j++) {
                    if (((word >> j) & 1) == 1) {
                        builder.append(reverse ? '0' : '1');
                    } else {
                        builder.append(reverse ? '1' : '0');
                    }
                }
            }

            long last = words[len - 1];

            for (int i = 0; i < end; i++) {
                if (((last >> i) & 1) == 1) {
                    builder.append(reverse ? '0' : '1');
                } else {
                    builder.append(reverse ? '1' : '0');
                }
            }

            return builder.toString();
        }

        public static boolean getBit(long word, int bitSize) {
            return ((word >> bitSize) & 1) == 1;
        }

        public static long setBit(long word, int bitSize) {
            return word | 1L << bitSize;
        }

        public static long resetBit(long word, int bitSize) {
            return word & ~(1L << bitSize);
        }

    }



}
