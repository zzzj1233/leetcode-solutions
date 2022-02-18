package com.zzzj.leet;

import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-09-28 11:08
 */
public class Leet71 {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
    }

    // /home/ -> /home
    // /../  -> /
    // /home//foo/ -> /home/foo
    // /a/.b/../../c -> /c
    private static final char DOT = '.';

    private static final char SEP = '/';

    private static boolean isBack(int i, String path) {
        return path.charAt(i) == DOT && i < path.length() - 2 && path.charAt(i + 1) == DOT && path.charAt(i + 2) == SEP;
    }

    private static boolean isBack2(int i, String path) {
        return path.charAt(i) == DOT && i < path.length() - 1 && path.charAt(i + 1) == DOT;
    }

    private static boolean isCurrent(int i, String path) {
        return path.charAt(i) == DOT && i < path.length() - 1 && path.charAt(i + 1) == SEP;
    }

    private static boolean isThreeDot(int i, String path) {
        return path.charAt(i) == DOT && i < path.length() - 2 && path.charAt(i + 1) == DOT && path.charAt(i + 2) == DOT;
    }

    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        // 在规范路径中，多个连续斜杠需要用一个斜杠替换
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);

            if (isThreeDot(i, path)) {
                stack.push("...");
                i += 2;
            } else if (isBack(i, path)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                i += 2;
            } else if (isBack2(i, path)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                i += 1;
            } else if (isCurrent(i, path)) {
                i += 1;
            } else if (SEP == c) {
                if (i < path.length() - 1 && path.charAt(i + 1) == SEP) {
                    while (i < path.length() - 1 && path.charAt(i + 1) == SEP) {
                        i++;
                    }
                }
            } else if (DOT == c) {
                continue;
            } else {
                int startI = i;
                while (i < path.length() && path.charAt(i) != SEP) {
                    i++;
                }
                stack.push(path.substring(startI, i));
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(SEP);

        for (int i = 0; i < stack.size(); i++) {
            String element = stack.elementAt(i);
            stringBuffer.append(element);
            if (i != stack.size() - 1) {
                stringBuffer.append(SEP);
            }
        }

        return stringBuffer.toString();
    }

}
