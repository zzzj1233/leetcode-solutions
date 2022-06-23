package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-22 11:40
 */
public class Leet758 {


    public static void main(String[] args) {
//        System.out.println(boldWords(new String[]{"ab", "bc"}, "aabcd"));
//        System.out.println(boldWords(new String[]{"ab", "cb"}, "aabcd"));
        System.out.println(boldWords(new String[]{"ab", "cb"}, "aabcdefgabcb"));
    }

    /**
     * 给定一个关键词集合 words 和一个字符串 s，将所有 s 中出现的关键词 words[i] 加粗。所有在标签 <b> 和 <b> 中的字母都会加粗。
     * <p>
     * 加粗后返回 s 。返回的字符串需要使用尽可能少的标签，当然标签应形成有效的组合。
     * <p>
     * 示例 1:
     * <p>
     * 输入: words = ["ab","bc"], s = "aabcd"
     * 输出: "a<b>abc</b>d"
     * 解释: 注意返回 "a<b>a<b>b</b>c</b>d" 会使用更多的标签，因此是错误的。
     * 示例 2:
     * <p>
     * 输入: words = ["ab","cb"], s = "aabcdefgabcb"
     * 输出: "a<b>ab</b>cd"
     * <p>
     * aabcdefgabcb
     * "a<b>ab</b>cdefg<b>abcb</b>"
     * <p>
     */
    private static final String START = "<b>";
    private static final String END = "</b>";

    public static String boldWords(String[] words, String s) {

        char[] chars = s.toCharArray();

        StringBuilder builder = new StringBuilder();

        Trie trie = buildTrie(words);

        int start = -1;
        int end = -1;

        for (int i = 0; i < chars.length; i++) {
            int searchEnd = search(trie, s, i);
            // 没有单词以此字符开始
            if (searchEnd == -1) {
                builder.append(chars[i]);
            } else {
                // 更新最大end
                end = Math.max(end, searchEnd);
                // 有但是没有start
                if (start == -1) {
                    builder.append(START);
                    builder.append(chars[i]);
                    start = i;
                } else {
                    builder.append(chars[i]);
                }
            }
            if (i == end && (i + 1 == chars.length || search(trie, s, i + 1) == -1)) {
                builder.append(END);
                start = -1;
                end = -1;
            }
        }

        return builder.toString();
    }

    private static class Trie {
        Trie[] next;
        boolean end;

        public Trie() {
            this.next = new Trie[26];
        }
    }

    private static Trie buildTrie(String[] words) {
        Trie root = new Trie();

        Trie node = root;

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new Trie();
                }
                node = node.next[c - 'a'];
            }
            node.end = true;
            node = root;
        }

        return root;
    }

    private static int search(Trie root, String word, int start) {
        int ans = -1;

        Trie cur = root;

        for (int i = start; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (cur.next[index] == null) {
                break;
            }
            cur = cur.next[index];
            if (cur.end) {
                ans = Math.max(ans, i);
            }
        }
        return ans;
    }

}
