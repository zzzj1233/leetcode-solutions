package com.zzzj.greedy;

import java.util.LinkedList;

public class Leet1989 {

    public static void main(String[] args) {
        System.out.println(catchMaximumAmountofPeople(new int[]{0, 1, 0, 1, 0}, 3));
        System.out.println(catchMaximumAmountofPeople(new int[]{1, 1, 0, 1, 0, 1}, 2));
    }

    // [1,1,0,1,0,1]
    // 2
    public static int catchMaximumAmountofPeople(int[] team, int dist) {
        LinkedList<Integer> list = new LinkedList<>();

        int N = team.length;

        for (int i = 0; i < N; i++) {
            if (team[i] == 0) {
                list.add(i);
            }
        }

        int ans = 0;

        for (int i = 0; i < N && !list.isEmpty(); i++) {
            if (team[i] == 1) {
                int left = i - dist;
                int right = i + dist;

                while (!list.isEmpty() && list.peekFirst() < left) {
                    list.removeFirst();
                }

                if (list.isEmpty()) {
                    break;
                }

                Integer first = list.peekFirst();

                if (first >= left && first <= right) {
                    list.removeFirst();
                    ans++;
                }
            }
        }

        return ans;
    }

}
