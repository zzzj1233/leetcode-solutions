package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2023-04-24 14:44
 */
public class Leet2611 {

    public static void main(String[] args) {
        int N = 10000;

        for (int i = 0; i < N; i++) {

            int K = 4;

            int[] r1 = ArrayUtil.generateArray(K, 0, 5);
            int[] r2 = ArrayUtil.generateArray(K, 0, 5);
            int k = LeetUtils.random.nextInt(K);

            if (miceAndCheese(r1, r2, k) != right(r1, r2, k)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(r1));
                System.out.println(Arrays.toString(r2));
                System.out.println(k);
                System.out.println(miceAndCheese(r1, r2, k));
                System.out.println(right(r1, r2, k));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int N = reward1.length;

        // 老鼠1: 必须刚好吃K块蛋糕

        int[][] diff = new int[N][2];

        for (int i = 0; i < N; i++) {
            diff[i][0] = i;
            diff[i][1] = reward2[i] - reward1[i];
        }

        Arrays.sort(diff, Comparator.comparingInt(o -> o[1]));

        int ans = 0;

        for (int i = 0; i < k; i++) {
            ans += reward1[diff[i][0]];
        }

        for (int i = k; i < N; i++) {
            ans += reward2[diff[i][0]];
        }

        return ans;
    }

    public static int right(int[] reward1, int[] reward2, int k) {
        // 贪心
        int n = reward1.length;
        int res = 0;
        int[] diff = new int[n];
        // 首先将所有奶酪都给第二只，总分数为res。
        // 接下来从这i个奶酪中再挑出k个给第一只，让res最大。那么最优选择就是选择这n块奶酪中，第一只的分数尽量大于第二只分数的k个(也就是差值diff = reward1[i]-reward2[i]最大的k个。排序后如果最大的diff>0,说明这块奶酪给第一只吃分数更高，理应给第一只；如若diff<0，则也说明这个diff是将奶酪给第一只后令总分数res减小最少的选择，也应给第一只。)
        for (int i = 0; i < n; ++i) {
            res += reward2[i];
            diff[i] = reward1[i] - reward2[i];
        }
        // 每块奶酪两只老鼠分数差值diff排序
        Arrays.sort(diff);
        // 选择第一只分数减第二只分数差最大的k个，将差值加到res(此时res中是第二只全吃的分数)中，加入后相当于将加入差值diff的k块重新分给了第一只。
        for (int i = 0; i < k; ++i) {
            res += diff[n - 1 - i];
        }
        return res;
    }

}
