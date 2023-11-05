package com.zzzj.leet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-11-24 16:25
 */
public class Leet1807 {

    public static String evaluate(String s, List<List<String>> knowledge) {

        Map<String, String> kv = knowledge.stream()
                .collect(Collectors.toMap(strings -> strings.get(0), strings -> strings.get(1)));

        int N = s.length();

        int startIdx = -1;

        StringBuilder ans = new StringBuilder(s.length());

        for (int i = 0; i < N; i++) {
            ans.append(s.charAt(i));
            if (s.charAt(i) == '(') {
                startIdx = i;
            } else if (s.charAt(i) == ')') {
                if (startIdx == -1) {
                    continue;
                }
                // try replace
                String placeholder = s.substring(startIdx + 1, i);

                String value = kv.get(placeholder);

                if (value == null) {
                    value = "?";
                }

                int len1 = i - startIdx + 1;

                ans.setLength(ans.length() - len1);

                ans.append(value);

            }
        }

        return ans.toString();
    }

}
