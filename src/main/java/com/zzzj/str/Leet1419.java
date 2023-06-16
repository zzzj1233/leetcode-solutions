package com.zzzj.str;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-06-14 18:27
 */
public class Leet1419 {

    public static void main(String[] args) {
        System.out.println(minNumberOfFrogs("crcoakroak"));

        System.out.println(minNumberOfFrogs("croakcrook"));
    }

    public static int minNumberOfFrogs(String croakOfFrogs) {

        int N = croakOfFrogs.length();

        LinkedList<Integer>[] lists = new LinkedList[5];

        for (int i = 0; i < 5; i++) {
            lists[i] = new LinkedList<>();
        }

        int ans = 0;

        // croak
        for (int i = 0; i < N; i++) {

            char c = croakOfFrogs.charAt(i);

            int index = index(c);

            int preIndex = index - 1;

            if (preIndex >= 0) {

                if (lists[preIndex].isEmpty()) return -1;

                Integer firstIndex = lists[preIndex].removeFirst();

            }

            if (index != 4) lists[index].addLast(index);

            int size = 0;

            for (LinkedList<Integer> list : lists) size += list.size();

            ans = Math.max(ans, size);
        }

        for (LinkedList<Integer> list : lists) if (!list.isEmpty()) return -1;

        return ans;
    }

    public static int index(char c) {
        switch (c) {
            case 'c':
                return 0;
            case 'r':
                return 1;
            case 'o':
                return 2;
            case 'a':
                return 3;
            case 'k':
            default:
                return 4;
        }
    }


}
