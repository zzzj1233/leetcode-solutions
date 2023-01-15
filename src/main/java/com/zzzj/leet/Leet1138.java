package com.zzzj.leet;

public class Leet1138 {

    public static void main(String[] args) {

        System.out.println(alphabetBoardPath("zdz"));

        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(10, false);

            if (alphabetBoardPath(str).length() != right(str).length()) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringToLeetCode(str));
                System.out.println(alphabetBoardPath(str));
                System.out.println(right(str));
                return;
            }
        }

        System.out.println("Ok");
    }

    static final int[][] table = new int[128][2];

    public static void initTable(char c, int left, int right) {
        table[c][0] = left;
        table[c][1] = right;
    }

    static {
        initTable('a', 0, 4);
        initTable('b', 1, 3);
        initTable('c', 2, 2);
        initTable('d', 3, 1);
        initTable('e', 4, 0);

        initTable('f', 0, 4);
        initTable('g', 1, 3);
        initTable('h', 2, 2);
        initTable('i', 3, 1);
        initTable('j', 4, 0);

        initTable('k', 0, 4);
        initTable('l', 1, 3);
        initTable('m', 2, 2);
        initTable('n', 3, 1);
        initTable('o', 4, 0);

        initTable('p', 0, 4);
        initTable('q', 1, 3);
        initTable('r', 2, 2);
        initTable('s', 3, 1);
        initTable('t', 4, 0);

        initTable('u', 0, 4);
        initTable('v', 1, 3);
        initTable('w', 2, 2);
        initTable('x', 3, 1);
        initTable('y', 4, 0);

        initTable('z', 0, 0);
        initTable(((char) ('z' + 1)), 1, 0);
        initTable(((char) ('z' + 2)), 2, 0);
        initTable(((char) ('z' + 3)), 3, 0);
        initTable(((char) ('z' + 4)), 4, 0);
    }

    public static String alphabetBoardPath(String target) {
        StringBuilder builder = new StringBuilder();

        char cur = 'a';

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            while (true) {
                int step = c - cur;

                if (step == 0) {
                    builder.append("!");
                    break;
                }

                int[] row = table[cur];

                // 1. 是否是同一排的?
                boolean isSameLow = Math.abs(step) <= 4 && (step < 0 ? Math.abs(step) <= row[0] : step <= row[1]);

                if (!isSameLow) {
                    // 在下面
                    if (step > 0) {
                        int down = Math.max(1, step / 5);
                        for (int j = 0; j < down; j++) {
                            builder.append("D");
                        }
                        cur = ((char) (cur + down * 5));
                    } else {
                        int up = Math.max(1, Math.abs(step) / 5);
                        for (int j = 0; j < up; j++) {
                            builder.append("U");
                        }
                        cur = ((char) (cur - up * 5));
                    }
                } else {
                    if (step > 0) {
                        for (int j = 0; j < step; j++) {
                            builder.append("R");
                        }
                        cur = c;
                    } else {
                        step = Math.abs(step);
                        for (int j = 0; j < step; j++) {
                            builder.append("L");
                        }
                        cur = c;
                    }
                }
            }
        }

        return builder.toString();
    }

    public static String right(String target) {
        StringBuilder sb = new StringBuilder();
        int cur = 0, tar;
        for (int j = 0; j < target.length(); j++) {
            tar = target.charAt(j) - 'a';
            if (cur == 25 && tar != 25) {
                sb.append("U");
                cur = 'u' - 'a';
            }
            int difCol = (tar % 5) - (cur % 5);
            char dir = 'R';
            if (difCol < 0) {
                difCol = -difCol;
                dir = 'L';
            }
            for (int i = 0; i < difCol; i++) {
                sb.append(dir);
            }
            int difRow = (tar / 5) - (cur / 5);
            dir = 'D';
            if (difRow < 0) {
                difRow = -difRow;
                dir = 'U';
            }
            for (int i = 0; i < difRow; i++) {
                sb.append(dir);
            }
            sb.append("!");
            cur = tar;
        }
        return sb.toString();
    }

}
