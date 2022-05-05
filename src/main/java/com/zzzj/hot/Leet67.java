package com.zzzj.hot;

import cn.hutool.core.util.StrUtil;
import com.zzzj.leet.LeetUtils;


/**
 * @author zzzj
 * @create 2022-04-25 15:39
 */
public class Leet67 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String a = LeetUtils.randomString0("01", LeetUtils.random.nextInt(100));
            String b = LeetUtils.randomString0("01", LeetUtils.random.nextInt(100));

            a = StrUtil.removePrefix(a, "0");
            b = StrUtil.removePrefix(b, "0");

            if (!right(a, b).equals(addBinary(a, b))) {
                System.out.println(a);
                System.out.println(b);
                System.out.println(addBinary(a, b));
                return;
            }
        }
    }

    public static String right(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

    public static String addBinary(String a, String b) {
        //  111
        //  111
        // 1110

        //  1111
        //   111
        // 10110

        int add = 0;

        char[] char1 = a.toCharArray();
        char[] char2 = b.toCharArray();

        int len = Math.min(char1.length, char2.length);

        StringBuilder ans = new StringBuilder(Math.max(char1.length, char2.length) + 1);

        for (int i = 0; i < len; i++) {
            int c1 = char1[i] - '0';
            int c2 = char2[i] - '0';

            int sum = c1 + c2 + add;

            ans.append(sum % 2);

            add = sum > 1 ? 1 : 0;
        }

        for (int i = len; i < char1.length; i++) {
            int c1 = char1[i] - '0';
            int c2 = 0;
            int sum = c1 + c2 + add;

            ans.append(sum % 2);

            add = sum > 1 ? 1 : 0;
        }

        for (int i = len; i < char2.length; i++) {
            int c1 = 0;
            int c2 = char2[i] - '0';
            int sum = c1 + c2 + add;

            ans.append(sum % 2);

            add = sum > 1 ? 1 : 0;
        }

        if (add > 0) {
            ans.append("1");
        }

        return ans.reverse().toString();
    }


}
