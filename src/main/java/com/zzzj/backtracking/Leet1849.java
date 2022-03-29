package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-03-25 14:52
 */
public class Leet1849 {

    public static void main(String[] args) {
//         basicTest();
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomNumberString(20);
            if (splitString(str) != right(str)) {
                System.out.println(str);
                System.out.println(splitString(str));
                return;
            }
        }
    }

    public static void basicTest() {
        System.out.println(splitString("10009998"));
        System.out.println(splitString("9080701"));
        System.out.println(splitString("050043"));
        System.out.println(splitString("1234"));
    }

    public static boolean ans;

    public static Map<Long, Long> cache;

    public static boolean splitString(String s) {
        cache = new HashMap<>(128);
        ans = false;

        char[] chars = s.toCharArray();

        for (int j = 1; j < chars.length && !ans; j++) {
            dfs(chars, parseLong(chars, 0, j), j);
        }

        return ans;
    }

    public static void dfs(char[] chars, long pre, int index) {
        if (index == chars.length) {
            ans = true;
            return;
        }
        if (ans) {
            return;
        }
        // 尝试匹配pre
        for (int j = index + 1; j <= chars.length; j++) {
            long value = parseLong(chars, index, j);
            if (value == pre - 1) {
                dfs(chars, value, j);
            }
        }
    }

    // (start-end]
    public static long parseLong(char[] chars, int start, int end) {
        long cacheKey = (long) start << 15 | end;

        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        long num = chars[start++] - 48;
        while (start < end) {
            num = (num * 10) + (chars[start] - 48);
            start++;
        }

        cache.put(cacheKey, num);
        return num;
    }


    public static boolean right(String s) {
        long t = 0;     //枚举第一个数字的值，因为s长度为20，所以会超过int，要用long类型
        for (int i = 0; i < s.length() - 1; i++) {  //因为必须要分割成两个子串，所以最后一个字符不可能是组成第一个数字的字符，我们这里也是为了防止刚好20位导致long也会溢出的情况
            t = t * 10 + s.charAt(i) - '0'; //把当前字符加入到组成第一个数字的字符集中
            if (t > 10000000000L)    //如果t大于10^10那么后面最多还有9位数，所以不可能组成递减的连续值
                return false;
            if (dfs2(s, t, i + 1))   //把t当作第一个数字，找寻后面递减的数
                return true;
        }
        return false;
    }

    //s要分割的字符串；pre前面一个数的值；k当前字符串已经用到了哪个位置
    private static boolean dfs2(String s, long pre, int k) {
        if (k == s.length())    //代表能组成递减的连续值
            return true;
        long t = 0;     //枚举pre后面的一个数字的值
        for (int i = k; i < s.length(); i++) {  //从第k个字符开始组成数字
            t = t * 10 + s.charAt(i) - '0';
            if (t > 10000000000L)
                return false;
            if (pre - 1 == t && dfs2(s, t, i + 1))   //如果前面一个数字和当前数组相差为1，则继续往下面寻找满足条件的数组
                return true;
            if (t >= pre)   //当前组成的数大于前面的数表示不符合要求，直接返回false
                return false;
        }
        return false;
    }


}
