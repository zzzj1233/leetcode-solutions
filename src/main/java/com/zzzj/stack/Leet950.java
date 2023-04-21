package com.zzzj.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zzzj
 * @create 2023-04-12 17:55
 */
public class Leet950 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(deckRevealedIncreasing(new int[]{17, 13, 11, 2, 3, 5, 7})));
    }

    public static int[] deckRevealedIncreasing(int[] deck) {
        if (deck == null || deck.length < 1) {
            return deck;
        }

        Arrays.sort(deck);// 得到升序排列的数组

        Queue<Integer> queue = new LinkedList<>();

        for (int i = deck.length - 1; i >= 0; i--) {// 倒着遍历，便是按降序访问
            queue.add(deck[i]);// 选最大值插入队列
            if (i == 0) {// 数组中所有元素均在队列中，退出过程
                break;
            }

            queue.add(queue.poll());// 将队头元素插入到队尾中
        }

        for (int i = deck.length - 1; i >= 0; i--) {
            deck[i] = queue.poll();// 倒回去，得到answer
        }

        return deck;
    }

}
