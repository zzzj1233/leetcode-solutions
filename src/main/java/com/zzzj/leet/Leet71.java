package com.zzzj.leet;


import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-09-28 11:08
 */
public class Leet71 {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
    }

    public static String simplifyPath(String path) {

        String[] split = path.split("/");

        LinkedList<String> stack = new LinkedList<>();

        for (String s : split) {
            String str = s.trim();
            if (str.isEmpty() || str.equals(".")) {
                continue;
            }

            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.add(str);
            }
        }

        return "/" + String.join("/", stack);
    }


}
