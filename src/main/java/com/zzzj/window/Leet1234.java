package com.zzzj.window;

/**
 * @author zzzj
 * @create 2023-12-26 14:11
 */
public class Leet1234 {

    public static void main(String[] args) {

        // 3

        // 0 1 2 3 4 5 6 7 8 9 10  11  12  13  14 15 16 17 18 19
        // W W E Q E R Q W Q W W   R   W   W   E  R  Q  W  E  Q
        System.out.println(balancedString("WWEQERQWQWWRWWERQWEQ"));

    }

    public static int balancedString(String s) {

        int[] indexes = new int[97];

        indexes['Q'] = 0;
        indexes['W'] = 1;
        indexes['E'] = 2;
        indexes['R'] = 3;

        int[] window = new int[4];

        int N = s.length();

        int max = N / 4;

        int left = 0;

        int right = N - 1;

        while (left < N) {
            int index = indexes[s.charAt(left)];
            if (window[index] + 1 > max) break;
            window[index]++;
            left++;
        }

        if (left > 0)
            left--;

        int ans = N - left - 1;

        while (right > left) {

            int index = indexes[s.charAt(right)];

            window[index]++;

            while (window[index] > max) {
                if (left < 0)
                    return ans;
                window[indexes[s.charAt(left)]]--;
                left--;
            }

            ans = Math.min(ans, right - left - 1);

            right--;
        }

        return ans;
    }

}
