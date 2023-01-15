package com.zzzj.design;

public class Leet157 {

    private static class Reader4 {
        int read4(char[] buf4) {
            return -1;
        }
    }

    public static class Solution extends Reader4 {

        public int read(char[] buf, int n) {

            char[] cache = new char[4];

            int read;

            int ptr = 0;

            while ((read = read4(cache)) > 0 && ptr < n) {
                for (int i = 0; i < read && ptr < n; i++) {
                    buf[ptr] = cache[i];
                    ptr++;
                }
            }

            return ptr;
        }
    }

}
