package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-05-12 16:27
 */
public class Leet771 {

    public int numJewelsInStones(String jewels, String stones) {

        boolean[] tab = new boolean[26];

        for (int i = 0; i < jewels.length(); i++) tab[jewels.charAt(i) - 'a'] = true;

        int ans = 0;

        for (int i = 0; i < stones.length(); i++) if (tab[stones.charAt(i) - 'a']) ans++;

        return ans;
    }


}
