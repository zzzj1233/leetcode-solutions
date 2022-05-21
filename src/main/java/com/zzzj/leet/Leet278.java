package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-05-09 12:06
 */
public class Leet278 {

    public static void main(String[] args) {
        System.out.println(new Leet278().firstBadVersion(2126753390));
    }

    static boolean isBadVersion(int version) {
        return false;
    }

    public int firstBadVersion(int n) {
        // 1 ~ n
        int l = 1;

        int r = n;

        int mid = l + (r - l) / 2;

        int ans = -1;

        while (l <= r) {
            if (isBadVersion(mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            mid = l + (r - l) / 2;
        }

        return ans;
    }


}
