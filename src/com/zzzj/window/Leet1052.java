package com.zzzj.window;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Zzzj
 * @create 2021-12-18 15:52
 */
public class Leet1052 {

    public static void main(String[] args) {
        int[] customers = ArrayUtil.generateArray(10, 0, 10);
        int[] grumpy = ArrayUtil.generateArray(10, 0, 1);
        Random random = new Random();
        int minutes = random.nextInt(customers.length);

        System.out.println(Arrays.toString(customers));
        System.out.println(Arrays.toString(grumpy));
        System.out.println(minutes);

        System.out.println(maxSatisfied(customers, grumpy, minutes));
    }

    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {

        int l = -1;
        int r = 0;

        int ans = 0;
        int add = 0;
        int max = 0;

        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 1) {
                // l未初始化
                if (l == -1) {
                    l = i;
                    r = l + minutes - 1;
                    // l已经初始化了
                } else {
                    // 已越界
                    if (i > r) {
                        for (int j = 0; j < i - r; j++) {
                            if (grumpy[l + j] == 1) {
                                add -= customers[l + j];
                            }
                        }
                        l += i - r;
                        r = i;
                        // 未越界
                    }
                }
                add += customers[i];
                max = Math.max(max, add);
            } else {
                ans += customers[i];
            }
        }

        return ans + max;
    }

}
