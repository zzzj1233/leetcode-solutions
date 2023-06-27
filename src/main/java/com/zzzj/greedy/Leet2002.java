package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-06-26 17:47
 */
public class Leet2002 {

    public static void main(String[] args) {
        System.out.println(maxProduct("accbcaxxcxx"));
        System.out.println(maxProduct("leetcodecom"));
        System.out.println(maxProduct("bb"));
    }

    public static int maxProduct(String s) {
        return dfs(s, 0, 0, 0);
    }

    public static int dfs(String s, int index, int stat, int visited) {
        if (index >= s.length()) {
            if (check(s, stat)) {
                if (visited == 0)
                    return dfs(s, 0, 0, stat);
                else
                    return Integer.bitCount(stat) * Integer.bitCount(visited);
            }
            return 0;
        }

        if ((visited & (1 << index)) != 0) return dfs(s, index + 1, stat, visited);

        return Math.max(dfs(s, index + 1, stat | 1 << index, visited), dfs(s, index + 1, stat, visited));
    }

    public static boolean check(String source, int stat) {

        if (stat == 0) return false;

        int left = 0;

        while (left <= 12 && (stat & (1 << left)) == 0) left++;

        int right = 12;

        while (right >= 0 && (stat & (1 << right)) == 0) right--;

        while (left < right) {
            if (source.charAt(left) != source.charAt(right)) return false;

            left++;
            while (left < right && (stat & (1 << left)) == 0) left++;
            right--;
            while (right > left && (stat & (1 << right)) == 0) right--;
        }

        return true;
    }

}
