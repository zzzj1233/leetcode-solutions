package com.zzzj.greedy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-01-05 14:12
 */
public class Leet759 {

    private static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // 合并区间
        List<Interval> intervals = schedule
                .stream()
                .flatMap(Collection::stream)
                .sorted((o1, o2) -> {
                    if (o1.start == o2.start) {
                        return o1.end - o2.end;
                    }
                    return o1.start - o2.start;
                }).collect(Collectors.toList());


        List<Interval> mergedSchedule = new ArrayList<>();

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);

            if (interval.start > end) {
                mergedSchedule.add(new Interval(start, end));
                start = interval.start;
            }

            if (interval.end > end) {
                end = interval.end;
            }
        }

        mergedSchedule.add(new Interval(start, end));

        List<Interval> ans = new ArrayList<>();

        for (int i = 1; i < mergedSchedule.size(); i++) {
            Interval cur = mergedSchedule.get(i);

            Interval prev = mergedSchedule.get(i - 1);

            ans.add(new Interval(prev.end, cur.start));
        }

        return ans;
    }


}
