package com.zzzj.leet;

import com.zzzj.util.Unresolved;

/**
 * @author zzzj
 * @create 2022-05-11 11:52
 */
@Unresolved
public class Leet260 {

    // 取异或值最后一个二进制位为 1 的数字作为 mask，如果是 1 则表示两个数字在这一位上不同。
    // int mask = xor & (-xor);
    // 第三步：
    // 通过与这个 mask 进行与操作，如果为 0 的分为一个数组，为 1 的分为另一个数组。这样就把问题降低成了：“有一个数组每个数字都出现两次，有一个数字只出现了一次，求出该数字”。对这两个子问题分别进行全异或就可以得到两个解。也就是最终的数组了。
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 2, 3, 3, 4, 5, 6, 6};

        int xor = nums[0];

        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }

        //

        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(-7));
    }

    public static int[] singleNumber(int[] nums) {


        return new int[]{};
    }

}
