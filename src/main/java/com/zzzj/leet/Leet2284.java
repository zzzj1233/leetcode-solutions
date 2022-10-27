package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zzzj
 * @create 2022-10-12 10:46
 */
public class Leet2284 {

    public static String largestWordCount(String[] messages, String[] senders) {
        int N = messages.length;

        String reg = "\\s+";

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(senders[i], map.getOrDefault(senders[i], 0) + messages[i].split(reg).length);
        }

        return map.entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    if (Objects.equals(o1.getValue(), o2.getValue())) {
                        return o2.getKey().compareTo(o1.getKey());
                    }
                    return o2.getValue() - o1.getValue();
                }).limit(1)
                .findFirst()
                .get()
                .getKey();
    }


}
