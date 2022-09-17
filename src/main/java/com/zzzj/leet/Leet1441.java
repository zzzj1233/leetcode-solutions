package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

public class Leet1441 {

    private static final String PUSH = "Push";

    private static final String POP = "Pop";

    public static void addNonexistenceNum(List<String> list) {
        list.add(PUSH);
        list.add(POP);
    }

    public static void addExistenceNum(List<String> list) {
        list.add(PUSH);
    }

    public static List<String> buildArray(int[] target, int n) {
        int cur = 1;

        List<String> ans = new ArrayList<>(target.length << 1);

        for (int i = 0; i < target.length; i++) {
            int num = target[i];
            while (cur < num) {
                addNonexistenceNum(ans);
                cur++;
            }
            addExistenceNum(ans);
            cur++;
        }

        return ans;
    }

}
