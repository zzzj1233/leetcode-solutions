package com.zzzj.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-09-02 16:13
 */
public class Leet937 {

    public static String[] reorderLogFiles(String[] logs) {

        List<WordLog> wordLogs = new ArrayList<>();

        List<NumLog> numLogs = new ArrayList<>();

        final int N = logs.length;

        for (int i = 0; i < N; i++) {
            String log = logs[i];

            String[] s = log.split(" ", 2);

            if (Character.isDigit(s[1].charAt(0))) {
                numLogs.add(new NumLog(log, i));
            } else {
                wordLogs.add(new WordLog(log, s[0], s[1]));
            }
        }

        String[] ans = new String[N];

        wordLogs.sort((o1, o2) -> {
            // 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
            int compare1 = o1.word.compareTo(o2.word);
            if (compare1 == 0) {
                return o1.identify.compareTo(o2.identify);
            }
            return compare1;
        });

        for (int i = 0; i < wordLogs.size(); i++) {
            ans[i] = wordLogs.get(i).log;
        }

        numLogs.sort(Comparator.comparingInt(o -> o.index));
        ;

        for (int i = 0; i < numLogs.size(); i++) {
            ans[i + wordLogs.size()] = numLogs.get(i).log;
        }

        return ans;
    }

    private static class WordLog {

        private final String log;
        private final String identify;
        private final String word;

        public WordLog(String log, String identify, String word) {
            this.log = log;
            this.identify = identify;
            this.word = word;
        }
    }

    private static class NumLog {
        final int index;

        private final String log;

        private NumLog(String log, int index) {
            this.log = log;
            this.index = index;
        }
    }


}
