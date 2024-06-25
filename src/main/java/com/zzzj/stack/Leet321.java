package com.zzzj.stack;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-06-20 12:05
 */
public class Leet321 {

    public static void main(String[] args) {

        System.out.println(
                Arrays.toString(maxNumber(
                        new int[]{6, 7},
                        new int[]{6, 0, 4},
                        5
                ))
        );

        System.exit(0);

        int N = 500;

        for (int i = 0; i < 1000; i++) {

            int[] arr1 = ArrayUtil.generateArray(N, 0, N);

            int[] arr2 = ArrayUtil.generateArray(N, 0, N);

            int k = LeetUtils.random.nextInt(N);

            int[] r = maxNumber(arr1, arr2, k);

            int[] rr = new Solution().maxNumber(arr1, arr2, k);

            if (!Arrays.equals(r, rr)) {
                System.out.println("r = " + Arrays.toString(r));
                System.out.println("rr = " + Arrays.toString(rr));
                System.out.println("arr1 = " + Arrays.toString(arr1));
                System.out.println("arr2 = " + Arrays.toString(arr2));
                System.out.println("k = " + k);
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int N = nums1.length;

        int M = nums2.length;

        int[] ans = new int[k];

        Arrays.fill(ans, -1);

        for (int i = 0; i <= k; i++) {
            if (i > nums1.length || k - i > nums2.length)
                continue;

            int[] arr1 = pick(nums1, i);

            int[] arr2 = pick(nums2, k - i);

            int[] merged = merge(arr1, arr2, k);

            if (compare(ans, merged, 0, 0) > 0)
                ans = merged;
        }

        return ans;
    }

    public static int[] merge(
            int[] arr1,
            int[] arr2,
            int k
    ) {

        int[] r = new int[k];

        int i = 0;

        int j = 0;

        int index = 0;

        while (index < k) {

            if (i >= arr1.length) {
                r[index] = arr2[j++];
            } else if (j >= arr2.length) {
                r[index] = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                r[index] = arr1[i++];
            } else if (arr1[i] < arr2[j]) {
                r[index] = arr2[j++];
            } else {
                if (compare(arr1, arr2, i + 1, j + 1) > 0)
                    r[index] = arr2[j++];
                else
                    r[index] = arr1[i++];
            }

            // 当两个数一样大时, 哪个数组后面的数更近的更大, 就取哪个数组

            index++;
        }

        return r;
    }

    public static int[] pick(int[] nums, int k) {
        if (k == 0)
            return new int[0];

        LinkedList<Integer> stack = new LinkedList<>();

        int N = nums.length;

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && stack.peekLast() < nums[i] && stack.size() + (N - i - 1) >= k)
                stack.removeLast();

            stack.add(nums[i]);
        }

        return stack.stream().mapToInt(value -> value).toArray();
    }

    public static int compare(int[] nums1, int[] nums2, int i, int j) {
        for (; i < nums1.length && j < nums2.length; i++, j++) {
            if (nums1[i] > nums2[j])
                return -1;
            if (nums1[i] < nums2[j])
                return 1;
        }

        return i == nums1.length ? 1 : -1;
    }


    private static class Solution {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int n1 = nums1.length, n2 = nums2.length;
            int a1 = Math.max(0, k - n2);
            int a2 = Math.min(n1, k);
            int[] res = new int[k];
            Arrays.fill(res, -1);
            for (int i = a1; i <= a2; ++i) {
                int[] ans1 = find(nums1, i);
                int[] ans2 = find(nums2, k - i);
                int[] ans = merge(ans1, ans2);
                if (compare(res, ans) < 0) {
                    res = ans;
                }
            }
            return res;
        }

        private int[] find(int[] nums1, int m) {
            int[] ans = new int[m];
            int n = nums1.length;
            int l = 0, r = n - m, remain = m;
            while (remain > 0) {
                int loc = findMaxLoc(nums1, l, r);
                ans[m - remain] = nums1[loc];
                l = loc + 1;
                r = n - --remain;
            }
            return ans;
        }

        private int findMaxLoc(int[] nums, int l, int r) {
            int ans = -1, max = -1;
            for (int i = l; i <= r; ++i) {
                if (nums[i] > max) {
                    ans = i;
                    max = nums[i];
                }
            }
            return ans;
        }

        private int[] merge(int[] ans1, int[] ans2) {
            int l1 = 0, l2 = 0, l = 0;
            int r1 = ans1.length - 1, r2 = ans2.length - 1;
            int[] ans = new int[r1 + r2 + 2];
            while (l1 <= r1 && l2 <= r2) {
                if (ans1[l1] > ans2[l2]) {
                    ans[l++] = ans1[l1++];
                } else if (ans1[l1] < ans2[l2]) {
                    ans[l++] = ans2[l2++];
                } else {
                    int s1 = l1, s2 = l2;
                    boolean flag = false;
                    while (s1 <= r1 && s2 <= r2) {
                        if (ans1[s1] > ans2[s2]) {
                            flag = true;
                            break;
                        } else if (ans1[s1] < ans2[s2]) {
                            break;
                        }
                        s1++;
                        s2++;
                    }
                    if (s2 > r2) {
                        flag = true;
                    }
                    if (flag) {
                        ans[l++] = ans1[l1++];
                    } else {
                        ans[l++] = ans2[l2++];
                    }

                }
            }
            while (l1 <= r1) {
                ans[l++] = ans1[l1++];
            }
            while (l2 <= r2) {
                ans[l++] = ans2[l2++];
            }
            return ans;
        }

        private int compare(int[] target, int[] challenger) {
            int n = target.length;
            for (int i = 0; i < n; ++i) {
                if (target[i] < challenger[i]) {
                    return -1;
                } else if (target[i] > challenger[i]) {
                    return 1;
                }
            }
            return 0;
        }
    }
}
