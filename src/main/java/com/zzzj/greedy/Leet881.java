package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-13 15:15
 */
public class Leet881 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(600, 1, 100);
            int limit = LeetUtils.random.nextInt(100) + 1;
            if (numRescueBoats(arr, limit) != right(arr, limit)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(limit);
                System.out.println(numRescueBoats(arr, limit));
                System.out.println(right(arr, limit));
                return;
            }
        }
        System.out.println("Ok");
    }

    // [5,1,4,2]
    // 6

    // 1 2 4 5

    // 每艘船最多乘两个人,体重不能超过limit,求最少要多少艘船
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int ans = 0;

        int N = people.length;

        int L = 0;
        int R = N - 1;

        while (L <= R) {
            if (people[L] > limit) {
                return ans + (R - L + 1);
            }

            while (R > L && people[L] + people[R] > limit) {
                R--;
                ans++;
            }

            if (R == L) {
                return ans + 1;
            }

            ans++;
            L++;
            R--;
        }

        return ans;
    }

    public static int right(int[] people, int limit) {
        int res = 0;
        int right = people.length - 1;
        int left = 0;
        Arrays.sort(people);
        while (left <= right) {
            if (left == right) {
                res++;      // 只剩下最后一个,直接一个走,结束
                break;
            }
            if (people[left] + people[right] > limit) {
                res++;
                right--;        // 先载最重的, 而且最小的也无法一起载,那么就最重的单独走
            } else {
                res++;
                right--;        // 最重的与最轻的一起走
                left++;
            }
        }
        return res;
    }

}
