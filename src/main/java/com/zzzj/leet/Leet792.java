package com.zzzj.leet;

import java.util.ArrayList;

/**
 * @author Zzzj
 * @create 2022-07-24 16:39
 */
public class Leet792 {

    public static int numMatchingSubseq(String s, String[] words) {

        int N = words.length;

        ArrayList<Node>[] nodes = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String word = words[i];

            Node node = new Node();
            node.str = word;
            node.index = 0;

            int index = word.charAt(i) - 'a';

            nodes[index].add(node);
        }

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            int index = s.charAt(i) - 'a';

            ArrayList<Node> nodeList = nodes[index];

            // 换桶

            ArrayList<Node> copy = new ArrayList<>();

            for (int j = 0; j < nodeList.size(); j++) {
                Node node = nodeList.get(j);
                if (node.isEnd()) {
                    ans++;
                } else {
                    node.index++;
                    int nextIndex = node.str.charAt(node.index) - 'a';
                    if (nextIndex == index) {
                        copy.add(node);
                    } else {
                        nodes[nextIndex].add(node);
                    }
                }
            }

            nodes[index] = copy;
        }

        return ans;
    }

    private static class Node {
        String str;
        int index;

        public boolean isEnd() {
            return index == str.length();
        }
    }

}
