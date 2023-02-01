package com.zzzj.stack;

import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-01-17 11:09
 */
public class Leet2454 {

    public static void main(String[] args) {

//         1, -1, 4, -1, -1
//         1, 4, 4, -1, -1
//        System.out.println(Arrays.toString(secondGreaterElement(new int[]{0, 1, 1, 5, 4})));

//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int[] arr = ArrayUtil.generateArray(5, 0, 10);

            CopyableIterator<int[]> iterator = new CopyableIterator<>(arr, ArrayUtil::copy);

            if (!Arrays.equals(secondGreaterElement(iterator.next()), right(iterator.next()))) {
                System.out.println("Error");
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println(Arrays.toString(secondGreaterElement(iterator.next())));
                System.out.println(Arrays.toString(right(iterator.next())));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int[] secondGreaterElement(int[] nums) {
        int N = nums.length;

        // 单调栈, 维护一个最大栈
        LinkedList<Integer> stack = new LinkedList<>();

        PriorityQueue[] queues = new PriorityQueue[N];

        int[] ans = new int[N];

        Arrays.fill(ans, -1);

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            while (!stack.isEmpty()) {
                // 栈的最后一个元素比当前元素小
                if (nums[stack.peekLast()] < num) {
                    Integer last = stack.removeLast();
                    PriorityQueue<Integer> queue = queues[last];
                    if (queue != null) {
                        for (Integer index : queue) {
                            ans[index] = num;
                        }
                    }
                    if (queues[i] == null) {
                        queues[i] = new PriorityQueue<Integer>(Comparator.comparingInt(o -> nums[o]));
                    }
                    queues[i].add(last);
                } else {
                    //
                    Integer last = stack.peekLast();
                    PriorityQueue<Integer> queue = queues[last];
                    while (queue != null && !queue.isEmpty() && nums[queue.peek()] < num) {
                        ans[queue.remove()] = num;
                    }
                    break;
                }
            }
            stack.add(i);
        }


        return ans;
    }

    public static int[] right(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int[] stk = new int[100010];
        int top1 = -1;
        int[] stk2 = new int[100010];
        int top2 = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (top2 != -1 && nums[stk2[top2]] < num) {
                ans[stk2[top2--]] = num;
            }
            int oldTop1 = top1;
            while (top1 != -1 && nums[stk[top1]] < num) {
                top1--;
            }
            System.arraycopy(stk, top1 + 1, stk2, top2 + 1, oldTop1 - top1);
            top2 += oldTop1 - top1;
            stk[++top1] = i;
        }
        return ans;
    }

}
