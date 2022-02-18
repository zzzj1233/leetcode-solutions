package com.zzzj.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-12-06 15:49
 */
public class Leet1475 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(finalPrices(new int[]{10, 1, 1, 6})));
    }

    /**
     * 给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
     * <p>
     * 商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。
     * <p>
     * 请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
     * <p>
     * 输入：prices = [8,4,6,2,3]
     * 输出：[4,2,4,2,3]
     * 解释：
     * 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
     * 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
     * 商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
     * 商品 3 和 4 都没有折扣。
     */
    public static int[] finalPrices(int[] prices) {
            int[] answer = new int[prices.length];

            LinkedList<Integer> singleStack = new LinkedList<>();

            // 最小栈
            for (int i = 0; i < prices.length; i++) {
                while (!singleStack.isEmpty() && prices[i] <= prices[singleStack.peekLast()]) {
                    Integer last = singleStack.removeLast();
                    answer[last] = prices[last] - prices[i];
                }
                singleStack.addLast(i);
                answer[i] = prices[i];
            }

            return answer;
    }

}
