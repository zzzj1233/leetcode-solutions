package com.zzzj.contest.week384;

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

        builder.setLength(0);

        for (int i = 0; i < N - 1; i++)
            if (nums[i + 1] == nums[i])
                builder.append(1);
            else if (nums[i + 1] > nums[i])
                builder.append(2);
            else
                builder.append(0);

        String ns = builder.toString();

        int[] next = next(ps);

        int x = 0;
        int y = 0;

        int ans = 0;

        while (x < N - 1) {

            if (ns.charAt(x) == ps.charAt(y)) {
                x++;
                y++;
                if (y == M) {
                    ans++;
                    y = next[y];
                }
            } else if (next[y] >= 0) {
                y = next[y];
            } else {
                x++;
            }

        }

        return ans;
    }

    public static int[] next(String s) {

        int N = s.length();

        int[] next = new int[N + 1];

        next[0] = -1;

        int index = 2;

        int cc = 0;

        while (index <= N) {
            if (s.charAt(index - 1) == s.charAt(cc)) {
                next[index++] = ++cc;
            } else if (next[cc] >= 0) {
                cc = next[cc];
            } else {
                index++;
            }
        }

        return next;
    }

}
