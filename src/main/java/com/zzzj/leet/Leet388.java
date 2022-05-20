package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-05-18 18:19
 */
public class Leet388 {

    public static void main(String[] args) {
        /**
         *   dir
         *   \\n\\t subdir1
         *    \\n\\t\\t file1.ext
         *    \\n\\t\\t subsubdir1
         *    \\n\\t subdir2
         *    \\n\\t\\t subsubdir2
         *    \\n\\t\\t\\t file2.ext
         */
        System.out.println(lengthLongestPath("dir\n        file.txt"));
    }

    // "dir\n        file.txt"
    public static int lengthLongestPath(String input) {
        LinkedList<int[]> stack = new LinkedList<>();

        int len = 0;
        int tCount = 0;
        boolean isFile = false;

        int ans = 0;

        for (int i = 0; i < input.length(); ) {
            char c = input.charAt(i);
            if (c == '\n' || c == '\t') {

                while (!stack.isEmpty() && tCount <= stack.peekLast()[1]) {
                    stack.removeLast();
                }
                // 计算上一个
                if (isFile) {
                    ans = Math.max(ans, len + (stack.isEmpty() ? 0 : stack.peekLast()[0]));
                } else { // is folder
                    stack.add(new int[]{len + 1 + (stack.isEmpty() ? 0 : stack.peekLast()[0]), tCount});
                }

                // reset
                tCount = 0;
                len = 0;
                isFile = false;

                while (c == '\n' || c == '\t') {
                    if (c == '\t') {
                        tCount++;
                    }
                    c = input.charAt(++i);
                }
            } else if (input.charAt(i) == '.') {
                isFile = true;
                len++;
                i++;
            } else {
                len++;
                i++;
            }

        }

        while (!stack.isEmpty() && tCount <= stack.peekLast()[1]) {
            stack.removeLast();
        }
        if (isFile) {
            ans = Math.max(ans, len + (stack.isEmpty() ? 0 : stack.peekLast()[0]));
        }

        return ans;
    }

}
