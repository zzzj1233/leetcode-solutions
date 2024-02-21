package com.zzzj.str;

/**
 * @author zzzj
 * @create 2024-02-20 15:07
 */
public class Leet3036 {

    public static void main(String[] args) {

        System.out.println(countMatchingSubarrays(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 1}));


    }

    public static int countMatchingSubarrays(int[] nums, int[] pattern) {

        int N = nums.length;

        int M = pattern.length;

        StringBuilder builder = new StringBuilder(M);

        for (int p : pattern) {
            builder.append(p + 1);
        }

        String ps = builder.toString();

        builder = new StringBuilder(N);

        for (int i = 0; i < N - 1; i++)
            if (nums[i + 1] == nums[i])
                builder.append(1);
            else if (nums[i + 1] > nums[i])
                builder.append(2);
            else
                builder.append(0);

        String ns = builder.toString();

        int[] z = zFunc(ns, ps);

        int ans = 0;

        for (int i = M; i < z.length; i++)
            if (z[i] >= M)
                ans++;

        return ans;
    }

    public static int[] zFunc(String ns, String ps) {

        String s = ps + ns;

        int N = s.length();

        int[] z = new int[N];

        int l = 0, r = 0;

        for (int k = 1; k < N; k++) {

            if (k <= r) {
                int prev = k - l;

                if (z[prev] + k < r) {
                    z[k] = z[prev];
                } else {
                    l = k;
                    while (r < N && s.charAt(r) == s.charAt(r - l)) {
                        r++;
                    }
                    z[k] = r - l;
                    r--;
                }
            } else {
                l = k;
                r = k;
                while (r < N && s.charAt(r) == s.charAt(r - l)) {
                    r++;
                }
                z[k] = r - l;
                r--;
            }

        }

        return z;
    }

}
