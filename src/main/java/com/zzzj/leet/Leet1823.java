package com.zzzj.leet;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-10-07 21:12
 */
public class Leet1823 {

    public static void main(String[] args) {
        System.out.println(findTheWinner(5, 2));
        System.out.println(findTheWinner(3, 1));
    }

    public static int findTheWinner(int n, int k) {

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        int index = 1;

        while (queue.size() > 1) {
            Iterator<Integer> it = queue.iterator();

            while (it.hasNext() && queue.size() > 1) {
                it.next();
                if (index % k == 0) {
                    it.remove();
                }
                index++;
            }

        }

        return queue.peekFirst();
    }


}
