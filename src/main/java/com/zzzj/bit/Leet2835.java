package com.zzzj.bit;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-11-07 15:46
 */
public class Leet2835 {

    public static void main(String[] args) {

        System.out.println(minOperations(Arrays.asList(1, 2, 8), 7));

        System.out.println(minOperations(Arrays.asList(1, 32, 1, 2), 12));

        System.out.println(minOperations(Arrays.asList(1, 32, 1), 35));

    }

    public static int minOperations(List<Integer> nums, int target) {

        int[] tab = new int[33];

        for (Integer num : nums)
            tab[power(num)]++;

        int ans = 0;

        for (int i = 0; i <= 31; i++) {

            if ((target & (1 << i)) != 0) {

                if (tab[i] == 0) {

                    for (int x = i + 1; x <= 31; x++) {

                        if (tab[x] > 0) {

                            tab[x]--;

                            for (int y = x - 1; y >= i; y--) {
                                tab[y] += 1;
                                ans++;
                            }

                            tab[i] += 1;

                            break;
                        }

                    }

                }

                if (tab[i] == 0)
                    return -1;

                tab[i]--;
            }

            tab[i + 1] += tab[i] / 2;
        }

        return ans;
    }

    private static int power(int num) {
        int power = 0;
        while (num > 1) {
            num >>= 1;
            power++;
        }
        return power;
    }

}
