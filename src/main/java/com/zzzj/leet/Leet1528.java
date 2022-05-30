package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-30 18:44
 */
public class Leet1528 {


    public static void main(String[] args) {
        System.out.println(restoreString("codeleet", new int[]{4, 5, 6, 7, 0, 2, 1, 3}));
    }

    public static String restoreString(String s, int[] indices) {

        char[] chars = s.toCharArray();
        char[] ans = new char[chars.length];

        for (int i = 0; i < indices.length; i++) {
            ans[indices[i]] = chars[i];
        }

        return new String(ans);
    }


}
