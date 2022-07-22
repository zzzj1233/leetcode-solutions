package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-07-20 16:28
 */
public class Leet642 {

    public static void main(String[] args) {
        AutocompleteSystem system = new AutocompleteSystem(new String[]{"i love you", "island", "iroman", "i love leetcode"}, new int[]{5, 3, 2, 2});

        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
    }

    private static class AutocompleteSystem {

        private final int[] times;

        private final String[] sentences;

        private Trie trie;

        private Trie origin;

        private StringBuilder path;

        private int index;

        private final int MAX = 5000;

        private IndexComparator comparator = new IndexComparator();

        private IndexComparator2 comparator2 = new IndexComparator2();

        private static class Trie {
            Trie[] next;
            boolean isEnd;
            int index;

            public Trie() {
                this.next = new Trie[128];
            }
        }

        private boolean addWord(String word, int index, int addTimes) {
            Trie node = origin;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                Trie next = node.next[c];
                if (next == null) {
                    next = new Trie();
                    node.next[c] = next;
                }
                node = next;
            }
            if (node.isEnd) {
                times[node.index]++;
                return false;
            } else {
                node.isEnd = true;
                node.index = index;
                times[index] = addTimes;
                sentences[index] = word;
                return true;
            }
        }

        public AutocompleteSystem(String[] sentences, int[] times) {
            this.trie = new Trie();

            origin = this.trie;

            path = new StringBuilder();

            this.sentences = new String[MAX + 1];

            this.times = new int[MAX + 1];

            for (int i = 0; i < sentences.length; i++) {
                addWord(sentences[i], i, times[i]);
            }

            this.index = sentences.length;
        }

        private void getWord(List<Integer> indexes, Trie trie) {
            if (trie.isEnd) {
                indexes.add(trie.index);
            }
            for (Trie next : trie.next) {
                if (next != null) {
                    getWord(indexes, next);
                }
            }
        }

        private class IndexComparator implements Comparator<Integer> {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (times[o1] == times[o2]) {
                    return sentences[o2].compareTo(sentences[o1]);
                }
                return times[o1] - times[o2];
            }
        }

        private class IndexComparator2 implements Comparator<Integer> {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (times[o1] == times[o2]) {
                    return sentences[o1].compareTo(sentences[o2]);
                }
                return times[o2] - times[o1];
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                if (addWord(path.toString(), index, 1)) {
                    index++;
                }
                path.setLength(0);
                this.trie = origin;
                return Collections.emptyList();
            }
            path.append(c);

            if (trie == null) {
                return Collections.emptyList();
            }

            trie = trie.next[c];

            if (this.trie == null) {
                return Collections.emptyList();
            }

            // 遍历trie的words
            LinkedList<Integer> indexes = new LinkedList<>();

            getWord(indexes, trie);

            if (indexes.isEmpty()) {
                return Collections.emptyList();
            }

            // 排序
            PriorityQueue<Integer> queue = new PriorityQueue<>(3, comparator);

            for (Integer index : indexes) {
                if (queue.size() < 3) {
                    queue.add(index);
                } else {
                    int time = times[index];
                    if (time < times[queue.peek()] || (time == times[queue.peek()] && sentences[index].compareTo(sentences[queue.peek()]) >= 0)) {
                        continue;
                    }
                    queue.remove();
                    queue.add(index);
                }
            }

            return queue.stream()
                    .sorted(comparator2)
                    .map(index -> sentences[index])
                    .collect(Collectors.toList());
        }
    }

}
