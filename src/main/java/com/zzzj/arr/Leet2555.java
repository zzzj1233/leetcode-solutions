package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.Unresolved;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-04-20 15:28
 * @Unresolved: 有点难
 */
@Unresolved
public class Leet2555 {


    public static void main(String[] args) {

//        // 0 1 2 3 4 5
//        System.out.println(maximizeWin(
//                new int[]{1, 1, 2, 2, 3, 3, 5},
//                2
//        ));
//
//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int k = 10;

            int[] arr = ArrayUtil.generateArray(k, 0, 5);

            Arrays.sort(arr);

            int times = LeetUtils.random.nextInt(k);

            if (maximizeWin(arr, times) != right(arr, times)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(times);
                System.out.println("==========================================");
                System.out.println(maximizeWin(arr, times));
                System.out.println("right ans: ");
                System.out.println(right(arr, times));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int maximizeWin(int[] prizePositions, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int position : prizePositions) {
            map.put(position, map.getOrDefault(position, 0) + 1);
        }

        Helper helper = new Helper(map);

        int L1 = 0;
        int R1 = 0;

        int L2 = R1 + k + 1;
        int R2 = L2;

        int sum = 0;

        int ans = 0;

        while (R1 < helper.size()) {

            while (helper.position(R1) - helper.position(L1) > k) {
                sum -= helper.count(L1);
                L1++;
            }

            while (helper.position(R2) - helper.position(L2) > k) {
                sum -= helper.count(L2);
                L2++;
            }

            sum += helper.count(R1) + helper.count(R2);

            ans = Math.max(ans, sum);

            R1++;
            R2++;
        }

        return ans;
    }

    static class Helper {

        // 1,1,2,2,5,5,7
        // =============>
        // 1:2
        // 2:2
        // 5:2
        // 7:1
        private final List<Map.Entry<Integer, Integer>> list;

        private final int size;

        public Helper(TreeMap<Integer, Integer> map) {
            this.list = new ArrayList<>(map.entrySet());
            this.size = list.size();
        }

        public int position(int index) {
            if (index >= size) {
                return list.get(size - 1).getKey();
            }
            return list.get(index).getKey();
        }

        public int count(int index) {
            if (index >= size) {
                return 0;
            }
            return list.get(index).getValue();
        }

        public int size() {
            return size;
        }

    }


    public static int right(int[] prizePositions, int k) {
        int left = 0, right = 0, res = 0;
        int[] sum = new int[prizePositions.length + 1];
        for (; right < prizePositions.length; ++right) {
            //如果此时线段长度已经超过了k，就将将left向前移动，直到线段长度<=k
            while (prizePositions[right] - prizePositions[left] > k) left++;
            //更新一下答案
            res = Math.max(res, right - left + 1 + sum[left]);
            //更新一下sum数组
            sum[right + 1] = Math.max(sum[right], right - left + 1);
        }
        return res;
    }

}
