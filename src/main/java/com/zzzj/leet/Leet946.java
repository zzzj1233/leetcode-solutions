package com.zzzj.leet;

import java.util.LinkedList;

public class Leet946 {


    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));

        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));

        System.out.println(validateStackSequences(new int[]{2, 1, 3, 0}, new int[]{1, 0, 3, 2}));
    }

    // [2,1,3,0]
    // [1,0,3,2]
    public static boolean validateStackSequences(int[] pushed, int[] popped) {

        LinkedList<Integer> queue = new LinkedList<>();

        int N = pushed.length;

        int popIndex = 0;

        for (int i = 0; i < N; i++) {
            queue.addLast(pushed[i]);

            while (!queue.isEmpty() && queue.peekLast() == popped[popIndex]) {
                popIndex++;
                queue.removeLast();
            }

        }

        return queue.isEmpty();
    }

}
