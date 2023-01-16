package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leet1801 {

    public static void main(String[] args) {
        System.out.println(getNumberOfBacklogOrders(LeetUtils.convertInts("[[10,5,0],[15,2,1],[25,1,1],[30,4,0]]")));
        System.out.println(getNumberOfBacklogOrders(LeetUtils.convertInts("[[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]")));
    }

    public static int getNumberOfBacklogOrders(int[][] orders) {
        // 阅读理解题，读着确实难受，不过还挺有意义的。
        //其实题目描述的就是“价格优先”的竞价规则。
        //所谓“价格优先”，就是优先成交“出价最高”的“买方”和“出价最低”的“卖方”。
        //股票市场以及steam里的市场都将这个原则放在第一位。
        //所谓“积压订单”，就是一个订单池，买方出价和卖方要价没有重叠（否则立即成交）。
        //我们要根据价格优先原则维护这些订单的“优先级”，很自然地想到堆（优先队列）。
        //买方出价越高，优先级越高，所以用大顶堆维护，与之相反，卖方订单用小顶堆维护。
        //每遇到一个新的订单，就与订单池中另一方的优先级最高的订单（堆顶）比较，并尝试进行交易，直到没有订单可以交易。
        //维护订单池中的订单数，根据出入调整，最后返回即可。 剩下的都在注释里了，C++ 184ms

        PriorityQueue<int[]> buyQueue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        PriorityQueue<int[]> sellQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        int ans = 0;

        final int MOD = 1000000007;

        for (int[] order : orders) {
            int price = order[0];
            int count = order[1];
            int type = order[2];

            if (type == 0) { // buy
                while (!sellQueue.isEmpty() && sellQueue.peek()[0] <= price) {
                    int[] top = sellQueue.remove();
                    if (top[1] > count) {
                        top[1] -= count;
                        sellQueue.add(top);
                        count = 0;
                        break;
                    } else if (top[1] < count) {
                        count -= top[1];
                    } else {
                        count = 0;
                        break;
                    }
                }

                if (count > 0) {
                    buyQueue.add(new int[]{price, count});
                }
            } else { // sell
                while (!buyQueue.isEmpty() && buyQueue.peek()[0] >= price) {
                    int[] top = buyQueue.remove();
                    if (top[1] > count) {
                        top[1] -= count;
                        buyQueue.add(top);
                        count = 0;
                        break;
                    } else if (top[1] < count) {
                        count -= top[1];
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count > 0) {
                    sellQueue.add(new int[]{price, count});
                }
            }
        }

        while (!sellQueue.isEmpty()){
            ans += sellQueue.remove()[1];
            ans %= MOD;
        }

        while (!buyQueue.isEmpty()){
            ans += buyQueue.remove()[1];
            ans %= MOD;
        }

        return ans;
    }

}
