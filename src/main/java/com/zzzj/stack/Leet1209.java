package com.zzzj.stack;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-12-16 10:45
 */
public class Leet1209 {

    public static void main(String[] args) {
        int N = 10000;

        for (int i = 0; i < N; i++) {

            String str = LeetUtils.randomString(200, false);

            int k = LeetUtils.random.nextInt(str.length()) + 2;

            if (!Objects.equals(removeDuplicates(str, k), right(str, k))) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(k);
                System.out.println(removeDuplicates(str, k));
                System.out.println(right(str, k));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static String removeDuplicates(String s, int k) {
        int N = s.length();

        LinkedList<Info> list = new LinkedList<>();

        for (int i = 0; i < N; ) {
            char c = s.charAt(i);

            int j = i + 1;

            int count = 1;

            while (j < N && s.charAt(j) == c) {
                count++;
                j++;
            }

            if (count >= k) {
                count %= k;
            }

            if (count > 0) {


                if (!list.isEmpty() && list.peekLast().c == c) {
                    Info prev = list.peekLast();

                    count += prev.count;

                    if (count >= k) {
                        count %= k;
                    }
                    if (count == 0) {
                        list.removeLast();
                    } else {
                        prev.count = count;
                    }
                } else {
                    list.add(new Info(c, count));
                }
            }

            i = j;
        }

        return list.stream()
                .map(Objects::toString)
                .collect(Collectors.joining());
    }

    private static class Info {
        char c;
        int count;

        public Info(char c, int count) {
            this.c = c;
            this.count = count;
        }

        @Override
        public String toString() {

            StringBuilder builder = new StringBuilder(count);

            for (int i = 0; i < count; i++) {
                builder.append(c);
            }

            return builder.toString();
        }
    }

    public static String right(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int length = -1;
        while (length != sb.length()) {
            length = sb.length();
            for (int i = 0, count = 1; i < sb.length(); ++i) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count = 1;
                } else if (++count == k) {
                    sb.delete(i - k + 1, i + 1);
                    break;
                }
            }
        }
        return sb.toString();
    }

}
