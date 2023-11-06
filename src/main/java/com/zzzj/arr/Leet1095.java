package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-10-27 15:01
 */
public class Leet1095 {

    public static void main(String[] args) {

        System.out.println(findInMountainArray(3, new MountainArrayImpl(new int[]{1, 2, 4, 5, 3, 1})));

    }

    private interface MountainArray {
        int get(int index);

        int length();
    }

    private static class MountainArrayImpl implements MountainArray {

        private final int[] arr;

        public MountainArrayImpl(int[] arr) {
            this.arr = arr;
        }

        @Override
        public int get(int index) {
            return arr[index];
        }

        @Override
        public int length() {
            return arr.length;
        }
    }

    public static int search(int target, MountainArray mountainArr, int left, int right) {
        int ans = -1;

        int N = mountainArr.length();

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            int num = mountainArr.get(mid);

            int prev = mid > 0 ? mountainArr.get(mid - 1) : -1;

            int next = mid < N - 1 ? mountainArr.get(mid + 1) : -1;

            // 峰顶
            if (prev != -1 && next != -1 && prev < num && next < num) {
                if (num == target)
                    return mid;

                int leftRes = -1;

                if (target <= prev)
                    leftRes = search(target, mountainArr, left, mid - 1);

                if (leftRes != -1)
                    return leftRes;

                if (target <= next)
                    return search(target, mountainArr, mid + 1, right);

                return -1;
            } else if (mid == 0 || prev < num) {
                // 在峰顶左边
                if (num == target)
                    return mid;

                int leftRes = -1;

                if (target < num)
                    leftRes = search(target, mountainArr, left, mid - 1);

                if (leftRes != -1)
                    return leftRes;

                return search(target, mountainArr, mid + 1, right);
            } else {
                // 在峰顶右边
                if (num == target) {
                    int leftRes = search(target, mountainArr, left, mid - 1);

                    return leftRes == -1 ? mid : leftRes;
                } else if (target < num) {
                    // 1. 在峰顶的左边
                    // 2. 在当前元素的右边
                    int leftRes = search(target, mountainArr, left, mid - 1);

                    if (leftRes != -1)
                        return leftRes;

                    return search(target, mountainArr, mid + 1, right);
                } else {
                    right = mid - 1;
                }
            }

        }

        return ans;
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        return search(target, mountainArr, 0, mountainArr.length() - 1);
    }

}
