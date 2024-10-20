package com.zzzj.contest.week419;

import cn.hutool.core.util.StrUtil;
import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2024-10-18 15:18
 */
public class Leet3321 {

    public static void main(String[] args) {

        // [1,5,7,3,10]
        System.out.println(
                Arrays.toString(check(
                        new int[]{2, 6, 4, 2, 3, 2, 5, 5, 1, 5, 6},
                        4,
                        3
                ))
        );

        System.out.println(
                Arrays.toString(findXSum(
                        new int[]{2, 6, 4, 2, 3, 2, 5, 5, 1, 5, 6},
                        4,
                        3
                ))
        );

    }

    public static long[] check(int[] nums, int k, int x) {

        if (print) {
            System.out.println(LeetUtils.toStringWithIndex(nums));
            System.out.println("k = " + k);
            System.out.println("x = " + x);
        }

        int N = nums.length;

        Map<Integer, Integer> valueCnt = new HashMap<>();

        long[] ans = new long[N - k + 1];

        for (int i = 0; i < N; i++) {
            int num;

            if (i >= k) {
                // out queue

                num = nums[i - k];

                Integer old = valueCnt.get(num);
                if (old == 1)
                    valueCnt.remove(num);
                else
                    valueCnt.put(num, old - 1);
            }

            // in queue
            num = nums[i];

            valueCnt.put(
                    num,
                    valueCnt.getOrDefault(num, 0) + 1
            );

            if (i >= k - 1) {

                long sum = valueCnt.entrySet()
                        .stream()
                        .sorted((o1, o2) -> {
                            int diff = o2.getValue() - o1.getValue();
                            if (diff == 0)
                                return o2.getKey() - o1.getKey();
                            return diff;
                        })
                        .limit(x)
                        .mapToLong(entry -> (long) entry.getKey() * entry.getValue())
                        .sum();

                // print window
                if (print) {
                    String win = valueCnt.entrySet()
                            .stream()
                            .sorted((o1, o2) -> {
                                int diff = o2.getValue() - o1.getValue();
                                if (diff == 0)
                                    return o2.getKey() - o1.getKey();
                                return diff;
                            })
                            .limit(x)
                            .map(entry -> String.format(" %d x %d ", entry.getKey(), entry.getValue()))
                            .collect(Collectors.joining(", "));

                    System.out.println("help = " + StrUtil.wrap(win, "[", "]"));
                }

                ans[i - k + 1] = sum;

            }


        }

        if (print)
            System.out.println("=====================================");

        return ans;
    }

    static boolean print = true;

    public static long[] findXSum(int[] nums, int k, int x) {

        // help(nums, k, x);

        int N = nums.length;

        Map<Integer, Integer> valueCnt = new HashMap<>();

        // 对顶堆
        // 思路:
        final int INDEX_CNT = 0;

        final int INDEX_NUM = 1;

        // inQueue: 最小堆
        // 和当前 { inQueue } { cnt } 最小的元素比较
        // 如果cnt相同, 那么出队 { num } 更小的元素
        Heap<int[], Integer> inQueue = new Heap<>((o1, o2) -> {
            int diff = o1[INDEX_CNT] - o2[INDEX_CNT];
            if (diff == 0)
                return o1[INDEX_NUM] - o2[INDEX_NUM];
            return diff;
        }, N, node -> node[INDEX_NUM]);

        // outQueue: 最大堆
        // 和当前 { inQueue } { cnt } 最大的元素比较
        // 如果cnt相同, 那么出队 { num } 更大的元素
        Heap<int[], Integer> deQueue = new Heap<>((o1, o2) -> {
            int diff = o2[INDEX_CNT] - o1[INDEX_CNT];
            if (diff == 0)
                return o2[INDEX_NUM] - o1[INDEX_NUM];
            return diff;
        }, N, node -> node[INDEX_NUM]);

        long sum = 0;

        long[] ans = new long[N - k + 1];

        for (int i = 0; i < N; i++) {

            int num;

            int cnt;

            // 出去的元素
            if (i >= k) {

                num = nums[i - k];

                cnt = valueCnt.get(num);

                if (cnt == 1)
                    valueCnt.remove(num);
                else
                    valueCnt.put(num, cnt - 1);

                if (inQueue.inHeap(num)) {
                    if (cnt - 1 == 0)
                        inQueue.remove0(num);
                    else
                        inQueue.update(new int[]{cnt - 1, num});
                    sum -= num;
                }

                if (deQueue.inHeap(num)) {
                    if (cnt - 1 == 0)
                        deQueue.remove0(num);
                    else
                        deQueue.update(new int[]{cnt - 1, num});
                }

            }

            // 进来的元素
            num = nums[i];

            cnt = valueCnt.getOrDefault(num, 0) + 1;

            valueCnt.put(num, cnt);

            if (inQueue.inHeap(num)) {
                inQueue.update(new int[]{cnt, num});
                sum += num;
            } else if (inQueue.size < x) {
                inQueue.push(new int[]{cnt, num});
                sum += (long) num * cnt;
            } else if (inQueue.peek()[INDEX_CNT] < cnt || (inQueue.peek()[INDEX_CNT] == cnt && inQueue.peek()[INDEX_NUM] < num)) {

                int[] inPop = inQueue.pop();

                sum -= (long) inPop[INDEX_CNT] * inPop[INDEX_NUM];
                sum += (long) num * cnt;

                deQueue.push(new int[]{inPop[INDEX_CNT], inPop[INDEX_NUM]});

                inQueue.push(new int[]{cnt, num});

            } else {
                deQueue.push(new int[]{cnt, num});
            }

            while (inQueue.size < x && deQueue.size > 0) {
                int[] dePop = deQueue.pop();
                sum += (long) dePop[INDEX_CNT] * dePop[INDEX_NUM];
                inQueue.push(new int[]{dePop[INDEX_CNT], dePop[INDEX_NUM]});
            }

            if (deQueue.size > 0 && inQueue.size > 0 && (deQueue.peek()[INDEX_CNT] > inQueue.peek()[INDEX_CNT] || (deQueue.peek()[INDEX_CNT] == inQueue.peek()[INDEX_CNT] && deQueue.peek()[INDEX_NUM] > inQueue.peek()[INDEX_NUM]))) {
                int[] dePop = deQueue.pop();

                int[] inPop = inQueue.pop();

                sum -= (long) inPop[INDEX_CNT] * inPop[INDEX_NUM];
                sum += (long) dePop[INDEX_CNT] * dePop[INDEX_NUM];

                deQueue.push(new int[]{inPop[INDEX_CNT], inPop[INDEX_NUM]});
                inQueue.push(new int[]{dePop[INDEX_CNT], dePop[INDEX_NUM]});
            }

            if (i >= k - 1) {

                if (print)
                    System.out.println("inQueue = " + inQueue);

                int ansIndex = i - k + 1;

                ans[ansIndex] = sum;
            }

        }

        return ans;
    }

    public static class Heap<T, K> {

        private final Comparator<T> comparator;

        private final int N;

        private final Map<K, Integer> indexes;

        private final T[] nodes;

        private final Function<T, K> keyGetter;

        private int size;

        public Heap(Comparator<T> comparator, int N, Function<T, K> keyGetter) {
            this.comparator = comparator;
            this.N = N + 1;
            this.indexes = new HashMap<>();
            this.keyGetter = keyGetter;
            this.size = 0;
            this.nodes = (T[]) new Object[this.N];
        }

        private int parent(int index) {
            return index >> 1;
        }

        private int leftChild(int index) {
            return index << 1;
        }

        private int rightChild(int index) {
            return (index << 1) + 1;
        }

        public void push(T t) {

            // 放到最后, 然后shiftUp
            this.nodes[++this.size] = t;

            K key = keyGetter.apply(t);

            indexes.put(key, this.size);

            shiftUp(size);
        }

        public boolean inHeap(K key) {
            return indexes.containsKey(key);
        }

        public void update(T node) {

            K key = keyGetter.apply(node);

            Integer index = indexes.get(key);

            if (index == null)
                return;

            T old = nodes[index];

            int compared = comparator.compare(node, old);

            if (compared == 0) {
                return;
            }

            // update
            nodes[index] = node;

            // 最小堆
            // 变小了 -> shiftDown
            // 变大了 -> shiftUp
            if (compared < 0)
                shiftUp(index);
            else
                shiftDown(index);

        }

        public void remove(T node) {

            K key = keyGetter.apply(node);

            remove0(key);
        }

        public void remove0(K key) {

            Integer index = indexes.get(key);

            if (index == null)
                return;

            T oldNode = nodes[index];

            swap(index, size);

            size--;

            indexes.remove(key);

            shiftDown(index);
        }

        public T peek() {
            if (size == 0)
                return null;
            return nodes[1];
        }

        public T pop() {
            if (size == 0)
                return null;

            T pop = nodes[1];

            swap(1, size);
            nodes[size] = null;
            size--;
            shiftDown(1);
            indexes.remove(keyGetter.apply(pop));

            return pop;
        }

        private void shiftUp(int index) {

            while (index > 1 && comparator.compare(nodes[index], nodes[parent(index)]) < 0) {
                swap(index, parent(index));
                index = parent(index);
            }

        }

        private void shiftDown(int index) {

            while (index <= size) {

                int lc = leftChild(index);

                int rc = rightChild(index);

                if (lc > size)
                    return;

                // 比左边元素小 && 左边 >= 右边
                if (comparator.compare(nodes[lc], nodes[index]) < 0 && (rc > size || comparator.compare(nodes[lc], nodes[rc]) <= 0)) {
                    swap(index, lc);
                    index = lc;
                    // 比右边元素小
                } else if (rc <= size && comparator.compare(nodes[rc], nodes[index]) < 0) {
                    swap(index, rc);
                    index = rc;
                } else {
                    break;
                }

            }

        }

        private void swap(int a, int b) {
            // 先交换 nodes
            T temp = nodes[a];
            nodes[a] = nodes[b];
            nodes[b] = temp;

            // 更新 indexes
            indexes.put(keyGetter.apply(nodes[a]), a);
            indexes.put(keyGetter.apply(nodes[b]), b);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 1; i <= size; i++) {
                int[] node = (int[]) nodes[i];
                sb.append(String.format(" %d x %d ", node[1], node[0]));
                if (i < size) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

    }
}
