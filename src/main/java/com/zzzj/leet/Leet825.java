package com.zzzj.leet;


import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-13 18:08
 */
public class Leet825 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            final int[] arr = ArrayUtil.generateArray(4, 1, 110);

            if (numFriendRequests(arr) != right(arr)) {
                System.out.println("Error");
                Arrays.sort(arr);
                System.out.println(Arrays.toString(arr));
                System.out.println(numFriendRequests(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }


    // ages[y] <= 0.5 * ages[x] + 7     : 比自己小很多
    // ages[y] > ages[x]                : 比自己大
    // ages[y] > 100 && ages[x] < 100   : 100岁以下的不会和100岁以上的交朋友
    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);

        int N = ages.length;

        int ans = 0;

        for (int i = 0; i < N; ) {
            if (ages[i] < 15) {
                i++;
                continue;
            }
            int result = search(ages, 0, i, ages[i] / 2 + 7);

            int sameIndex = skipSame(ages, i);

            int same = sameIndex - i + 1;

            if (same > 1) {
                ans += same * result + same * (same - 1);
            } else {
                ans += result;
            }

            i = sameIndex + 1;
        }

        return ans;
    }

    public static int skipSame(int[] ages, int index) {
        int L = index;
        int R = ages.length - 1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (ages[mid] == ages[index]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return L == index ? index : L - 1;
    }

    // 必须 > limit
    public static int search(int[] ages, int left, int right, int limit) {
        int L = left;
        int R = right;

        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (ages[mid] > limit) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        if (R == right) {
            return 0;
        }
        return right - R;
    }

    public static int right(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {
                ++left;
            }
            while (right + 1 < n && ages[right + 1] <= age) {
                ++right;
            }
            ans += right - left;
        }
        return ans;
    }

}
