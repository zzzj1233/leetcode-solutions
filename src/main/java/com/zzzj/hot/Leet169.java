package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-19 17:38
 */
public class Leet169 {

    public static int majorityElement(int[] nums) {
        // 两个不同的数就删除
        int cur = nums[0];
        int count = 1;


        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == cur) {
                count++;
            } else {
                if (--count == 0){
                    count = 1;
                    cur = nums[i + 1];
                    i++;
                }
            }
        }

        return cur;
    }

}
