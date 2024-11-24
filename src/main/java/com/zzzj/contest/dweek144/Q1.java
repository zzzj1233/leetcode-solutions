package com.zzzj.contest.dweek144;

import com.sun.xml.internal.fastinfoset.util.QualifiedNameArray;

/**
 * @author zzzj
 * @create 2024-11-23 22:30
 */
public class Q1 {


    public static void main(String[] args) {

        System.out.println(canAliceWin(12));

        System.out.println(canAliceWin(1));

        System.out.println(canAliceWin(19));

    }

    public static boolean canAliceWin(int n) {

        boolean alice = true;

        int cnt = 10;

        while (n > 0) {
            if (n < cnt)
                return !alice;
            n -= cnt;
            cnt--;
            alice = !alice;
        }

        return !alice;
    }

}
