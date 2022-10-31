package com.zzzj.dpoint;


/**
 * @author zzzj
 * @create 2021-12-21 16:46
 */
public class Leet80 {


    // 每个重复的元素最多出现两次
    public static int removeDuplicates(int[] nums) {

        int ansIdx = 0;

        int N = nums.length;

        int curNum = nums[0];
        int count = 0;

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (num == curNum) {
                count++;
                if (count <= 2) {
                    nums[ansIdx++] = num;
                } else {
                    while (i < N && nums[i] == curNum) {
                        i++;
                    }
                    if (i < N) {
                        i--;
                    }
                }
            } else {
                count = 1;
                curNum = num;
                nums[ansIdx++] = num;
            }
        }

        return ansIdx;
    }

}
