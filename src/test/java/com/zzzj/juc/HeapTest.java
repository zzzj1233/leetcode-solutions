package com.zzzj.juc;

import cn.hutool.core.thread.ThreadUtil;
import com.zzzj.leet.DifferenceRandomNumber;
import com.zzzj.leet.LeetUtils;
import com.zzzj.util.Heap;
import com.zzzj.util.InvokableExp;
import com.zzzj.util.InvokeMethodSource;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

/**
 * @author zzzj
 * @create 2024-10-19 15:49
 */
public class HeapTest {

    private static final int MAXIMUM = 1000;

    @Test
    public void test2() throws Exception {
        // 创建一个最大堆，假设 int[1] 是排序关键字，int[0] 是其他数据
        Comparator<int[]> comparator = Comparator.comparingInt(o -> o[1]);
        Function<int[], Integer> keyGetter = intArray -> intArray[0];

        Heap<int[], Integer> heap = new Heap<>(comparator, 100, keyGetter);

        // 执行操作
        heap.push(new int[]{84, 404});
        heap.pop();
        heap.push(new int[]{993, 877});
        heap.peek(); // 目前堆顶元素
        heap.peek(); // 目前堆顶元素
        heap.push(new int[]{403, 742});
        heap.peek(); // 目前堆顶元素
        heap.push(new int[]{232, 590});
        heap.peek(); // 目前堆顶元素
        heap.push(new int[]{253, 139});
        heap.peek(); // 目前堆顶元素
        heap.pop(); // 弹出堆顶元素
        heap.peek(); // 目前堆顶元素
        heap.peek(); // 目前堆顶元素
        heap.pop(); // 弹出堆顶元素
        heap.push(new int[]{578, 299});
        heap.push(new int[]{291, 1});
        System.out.println(Arrays.toString(heap.pop()));
        System.out.println(Arrays.toString(heap.pop()));
    }

    @Test
    public void test() throws Exception {

        Comparator<int[]> comparator = Comparator.comparingInt(o -> o[1]);

        Function<int[], Integer> keyGetter = intArray -> intArray[0];

        int N = 1000;

        int M = 10000;

        int rc = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = ThreadUtil.newExecutor(rc);

        CountDownLatch latch = new CountDownLatch(rc);

        for (int c = 0; c < rc; c++) {
            executorService.execute(() -> {

                for (int i = 0; i < 1000; i++) {

                    DifferenceRandomNumber differ = new DifferenceRandomNumber(0, M);

                    InvokableExp hp = new InvokableExp(Heap.class, comparator, MAXIMUM, keyGetter);

                    InvokableExp cr = new InvokableExp(Corrector.class, comparator);

                    boolean r = LeetUtils.executeExpression(
                            hp,
                            cr,
                            N,
                            new InvokeMethodSource("push", () -> new Object[]{new int[]{differ.nextNum(), differ.nextNum()}}),
                            new InvokeMethodSource("update", () -> new Object[]{new int[]{differ.nextNum(), differ.nextNum()}}),
                            new InvokeMethodSource("remove", () -> new Object[]{new int[]{differ.nextNum(), differ.nextNum()}}),
                            new InvokeMethodSource("peek", () -> null),
                            new InvokeMethodSource("pop", () -> null));

                    if (!r) {
                        System.out.println("hp = " + hp);
                        System.out.println("cr = " + cr);
                        latch.countDown();
                        return;
                    }

                }
                latch.countDown();
            });
        }

        latch.await();

        executorService.shutdown();

        System.out.println("Ok");
    }

    static class Corrector<T> {

        private final Comparator<T> comparator;

        private final List<T> list;

        private boolean updated;

        private Corrector(Comparator<T> comparator) {
            this.comparator = comparator;
            this.list = new ArrayList<>(MAXIMUM);
        }

        public void push(T t) {
            this.list.add(t);
            updated = true;
        }

        public void update(T node) {
            boolean find = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(node)) {
                    list.set(i, node);
                    find = true;
                    break;
                }
            }
            if (!find)
                return;
            updated = true;
        }

        public void remove(T node) {
            int removeIndex = -1;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(node)) {
                    list.set(i, node);
                    removeIndex = i;
                    break;
                }
            }

            if (removeIndex == -1)
                return;

            list.remove(removeIndex);

            updated = true;
        }

        public T peek() {
            if (list.isEmpty())
                return null;

            if (updated)
                Collections.sort(list, comparator);
            return list.get(0);
        }

        public T pop() {
            if (list.isEmpty())
                return null;

            if (updated)
                Collections.sort(list, comparator);
            T r = list.remove(0);
            updated = true;
            return r;
        }

    }

}
