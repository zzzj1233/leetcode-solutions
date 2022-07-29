package com.zzzj.trie;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-26 18:47
 */
public class Leet2716 {

    public static void main(String[] args) {

//        System.exit(0);

        Solution solution = new Solution();

        for (int i = 0; i < 1000; i++) {
            final int N = LeetUtils.random.nextInt(5) + 1;

            String[] words = new String[N];

            for (int j = 0; j < N; j++) {
                words[j] = LeetUtils.randomString(LeetUtils.random.nextInt(5) + 1, false);
            }
            String[] myAns = maxRectangle(words);
            String[] rightAns = solution.maxRectangle(words);
            if (myAns.length == 1 && rightAns.length == 1) {
                continue;
            }
            if (!Arrays.equals(myAns, rightAns)) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringsToLeetCode(words));
                System.out.println("MyAns = " + Arrays.toString(myAns));
                System.out.println("Ans   = " + Arrays.toString(rightAns));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static List<String> ans;

    public static String[] maxRectangle(String[] words) {
        ans = Collections.emptyList();

        Trie root = new Trie();


        // 1. 构建Trie树
        for (int i = 0; i < words.length; i++) {
            Trie node = root;

            String word = words[i];

            int wordLen = word.length();

            for (int j = 0; j < wordLen; j++) {
                int index = word.charAt(j) - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }
            node.end = true;
        }

        // 2. 把words放入treeMap,按照长度逆序排,为了剪枝
        // 假设wordLength为5,求出了答案,那么一定是最大的矩阵,不需要再去考虑wordLength为4,3,2,1的情况
        TreeMap<Integer, List<String>> wordsMap = new TreeMap<>((o1, o2) -> o2 - o1);

        for (String word : words) {
            wordsMap.computeIfAbsent(word.length(), ignore -> new ArrayList<>())
                    .add(word);
        }


        // 3. dfs路径路径
        ArrayList<String> path = new ArrayList<>();

        // 4. 遍历treeMap,最先遍历是长度最长的字符串
        for (Map.Entry<Integer, List<String>> entry : wordsMap.entrySet()) {

            for (String s : entry.getValue()) {
                // 5. 创建nodes节点,dfs时传递并且更新
                Trie[] nodes = new Trie[s.length()];

                for (int i = 0; i < s.length(); i++) {
                    nodes[i] = root;
                }

                path.add(s);

                // 6. dfs
                dfs(root, entry.getKey(), wordsMap, nodes, path);

                path.remove(path.size() - 1);
            }

            // 7. 如果当前length有答案了,提前中止,不再考虑长度小于当前length的字符串
            if (!ans.isEmpty()) {
                break;
            }
        }


        String[] arr = new String[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            arr[i] = ans.get(i);
        }

        return arr;
    }

    public static void dfs(Trie root, int wordLen, Map<Integer, List<String>> wordsMap, Trie[] nodes, ArrayList<String> path) {
        // 1. 检测当前path是否有效,如果有效,并且矩阵大于ans,那么更新ans
        if (isValid(path, root) && path.size() > ans.size()) {
            ans = new ArrayList<>(path);
        }

        // 2. 只选择长度一样的字符串作为候选

        // e.g. abcd  len = 4
        //      ????  len = 4
        //      ????  len = 4
        List<String> candidates = wordsMap.get(wordLen);

        if (candidates == null || candidates.isEmpty()) {
            return;
        }

        // 3. 利用前缀树剪枝
        String previous = path.get(path.size() - 1);

        Trie[] newsNodes = new Trie[wordLen];

        for (int i = 0; i < wordLen; i++) {
            newsNodes[i] = nodes[i].next[previous.charAt(i) - 'a'];
            if (newsNodes[i] == null) {
                return;
            }
        }

        OUTER:
        for (String word : candidates) {

            // 4. 利用前缀树剪枝根据当前单词剪枝
            for (int i = 0; i < wordLen; i++) {
                int charIndex = word.charAt(i) - 'a';
                if (newsNodes[i].next[charIndex] == null) {
                    continue OUTER;
                }
            }

            path.add(word);

            // 5. 继续DFS
            dfs(root, wordLen, wordsMap, newsNodes, path);

            path.remove(path.size() - 1);
        }

    }


    public static boolean isValid(ArrayList<String> path, Trie root) {
        if (path.isEmpty()) {
            return false;
        }

        int N = path.size();

        int M = path.get(0).length();

        for (int i = 0; i < M; i++) {
            Trie node = root;
            for (int j = 0; j < N; j++) {
                int index = path.get(j).charAt(i) - 'a';
                if (node.next[index] == null) {
                    return false;
                }
                node = node.next[index];
            }
            if (!node.end) {
                return false;
            }
        }

        return true;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        boolean end;
    }


    private static class Solution {
        private static class Trie {
            Trie[] childs;
            boolean isLeaf;

            public Trie() {
                childs = new Trie[26];
            }
        }

        Trie root;
        Map<Integer, Set<String>> map;  //把清单根据单词长度集合起来
        int maxArea;
        int maxLength;
        List<String> ans;   //别忘最后转换成数组输出

        public String[] maxRectangle(String[] words) {
            root = new Trie();
            //构造字典树
            for (String str : words) {
                Trie node = root;
                for (char c : str.toCharArray()) {
                    if (node.childs[c - 'a'] == null) {
                        node.childs[c - 'a'] = new Trie();
                    }
                    node = node.childs[c - 'a'];
                }
                node.isLeaf = true;
            }

            map = new HashMap<>();
            ans = new ArrayList<>();
            maxArea = 0;
            maxLength = 0;
            for (String w : words) {
                int len = w.length();
                maxLength = Math.max(maxLength, len);
                Set<String> set = map.getOrDefault(len, new HashSet<>());
                set.add(w);
                map.put(len, set);
            }

            List<String> path = new ArrayList<>();
            for (int key : map.keySet()) {
                path.clear();
                //回溯需要的参数是：相同长度单词的集合，存放路径的列表，当前单词的长度
                dfs(map.get(key), path, key);
            }

            String[] ultimate = new String[ans.size()];
            return ans.toArray(ultimate);
        }

        //回溯的“套路”
        public void dfs(Set<String> dic, List<String> path, int wordLen) {
            //剪枝，dic里的情况不可能得到最优解，提前过滤掉不考虑
            if (wordLen * maxLength <= maxArea) return;

            //终止条件：如果path矩阵的高度已经超过清单中最长单词长度，结束
            if (path.size() > maxLength) return;

            for (String str : dic) {
                //做选择
                path.add(str);

                boolean[] res = isValid(path);
                if (res[0]) { //下面还可以再加
                    int area = path.size() * path.get(0).length();
                    if (res[1] && (area > maxArea)) {   //每列都是单词的矩阵
                        maxArea = area;
                        //ans = path;   一定注意这里不能直接把path引用交给答案
                        ans = new ArrayList<>(path);
                    }
                    //回溯
                    dfs(dic, path, wordLen);
                }
                //撤销选择
                path.remove(path.size() - 1);
            }
        }

        /**
         * 判断一个矩阵是否每一列形成的单词都在清单里
         * 存在两种情况：1.有的列中的字母不在字典树中，即这一列不可能构成单词，整个矩阵不合要求
         * 2.每列的所有字母都在字典树中但有的结尾不是leaf，也就是有的列目前还不是个单词
         * 所以需要一个boolean数组res[]来存放结果：
         * res[0]表示是否有字母不在字典树中，true:都在，false:有不在的
         * res[1]表示是否所有的列都构成了清单里的单词
         */
        public boolean[] isValid(List<String> input) {
            boolean[] res = new boolean[2];
            boolean allLeaf = true;
            int m = input.size();
            int n = input.get(0).length();
            for (int j = 0; j < n; j++) {
                //按列来看单词是否在字典树
                Trie node = root;
                for (int i = 0; i < m; i++) {
                    int c = input.get(i).charAt(j) - 'a';
                    if (node.childs[c] == null) return new boolean[]{false, false};
                    node = node.childs[c];
                }
                if (!node.isLeaf) allLeaf = false;
            }
            return new boolean[]{true, allLeaf};
        }
    }


}
