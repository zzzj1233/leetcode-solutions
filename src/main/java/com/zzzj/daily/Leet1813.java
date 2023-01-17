package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-01-16 14:28
 */
public class Leet1813 {

    public static void main(String[] args) {

//        System.out.println(areSentencesSimilar("My name is Haley", "My Haley"));
//        System.out.println(areSentencesSimilar("of", "A lot of words"));
//        System.out.println(areSentencesSimilar("Eating right now", "Eating"));

        // "eTUny i b R UFKQJ EZx JBJ Q xXz"
        // "eTUny i R EZx JBJ xXz"
        System.out.println(areSentencesSimilar("eTUny i b R UFKQJ EZx JBJ Q xXz",
                "eTUny i R EZx JBJ xXz"));

        System.exit(0);

        int N = 100000;

        for (int i = 0; i < N; i++) {

            int M = 10;

            String[] words1 = new String[M];

            String[] words2 = new String[M];

            int K = 5;

            for (int j = 0; j < M; j++) {
                words1[j] = LeetUtils.randomString(LeetUtils.random.nextInt(K) + 1, false);

                words2[j] = LeetUtils.randomString(LeetUtils.random.nextInt(K) + 1, false);
            }

            String s1 = String.join(" ", words1);
            String s2 = String.join(" ", words2);

            if (areSentencesSimilar(s1, s2) != right(s1, s2)) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringToLeetCode(s1));
                System.out.println(LeetUtils.stringToLeetCode(s2));
                System.out.println(areSentencesSimilar(s1, s2));
                System.out.println(right(s1, s2));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static boolean areSentencesSimilar(String sentence1, String sentence2) {

        String[] words1 = sentence1.split(" ");

        String[] words2 = sentence2.split(" ");

        int N = words1.length;

        int M = words2.length;

        for (int i = 0; i < N; i++) {

            LOOP2:
            for (int j = 0; j < M; j++) {

                if (words1[i].equals(words2[j])) {

                    int x = i;
                    int y = j;

                    while (x < N && y < M) {
                        if (!words1[x].equals(words2[y])) {
                            break;
                        }
                        x++;
                        y++;
                    }

                    if (x != N && y != M) {
                        // 检查包含

                        // 长度大的字符串有可能包含长度短的字符串
                        if (N == M) {
                            continue LOOP2;
                        }

                        if (i != 0 || j != 0) {
                            continue LOOP2;
                        }

                        if (N > M) {
                            while (x < N) {
                                // 匹配上了,如果后面的长度相同,那么依次匹配后续的字符串
                                if (words1[x].equals(words2[y])) {
                                    if (N - x == M - y) {
                                        while (x < N) {
                                            if (!words1[x].equals(words2[y])) {
                                                continue LOOP2;
                                            }
                                            x++;
                                            y++;
                                        }
                                        return true;
                                    }
                                    if (N - x < M - y) {
                                        continue LOOP2;
                                    }
                                }
                                x++;
                            }
                            continue LOOP2;
                        } else {
                            while (y < M) {
                                // 匹配上了,如果后面的长度相同,那么依次匹配后续的字符串
                                if (words1[x].equals(words2[y])) {
                                    if (N - x == M - y) {
                                        while (x < N) {
                                            if (!words1[x].equals(words2[y])) {
                                                continue LOOP2;
                                            }
                                            x++;
                                            y++;
                                        }
                                        return true;
                                    }
                                    if (M - y < N - x) {
                                        continue LOOP2;
                                    }
                                }
                                y++;
                            }
                            continue LOOP2;
                        }
                    }

                    if (x == N && y == M) {
                        if (i == 0 || j == 0) {
                            return true;
                        }
                    }

                    if ((x == N || y == M)) {
                        if (i == 0 && j == 0) {
                            return true;
                        }
                    }

                }

            }

        }

        return false;
    }

    public static boolean right(String sentence1, String sentence2) {
        if (sentence1.length() < sentence2.length()) {
            String tmp = sentence1;
            sentence1 = sentence2;
            sentence2 = tmp;
        }
        String[] sp1 = sentence1.split(" "), sp2 = sentence2.split(" ");
        int i = 0, j = 0, n1 = sp1.length, n2 = sp2.length;

        // 相似模式1：短句是长句的前缀
        while (i < n1 && j < n2 && sp1[i].equals(sp2[j])) {
            ++i;
            ++j;
        }
        if (j == n2) return true;
        int half = j; // 记录前缀的结尾位置

        // 相似模式2：短句是长句的后缀
        i = n1 - 1;
        j = n2 - 1;
        while (i >= 0 && j >= 0 && sp1[i].equals(sp2[j])) {
            --i;
            --j;
        }
        if (j == -1) return true;

        // 相似模式3：长句的开头是短句的一半并且长句的结尾是短句的另一半
        if (half > j) return true;

        return false;
    }

}
