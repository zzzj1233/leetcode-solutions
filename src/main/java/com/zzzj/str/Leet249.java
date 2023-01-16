package com.zzzj.str;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-01-05 18:36
 */
public class Leet249 {

    public static void main(String[] args) {
        System.out.println(groupStrings(LeetUtils.convertString1("[\"abc\", \"bcd\", \"acef\", \"xyz\", \"az\", \"ba\", \"a\", \"z\"]")));
    }

    public static List<List<String>> groupStrings(String[] strings) {

        List<List<String>> ans = new ArrayList<>();

        Map<Integer, List<String>> map = Arrays.stream(strings).collect(Collectors.groupingBy(String::length));

        if (map.containsKey(1)) {
            ans.add(map.remove(1));
        }

        // 同样长度的列表才有可能成为一组
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {

            // 对每一个字符串计算偏移量

            ans.addAll(
                    entry.getValue().stream()
                            .collect(Collectors.groupingBy(s -> offset(s)))
                            .values()
            );
        }

        return ans;
    }

    private static String offset(String word) {
        int N = word.length();

        StringBuilder offset = new StringBuilder();

        char c0 = word.charAt(0);

        for (int i = 1; i < N; i++) {
            char c = word.charAt(i);

            int sub = c - c0;

            if (sub < 0) {
                sub += 26;
            }

            offset.append(sub).append("-");
        }

        return offset.toString();
    }

}
