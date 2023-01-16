package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-02-11 14:22
 */
public class Leet816 {

    public static void main(String[] args) {
        System.out.println(ambiguousCoordinates("(0100)").stream().collect(Collectors.joining("\n")));
    }

    public static List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();

        int N = s.length();

        char[] chars = new char[N - 2];

        for (int i = 1; i < N - 1; i++) {
            chars[i - 1] = s.charAt(i);
        }

        int M = chars.length;

        StringBuilder path = new StringBuilder(M);

        for (int i = 0; i < M; i++) {
            ArrayList<String> left = new ArrayList<>();
            ArrayList<String> right = new ArrayList<>();
            process(left, chars, 0, i + 1, path);
            process(right, chars, i + 1, M, path);

            for (String s1 : left) {
                for (String s2 : right) {
                    ans.add("(" + s1 + ", " + s2 + ")");
                }
            }
        }

        return ans;
    }

    public static void process(List<String> ans, char[] chars, int left, int right, StringBuilder path) {
        if (left >= right) {
            if (path.length() > 0 && path.charAt(path.length() - 1) != '.') {
                // 小数点后面全是0
                int dotIndex = path.indexOf(".");
                if (dotIndex > 0) {
                    boolean allZero = false;
                    for (int i = dotIndex + 1; i < path.length(); i++) {
                        if (path.charAt(i) == '0') {
                            allZero = true;
                        } else {
                            allZero = false;
                        }
                    }
                    if (allZero) {
                        return;
                    }
                }
                ans.add(path.toString());
            }
            return;
        }

        int num = Character.digit(chars[left], 10);

        int pathLength = path.length();

        if (num == 0) {
            if (pathLength == 0) {

                path.append("0");
                process(ans, chars, left + 1, right, path);
                path.setLength(path.length() - 1);

                path.append("0.");
                process(ans, chars, left + 1, right, path);
                path.setLength(path.length() - 2);
                return;
            }
        }

        // 没有小数点, 前面一个数是0
        if (pathLength > 0 && path.indexOf(".") < 0 && path.charAt(pathLength - 1) == '0' && Double.parseDouble(path.toString()) == 0) {
            // 不能有两个0
            return;
        }

        // 没有小数点
        if (path.indexOf(".") < 0 && path.length() > 0) {
            path.append(".");
            path.append(num);
            process(ans, chars, left + 1, right, path);
            path.setLength(path.length() - 2);
        }

        path.append(num);
        process(ans, chars, left + 1, right, path);
        path.setLength(path.length() - 1);

    }

}
