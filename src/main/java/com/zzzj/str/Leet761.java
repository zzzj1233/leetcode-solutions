package com.zzzj.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-06-25 18:17
 */
public class Leet761 {

    public static void main(String[] args) {

        System.out.println(makeLargestSpecial("11011000"));

    }

    public static String makeLargestSpecial(String s) {
        return s.charAt(0) + dfs(s.substring(1, s.length() - 1), 1, s.length() - 2) + s.charAt(s.length() - 1);
    }

    public static String dfs(String str, int left, int right) {
        if (left < right && str.charAt(left) == '0') {
            return "0" + dfs(str, left + 1, right);
        }

        int cnt = 0;

        List<String> list = new ArrayList<>();

        list.add(str);

        for (int i = left; i <= right; i++) {

            if (str.charAt(i) == '1') cnt++;
            else {
                cnt--;

                if (cnt == 0) {

                    String rtStr = dfs(str, i + 1, right);

                    list.add(str.substring(left, i + 1) + rtStr);
                }
            }
        }

        return list.stream().max(String::compareTo).orElse(null);
    }

}
