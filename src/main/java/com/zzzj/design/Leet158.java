package com.zzzj.design;

/**
 * @author zzzj
 * @create 2023-01-04 12:18
 */
public class Leet158 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.source = "abc";

        char[] buf = new char[4];

        System.out.println(solution.read(buf, 4) + " --- " + new String(buf, 0, 4));

        System.out.println(solution.read(buf, 1) + " --- " + new String(buf, 0, 1));

//        System.out.println(solution.read(buf, 2) + " --- " + new String(buf, 0, 2));

    }

    private static class Reader4 {

        protected String source;

        int index;

        int read4(char[] buf4) {
            int startIndex = index;

            for (int i = 0; i < buf4.length && index < source.length(); i++, index++) {
                buf4[i] = source.charAt(index);
            }

            return index - startIndex;
        }
    }

    public static class Solution extends Reader4 {

        static final int CAPACITY = 4;

        char[] memoBuf = new char[CAPACITY];

        int limit;

        int readIndex;

        public int read(char[] buf, int n) {
            int bufIndex = 0;

            while (bufIndex < n) {

                while (bufIndex < n && readable()) {
                    buf[bufIndex] = memoBuf[readIndex];
                    bufIndex++;
                    readIndex++;
                }

                // 还没有读完
                if (bufIndex < n) {
                    limit = read4(memoBuf);
                    readIndex = 0;

                    if (limit == 0){
                        break;
                    }
                }

            }

            return bufIndex;
        }

        public int readableBytes() {
            return limit - readIndex;
        }

        public boolean readable() {
            return readIndex < limit;
        }

    }

}
