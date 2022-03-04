package com.zzzj.backtracking;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-04 16:12
 */
public class Leet638 {

    public static void main(String[] args) {
        System.out.println(shoppingOffers(Arrays.asList(2, 5), Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10)), Arrays.asList(3, 2)));

        System.out.println(shoppingOffers(Arrays.asList(2, 3, 4), Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9)), Arrays.asList(1, 2, 1)));

        System.out.println(shoppingOffers(Arrays.asList(4, 3, 2, 9, 8, 8), Arrays.asList(Arrays.asList(1, 5, 5, 1, 4, 0, 18), Arrays.asList(3, 3, 6, 6, 4, 2, 32)), Arrays.asList(6, 5, 5, 6, 4, 1)));
    }

    public static int ans;

    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        ans = Integer.MAX_VALUE;

        dfs(toArr(price), toArr2(special), toArr(needs), 0, 0);

        return ans;
    }

    public static void dfs(int[] price, int[][] special, int[] needs, int cost, int index) {
        if (match(needs)) {
            ans = Math.min(ans, cost);
            return;
        }

        for (int i = 0; i < needs.length; i++) {
            if (needs[i] <= 0) {
                continue;
            }
            // 或者买各种大礼包
            OUTER:
            for (int j = index; j < special.length; j++) {
                int[] giftBag = special[j];

                // 礼包中没有当前需要的
                if (giftBag[i] == 0) {
                    continue OUTER;
                }

                int buyCount = 0;

                WHILE:
                while (true) {
                    for (int k = 0; k < giftBag.length - 1; k++) {
                        if (giftBag[k] * (buyCount + 1) > needs[k]) {
                            break WHILE;
                        }
                    }
                    buyCount++;
                }

                for (int m = 1; m <= buyCount; m++) {

                    for (int k = 0; k < giftBag.length - 1; k++) {
                        needs[k] -= giftBag[k] * m;
                    }

                    dfs(price, special, needs, cost + giftBag[giftBag.length - 1] * m, j + 1);

                    for (int k = 0; k < giftBag.length - 1; k++) {
                        needs[k] += giftBag[k] * m;
                    }

                }
            }

            // 原价买
            int count = needs[i];
            needs[i] = 0;
            dfs(price, special, needs, cost + count * price[i], index);
            needs[i] = count;
        }

    }

    public static int[][] toArr2(List<List<Integer>> list) {
        int[][] result = new int[list.size()][];

        Iterator<List<Integer>> iterator = list.iterator();

        int index = 0;

        while (iterator.hasNext()) {
            result[index++] = toArr(iterator.next());
        }

        return result;
    }

    public static int[] toArr(List<Integer> list) {
        int[] result = new int[list.size()];

        Iterator<Integer> iterator = list.iterator();

        int index = 0;

        while (iterator.hasNext()) {
            result[index++] = iterator.next();
        }

        return result;
    }

    public static boolean match(int[] needs) {
        for (int need : needs) {
            if (need > 0) {
                return false;
            }
        }
        return true;
    }

}
