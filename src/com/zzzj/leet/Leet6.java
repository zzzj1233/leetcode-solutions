package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-25 16:59
 */
public class Leet6 {


    public static void main(String[] args) {
        System.out.println(convert("ABCD", 2));
    }

    /**
     * A C
     * B  D
     */

    public static String convert(String s, int numRows) {
        List<StringBuilder> lines = new ArrayList<>(numRows);

        for (int i = 0; i < numRows; i++) {
            lines.add(new StringBuilder());
        }

        // point: 需要知道每个字母应该是哪一行的
        int curIndex = 0;

        int lineIndex = 0;

        boolean down = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            lines.get(lineIndex).append(c);

            if (down) {
                lineIndex = Math.max(lineIndex - 1, 0);
            } else {
                lineIndex++;
            }

            if (lineIndex == numRows) {
                lineIndex = Math.max(lineIndex - 2, 0);
                down = lineIndex != 0;
            } else if (down && lineIndex == 0) {
                down = false;
            }
        }

        return String.join("", lines);
    }

}
