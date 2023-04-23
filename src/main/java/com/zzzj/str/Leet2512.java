package com.zzzj.str;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zzzj
 * @create 2023-04-21 18:05
 */
public class Leet2512 {

    public static List<Integer> topStudents(String[] positive_feedback,
                                            String[] negative_feedback,
                                            String[] report,
                                            int[] student_id,
                                            int k
    ) {

        Set<String> positive = Arrays.stream(positive_feedback)
                .collect(Collectors.toSet());

        Set<String> negative = Arrays.stream(negative_feedback)
                .collect(Collectors.toSet());

        int N = student_id.length;

        int[] scores = new int[N];

        for (int i = 0; i < N; i++) {

            String[] words = report[i].split(" ");

            for (String word : words) {

                if (positive.contains(word)) {
                    scores[i] += 3;
                } else if (negative.contains(word)) {
                    scores[i] -= 1;
                }
            }

        }

        return IntStream.range(0, student_id.length)
                .boxed()
                .sorted((o1, o2) -> {
                    if (scores[o2] == scores[o1]) {
                        return student_id[o1] - student_id[o2];
                    }
                    return scores[o2] - scores[o1];
                })
                .map(index -> student_id[index])
                .limit(k)
                .collect(Collectors.toList());
    }


}
