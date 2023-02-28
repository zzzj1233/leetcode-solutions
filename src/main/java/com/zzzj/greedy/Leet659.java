package com.zzzj.greedy;

import com.sun.media.sound.SoftTuning;
import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * 太难了,写不出来
 * <p>
 * 这应该是道Hard题
 *
 * @author zzzj
 * @create 2022-11-01 11:59
 */
public class Leet659 {

    public static void main(String[] args) {


//        System.out.println(isPossible(new int[]{2, 3, 4, 5, 6, 7, 7, 8, 8, 9}));
//        System.out.println(isPossible(new int[]{1, 2, 3, 3, 4, 5}));
//
//        System.out.println(isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
//
        System.out.println(isPossible(new int[]{2, 3, 4, 5, 7}));
//
//        System.exit(0);

        for (; ; ) {
//            int N = LeetUtils.random.nextInt(100);

            int N = LeetUtils.random.nextInt(11) + 1;

            int[] arr = ArrayUtil.generateArray(N, 2, 10);

            ArrayCopyIterator iterator = new ArrayCopyIterator(arr, true);

            if (isPossible(iterator.next()) != right(iterator.next())) {
//                System.out.println("Error --- " + i);
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println("MyAns : " + isPossible(iterator.next()));
                System.out.println("Right : " + right(iterator.next()));
                return;
            }

        }

//        System.out.println("Ok");
    }


    public static boolean isPossible(int[] nums) {
        //用一个哈希表统计每个元素出现的次数
        Map<Integer, Integer> countNum = new HashMap<Integer, Integer>();
        for (int num : nums) {
            countNum.put(num, countNum.getOrDefault(num, 0) + 1);
        }
        //定义一个哈希表记录最长的子序列
        Map<Integer, Integer> tail = new HashMap<Integer, Integer>();
        for (int num : nums) {
            int count = countNum.getOrDefault(num, 0);
            if (count <= 0) {//当前元素已经用完，直接跳过
                continue;
            } else if (tail.getOrDefault(num - 1, 0) > 0) {//前面还有数字，可以构成以num结尾的子序列
                countNum.put(num, count - 1);
                tail.put(num - 1, tail.get(num - 1) - 1);//覆盖当前最长的子序列
                tail.put(num, tail.getOrDefault(num, 0) + 1);//当前以num结尾的子序列+1
            } else if (countNum.getOrDefault(num + 1, 0) > 0 && countNum.getOrDefault(num + 2, 0) > 0) {//前面无数字构成子序列后，判断能不能跟后面的构成子序列
                countNum.put(num, count - 1);
                countNum.put(num + 1, countNum.get(num + 1) - 1);
                countNum.put(num + 2, countNum.get(num + 2) - 1);
                tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);//当前以num+2结尾的子序列+1
            } else
                return false;//前后不能构成子序列直接返回false
        }
        return true;
    }

    public static boolean right(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<Integer>());
            }
            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }

}
