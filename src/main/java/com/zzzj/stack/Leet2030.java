package com.zzzj.stack;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-06-20 11:11
 */
public class Leet2030 {

    public static void main(String[] args) {

        int N = 5000;

        int K = 500;

        for (int i = 0; i < 10000; i++) {

            char[] chars = LeetUtils.randomLowerString(N, 10).toCharArray();

            int k = LeetUtils.random.nextInt(K) + 1;

            int rep = Math.min(
                    k,
                    LeetUtils.random.nextInt(K) + 1
            );

            char letter = chars[LeetUtils.random.nextInt(N)];

            while (true) {
                int cnt = 0;
                for (char c : chars) {
                    if (c == letter)
                        cnt++;
                }
                if (cnt >= rep)
                    break;
                int index = LeetUtils.random.nextInt(N);
                if (chars[index] != letter)
                    chars[index] = letter;
            }

            String str = new String(chars);

            String r = smallestSubsequence(str, k, letter, rep);
            String rr = right(str, k, letter, rep);

            if (!rr.equals(r)) {

                int[] w = new int[N];

                for (int j = 0; j < str.length(); j++) {
                    w[j] = str.charAt(j) - 'a';
                }

                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("str = " + str);
                System.out.println("w = " + Arrays.toString(w));
                System.out.println("k = " + k);
                System.out.println("letter = " + letter);
                System.out.println("rep = " + rep);
                return;
            }

        }

        System.out.println("Ok");

    }

    public static final char[] CHARS = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String smallestSubsequence(String s, int k, char letter, int repetition) {

        LinkedList<Integer> stack = new LinkedList<>();

        int N = s.length();

        int[] cnt = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            cnt[i] = cnt[i + 1] + (s.charAt(i) == letter ? 1 : 0);
        }

        int stackCnt = 0;

        for (int i = 0; i < N; i++) {

            char c = s.charAt(i);

            boolean normal = c != letter;

            while (!stack.isEmpty()) {

                // 1. 检查长度是否足够
                if (N - i + (stack.size() - 1) < k)
                    break;

                char last = s.charAt(stack.peekLast());

                boolean lastIs = last == letter;

                // 2. 普通字符的处理
                if (normal) {

                    if (last <= c)
                        break;

                    // 判断letter出栈后, 栈内有多少个letter, 并且后面满足 k - stack[letter].size()
                    if (lastIs) {
                        if (stackCnt - 1 + cnt[i] < repetition)
                            break;

                        // 后续的letter + 栈中的letter足够repetition: 那么这个letter可以出栈
                        stackCnt--;
                    }

                    // 普通字符, 并且比当前字符大, 直接出栈
                    stack.removeLast();
                } else { // 3. letter字符的处理

                    // 3.1 如果出栈的字母 > 当前字母, 直接出栈
                    if (last > c) {
                        stack.removeLast();
                    } else if (last < c) {
                        int free = k - stack.size();
                        int needLetter = repetition - stackCnt;
                        if (free < needLetter) {
                            stack.removeLast();
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }

            }

            if (!normal)
                stackCnt++;

            stack.add(i);
        }

        StringBuilder builder = new StringBuilder(k);

        for (int i = 0; i < k; i++)
            builder.append(s.charAt(stack.removeFirst()));

        return builder.toString();
    }

    public static String right(String s, int k, char letter, int repetition) {
        int n = s.length();

        int[] suffix_cnt = new int[n];
        int acc = 0;
        for (int i = n - 1; i > -1; i--) {
            if (s.charAt(i) == letter) {
                acc++;
            }
            suffix_cnt[i] = acc;
        }

        Deque<Character> stk = new LinkedList<>();
        int x = 0;
        for (int i = 0; i < n; i++) {
            int y = x;
            //----弹栈
            while (!stk.isEmpty() && stk.size() + (n - i - 1) >= k && s.charAt(i) < stk.peekLast()) {
                if (stk.peekLast() == letter) {
                    --y;
                }
                if (y + suffix_cnt[i] < repetition) {
                    break;
                }
                stk.pollLast();
                x = y;
            }
            //----压栈
            if (stk.size() < k) {
                if (s.charAt(i) == letter || (k - stk.size() > repetition - x)) {
                    stk.addLast(s.charAt(i));
                    if (s.charAt(i) == letter) {
                        ++x;
                    }
                }
            }
        }

        StringBuilder SB = new StringBuilder();
        while (!stk.isEmpty()) {
            SB.append(stk.pollFirst());
        }
        return SB.toString();
    }


}
