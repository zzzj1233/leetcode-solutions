package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-01-04 11:05
 */
public class Leet604 {

    private static class StringIterator {

        private final String compressedString;

        int repeat;

        int index;

        Character lastChar;

        public StringIterator(String compressedString) {
            this.compressedString = compressedString;
            fetch();
        }

        public void fetch() {
            if (index >= compressedString.length()) {
                this.lastChar = null;
                this.repeat = 0;
                return;
            }

            char c = compressedString.charAt(index);

            int repeat = 0;

            if (index + 1 < compressedString.length()) {

                if (Character.isDigit(compressedString.charAt(index + 1))) {
                    index++;
                    while (index < compressedString.length() && Character.isDigit(compressedString.charAt(index))) {
                        repeat = repeat * 10 + Character.digit(compressedString.charAt(index), 10);
                        index++;
                    }
                }

            }

            this.lastChar = c;
            this.repeat = repeat;

            if (repeat == 0) {
                this.fetch();
            }
        }

        public char next() {
            if (!hasNext()){
                return ' ';
            }

            repeat--;

            char result = this.lastChar;

            if (repeat == 0){
                this.fetch();
            }

            return result;
        }

        public boolean hasNext() {
            return lastChar != null && repeat > 0;
        }

    }

}
