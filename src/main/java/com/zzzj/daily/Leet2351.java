package com.zzzj.daily;

public class Leet2351 {

    public static char repeatedCharacter(String s) {
        int[] tab = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (++tab[c] == 2) {
                return c;
            }
        }
        return 0;
    }

}
