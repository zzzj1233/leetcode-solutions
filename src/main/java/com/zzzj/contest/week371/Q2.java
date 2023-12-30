package com.zzzj.contest.week371;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(findHighAccessEmployees(LeetUtils.convertListStrings("[[\"ff\",\"1508\"],[\"ff\",\"1508\"],[\"ff\",\"1516\"]]")));

        System.out.println(findHighAccessEmployees(LeetUtils.convertListStrings("[[\"a\",\"0549\"],[\"b\",\"0457\"],[\"a\",\"0532\"],[\"a\",\"0621\"],[\"b\",\"0540\"]]")));

        System.out.println(findHighAccessEmployees(LeetUtils.convertListStrings("[[\"d\",\"0002\"],[\"c\",\"0808\"],[\"c\",\"0829\"],[\"e\",\"0215\"],[\"d\",\"1508\"],[\"d\",\"1444\"],[\"d\",\"1410\"],[\"c\",\"0809\"]]")));

        System.out.println(findHighAccessEmployees(LeetUtils.convertListStrings("[[\"cd\",\"1025\"],[\"ab\",\"1025\"],[\"cd\",\"1046\"],[\"cd\",\"1055\"],[\"ab\",\"1124\"],[\"ab\",\"1120\"]]")));

        // [["iudjn","0812"],["lnlqp","0848"],["lnlqp","0758"],["iudjn","0809"],["jwgrgxox","0848"],["lnlqp","0901"],["jwgrgxox","0807"],["dk","0824"],["dk","0807"]]
        System.out.println(findHighAccessEmployees(LeetUtils.convertListStrings("[[\"iudjn\",\"0812\"],[\"lnlqp\",\"0848\"],[\"lnlqp\",\"0758\"],[\"iudjn\",\"0809\"],[\"jwgrgxox\",\"0848\"],[\"lnlqp\",\"0901\"],[\"jwgrgxox\",\"0807\"],[\"dk\",\"0824\"],[\"dk\",\"0807\"]]")));

    }

    public static List<String> findHighAccessEmployees(List<List<String>> access_times) {

        Map<String, List<String>> accMap = new HashMap<>();

        for (List<String> access_time : access_times)
            accMap.computeIfAbsent(access_time.get(0), s -> new ArrayList<>()).add(access_time.get(1));

        return accMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() >= 3)
                .filter(entry -> {
                    List<String> times = entry.getValue();

                    Collections.sort(times, (o1, o2) -> {
                        int h1 = Integer.parseInt(o1.substring(0, 2));
                        int h2 = Integer.parseInt(o2.substring(0, 2));
                        if (h1 != h2)
                            return h1 - h2;
                        int m1 = Integer.parseInt(o1.substring(2));
                        int m2 = Integer.parseInt(o2.substring(2));
                        return m1 - m2;
                    });
                    // 一小时内
                    int N = times.size();

                    for (int i = 0; i < N; i++) {

                        String curTime = times.get(i);

                        int cnt = 1;

                        for (int j = i + 1; j < N; j++) {
                            if (ok(curTime, times.get(j)))
                                cnt++;
                        }

                        if (cnt >= 3)
                            return true;
                    }

                    return false;
                }).map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static boolean ok(String t, String c) {

        int h1 = Integer.parseInt(t.substring(0, 2));
        int h2 = Integer.parseInt(c.substring(0, 2));

        int m1 = Integer.parseInt(t.substring(2));
        int m2 = Integer.parseInt(c.substring(2));

        if (h1 == h2)
            return m2 >= m1;

        if (h2 - h1 == 1 && m2 < m1)
            return true;

        return false;
    }

}
