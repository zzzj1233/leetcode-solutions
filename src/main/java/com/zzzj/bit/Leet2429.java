package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-10-11 15:13
 */
public class Leet2429 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int num1 = LeetUtils.random.nextInt(10000) + 1;
            int num2 = LeetUtils.random.nextInt(10000) + 1;

            if (minimizeXor(num1, num2) != right(num1, num2)) {
                System.out.println("Error");
                System.out.println(num1);
                System.out.println(num2);
                System.out.println(minimizeXor(num1, num2));
                System.out.println(right(num1, num2));
                return;
            }

        }

        System.out.println("OK~");


    }

    public static int minimizeXor(int num1, int num2) {
        int c1 = getCount(num1);
        int c2 = getCount(num2);

        if (c1 == c2) {
            return num1;
        }

        if (c1 > c2) {
            int ans = num1;
            for (int i = 0; i < 31 && c1 > c2; i++) {
                if (((ans >> i) & 1) == 1) {
                    ans ^= (1 << i);
                    c1--;
                }
            }
            return ans;
        }

        // c1 < c2
        int ans = num1;

        c2 -= c1;

        for (int i = 0; i < 31 && c2 > 0; i++) {
            if (((ans >> i) & 1) == 0) {
                ans |= (1 << i);
                c2--;
            }
        }

        return ans;
    }

    public static int getCount(int num) {
        int count = 0;
        for (int i = 0; i < 31; i++) {
            if (((num >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    public static int right(int num1, int num2) {
        String s1 = Integer.toBinaryString(num1);
        String s2 = Integer.toBinaryString(num2);
        int count = 0;
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == '1')
                ++count;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            if (count == 0 || ch == '0')
                sb.append('0');
            else {
                sb.append('1');
                --count;
            }
        }
        while (count != 0) {
            int index = sb.lastIndexOf("0");
            if (index < 0) {
                while (count != 0) {
                    sb.insert(0, "1");
                    --count;
                }
                break;
            }
            sb.replace(index, index + 1, "1");
            --count;
        }
        return Integer.valueOf(sb.toString(), 2);
    }

}
