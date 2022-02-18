package com.zzzj.window;


import java.util.Random;

/**
 * @author zzzj
 * @create 2021-12-20 12:12
 */
public class Leet2024 {

    private static String randomTF(int len) {
        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (random.nextInt(2) % 2 == 0) {
                stringBuilder.append("T");
            } else {
                stringBuilder.append("F");
            }
        }

        return stringBuilder.toString();
    }


    public static void main(String[] args) {
//        final String s = randomTF(10);
//        final int k = LeetUtils.random.nextInt(s.length() / 2 + 1);
//
//        System.out.println(s);
//
//        System.out.println(k);

        System.out.println(maxConsecutiveAnswers("FFTFF", 1));
    }


    // 左右指针确定一个窗口，计算该窗口内T和F的数量，当其中一个小于k的时候，我们可以将那个字母全部改为另一个，从而使窗口中的所有字母相同，因此此时的窗口大小即为一个备选答案，遍历整个字符串得到最大值，就是我们要找的答案。
    // 1）右指针滑动，计算窗口内T和F的数量
    // 2）如果T和F的数量均大于k，即无法通过修改其中一个字母来使窗口内所有字母相同，需要移动左指针
    // 3）左指针移动完毕，此时窗口大小可以作为答案，和当前的最大值比较并更新
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();

        char[] c = answerKey.toCharArray();

        int left = 0, right = 0;

        int numT = 0, numF = 0;

        int ans = 0;

        // TTFTF
        // 1
        while (right < n) {
            if (c[right] == 'T')
                numT++;
            else
                numF++;
            while (numT > k && numF > k) {
                if (c[left] == 'T')
                    numT--;
                else
                    numF--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }


}
