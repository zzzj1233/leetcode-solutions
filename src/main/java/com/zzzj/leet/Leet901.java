package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-06-22 15:03
 */
public class Leet901 {

    public static void main(String[] args) {
        StockSpanner spanner = new StockSpanner();

        /**
         * S.next(100) 被调用并返回 1
         * S.next(80) 被调用并返回 1
         * S.next(60) 被调用并返回 1
         * S.next(70) 被调用并返回 2
         * S.next(60) 被调用并返回 1
         * S.next(75) 被调用并返回 4
         * S.next(85) 被调用并返回 6
         */
        System.out.println(spanner.next(100));
        System.out.println(spanner.next(80));
        System.out.println(spanner.next(60));
        System.out.println(spanner.next(70));
        System.out.println(spanner.next(60));
        System.out.println(spanner.next(75));
        System.out.println(spanner.next(85));
    }

    private static class StockSpanner {

        private LinkedList<int[]> stack;

        public StockSpanner() {
            stack = new LinkedList<>();
        }

        // 递减单调栈
        public int next(int price) {
            int ans = 0;

            while (!stack.isEmpty() && price >= stack.peekLast()[0]) {
                // + 1 = 当前remove掉的元素
                ans += stack.removeLast()[1] + 1;
            }

            stack.add(new int[]{price, ans});

            // + 1 = 自己
            return ans + 1;
        }

    }

}
