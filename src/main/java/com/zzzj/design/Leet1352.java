package com.zzzj.design;

import java.util.ArrayList;
import java.util.List;

public class Leet1352 {


    private static class ProductOfNumbers {

        /**
         * 只需要维护一个前缀积列表即可，初始化为[1]
         * <p>
         * 当加入一个num，可能存在两种情况
         * <p>
         * num为0，此时重新初始化前缀积列表为[1]
         * num不为0，此时将num和列表中最后一项相乘加入列表
         * 当需要返回后k个数字的乘积时，也有两种情况
         * <p>
         * 前缀积列表长度小于等于k，说明后k个数字里肯定存在0，那乘积肯定为0，返回0
         * 前缀积列表长度大于k，那就把最后一项和倒数第k+1项相除，此时得到的是最后k项的乘积，返回
         */

        ArrayList<Integer> preMul = new ArrayList<>();

        public ProductOfNumbers() {
            preMul.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                preMul.clear();
                preMul.add(1);
            } else {
                preMul.add(num * preMul.get(preMul.size() - 1));
            }
        }

        public int getProduct(int k) {
            if (preMul.size() <= k) {
                return 0;
            }
            int last = preMul.size() - 1;

            return preMul.get(last) / preMul.get(last - k);
        }

    }

}
