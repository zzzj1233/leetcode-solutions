package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-09-29 18:33
 */
public class Leet127 {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);

        Set<String> visited = new HashSet<>(wordList.size());

        LinkedList<String> queue = new LinkedList<>();

        int level = 1;

        queue.add(beginWord);

        visited.add(beginWord);

        while (!queue.isEmpty()) {

            level++;

            int size = queue.size();

            for (int k = 0; k < size; k++) {
                String last = queue.removeFirst();

                char[] chars = last.toCharArray();

                for (int j = 0; j < last.length(); j++) {

                    char c = last.charAt(j);

                    for (char i = 'a'; i <= 'z'; i++) {
                        if (i == c) {
                            continue;
                        }
                        chars[j] = i;

                        String newStr = String.valueOf(chars);

                        if (visited.contains(newStr)) {
                            continue;
                        }

                        if (words.contains(newStr)) {
                            if (endWord.equals(newStr)) {
                                return level;
                            }

                            visited.add(newStr);

                            queue.add(newStr);
                        }

                    }
                    chars[j] = c;
                }
            }

        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ladderLength("qa", "sq", TreeNode.buildList("[\"si\",\"go\",\"se\",\"cm\",\"so\",\"ph\",\"mt\",\"db\",\"mb\",\"sb\",\"kr\",\"ln\",\"tm\",\"le\",\"av\",\"sm\",\"ar\",\"ci\",\"ca\",\"br\",\"ti\",\"ba\",\"to\",\"ra\",\"fa\",\"yo\",\"ow\",\"sn\",\"ya\",\"cr\",\"po\",\"fe\",\"ho\",\"ma\",\"re\",\"or\",\"rn\",\"au\",\"ur\",\"rh\",\"sr\",\"tc\",\"lt\",\"lo\",\"as\",\"fr\",\"nb\",\"yb\",\"if\",\"pb\",\"ge\",\"th\",\"pm\",\"rb\",\"sh\",\"co\",\"ga\",\"li\",\"ha\",\"hz\",\"no\",\"bi\",\"di\",\"hi\",\"qa\",\"pi\",\"os\",\"uh\",\"wm\",\"an\",\"me\",\"mo\",\"na\",\"la\",\"st\",\"er\",\"sc\",\"ne\",\"mn\",\"mi\",\"am\",\"ex\",\"pt\",\"io\",\"be\",\"fm\",\"ta\",\"tb\",\"ni\",\"mr\",\"pa\",\"he\",\"lr\",\"sq\",\"ye\"]")));
    }

}
