package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-07-04 16:46
 */
public class Leet354 {

    public static void main(String[] args) {
    }

    // [[15,8],[2,20],[2,14],[4,17],[8,19],[8,9],[5,7],[11,19],[8,11],[13,11],[2,13],[11,19],[8,11],[13,11],[2,13],[11,19],[16,1],[18,13],[14,17],[18,19]]
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] envelope1, int[] envelope2) {
                // 排序先按宽度升序，再按高度降序
                return envelope1[0] != envelope2[0] ? envelope1[0] - envelope2[0] :
                        envelope2[1] - envelope1[1];
            }
        });
        int[] heightOfNum = new int[n + 1]; // 非降序的套娃高度序列
        heightOfNum[1] = envelopes[0][1]; //将第一个信封的高度加入高度序列
        int size = 1; // 初始套娃数量
        for (int i = 1; i < n; i++) {
            int height = envelopes[i][1];
            // 比之前信封高度大，可以套，加入高度队列
            if (height > heightOfNum[size]) {
                heightOfNum[++size] = height;
            } else {
                int replace = binarySearch(heightOfNum, size, height);// 找到比height高度大的最小高度位置
                heightOfNum[replace] = height;// 替换成更小的height
            }
        }
        return size;
    }

    // 简单二分搜索
    private int binarySearch(int[] heightOfNum, int size, int target) {
        int low = 0, high = size;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (heightOfNum[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
