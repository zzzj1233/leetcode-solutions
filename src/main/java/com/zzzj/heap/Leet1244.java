package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-06-23 10:34
 */
public class Leet1244 {

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
//        Right right = new Right();

        final LinkedList<Object> result2 = LeetUtils.executeExpression("[\"Leaderboard\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"addScore\",\"top\",\"reset\",\"reset\",\"top\",\"reset\",\"reset\",\"reset\",\"reset\",\"reset\",\"top\",\"addScore\",\"reset\",\"reset\",\"reset\",\"addScore\",\"reset\",\"addScore\",\"top\",\"addScore\",\"reset\",\"reset\",\"top\",\"reset\",\"addScore\",\"reset\",\"top\",\"top\",\"addScore\",\"addScore\",\"addScore\",\"reset\",\"reset\",\"addScore\",\"reset\",\"addScore\",\"reset\",\"reset\",\"reset\",\"addScore\",\"addScore\",\"top\",\"top\",\"addScore\",\"top\",\"reset\",\"reset\",\"addScore\",\"top\",\"reset\",\"reset\",\"addScore\",\"addScore\",\"top\",\"reset\",\"reset\",\"reset\",\"reset\",\"addScore\",\"addScore\",\"addScore\",\"reset\",\"reset\",\"addScore\",\"top\",\"addScore\",\"addScore\",\"reset\",\"top\",\"reset\",\"top\",\"top\",\"reset\",\"addScore\",\"reset\",\"addScore\",\"addScore\",\"reset\",\"reset\",\"reset\",\"top\",\"top\",\"top\",\"addScore\",\"addScore\",\"reset\",\"addScore\",\"top\",\"top\",\"reset\",\"reset\",\"reset\",\"addScore\",\"addScore\",\"top\",\"top\",\"addScore\",\"reset\"]", "[[],[1,55],[2,19],[3,83],[4,13],[5,86],[6,46],[7,39],[8,31],[9,87],[10,48],[11,99],[12,18],[13,63],[14,63],[15,35],[16,18],[17,65],[18,23],[19,73],[20,84],[21,49],[22,76],[23,23],[24,40],[25,56],[26,30],[27,77],[28,55],[29,14],[30,39],[31,62],[32,52],[33,84],[34,48],[35,36],[36,16],[37,35],[38,35],[39,75],[40,35],[41,59],[42,99],[43,63],[44,66],[45,10],[46,86],[47,18],[48,97],[49,13],[50,33],[51,21],[52,54],[53,74],[54,98],[55,37],[56,17],[57,12],[58,97],[59,15],[60,51],[61,75],[62,96],[63,15],[64,15],[65,57],[66,51],[67,72],[68,58],[69,9],[70,71],[71,64],[72,22],[73,25],[74,81],[75,23],[76,27],[77,22],[78,81],[79,50],[80,55],[81,35],[82,92],[83,77],[84,93],[85,88],[86,77],[87,44],[88,78],[89,64],[90,77],[91,50],[92,94],[93,52],[94,49],[95,53],[96,32],[97,65],[98,43],[99,60],[100,47],[27,6],[32,49],[16,20],[9],[1],[2],[31],[3],[4],[5],[6],[7],[37],[71,52],[8],[9],[10],[83,40],[11],[99,26],[47],[33,94],[12],[13],[55],[14],[9,38],[9],[66],[3],[40,12],[27,21],[97,65],[15],[16],[73,40],[17],[71,78],[18],[19],[20],[11,62],[26,35],[69],[76],[7,63],[32],[7],[11],[75,48],[29],[21],[22],[39,71],[8,77],[23],[8],[23],[24],[25],[75,82],[15,86],[87,54],[15],[26],[61,55],[48],[47,86],[54,86],[27],[30],[28],[58],[5],[29],[83,30],[30],[28,32],[6,64],[6],[28],[31],[25],[46],[12],[3,94],[55,29],[3],[8,87],[10],[6],[8],[32],[33],[17,89],[26,65],[36],[16],[29,90],[17]]", leaderboard);
    }


    public static void heapSort(int[] arr) {
        MaxHeap maxHeap = MaxHeap.heapify(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.popMax();
        }
    }

    private static class Leaderboard {

        MaxHeap heap = new MaxHeap();

        HashMap<Integer, Integer> outerMap = new HashMap<>();

        public Leaderboard() {

        }

        public void addScore(int playerId, int score) {
            heap.insert(playerId, score);
            outerMap.put(playerId, outerMap.getOrDefault(playerId, 0) + score);
        }

        public int top(int K) {
            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

            outerMap.entrySet().forEach(entry -> queue.add(new int[]{entry.getKey(), entry.getValue()}));

            List<int[]> list = new ArrayList<>(K);

            int ans = 0;

            int ans2 = 0;

            for (int i = 0; i < K; i++) {
                int[] pop = heap.popMax();
                int[] remove = queue.remove();
//                if (pop[1] != remove[1]) {
//                    System.out.println("Error");
//                    System.exit(0);
//                }
                ans2 += remove[1];
                ans += pop[1];
                list.add(pop);
            }

            list.forEach(item -> {
                heap.insert(item[0], item[1]);
            });

            if (ans != ans2) {
                System.out.println("Error");
            }

            return ans;
        }

        public void reset(int playerId) {
            heap.remove(playerId);
            outerMap.remove(playerId);
        }

        private class Element {
            int playerId;
            int score;
        }

        private class MaxHeap {
            private final Element[] data = new Element[10002];

            private final Map<Integer, Integer> map = new HashMap<>(64);

            private int count;

            public MaxHeap heapify(int[] data) {
                MaxHeap maxHeap = new MaxHeap();

                maxHeap.count = data.length;
                for (int i = 0; i < data.length; i++) {
                    // 索引从1开始
                    final Element ele = new Element();
                    ele.score = data[i];
                    maxHeap.data[i + 1] = ele;
                }

                // heapify ~
                // maxHeap的第一个非叶子节点的索引一定是count / 2
                int rootIdx = maxHeap.count / 2;

                for (int i = rootIdx; i >= 1; i--) {
                    maxHeap.shiftDown(i);
                }

                return maxHeap;
            }

            public void insert(int player, int score) {
                Integer index = map.get(player);

                if (index != null) {
                    try {
                        data[index].score += score;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    shiftUp(index);
                } else {
                    Element element = new Element();
                    element.playerId = player;
                    element.score = score;
                    this.data[count + 1] = element;
                    shiftUp(count + 1);
                    map.put(player, count + 1);
                    this.count++;
                }
            }

            public int[] remove(int index) {
                Element max = this.data[index];

                this.data[index] = this.data[count];

                this.data[count].score = 0;

                this.count--;

                shiftDown(index);

                map.remove(max.playerId);

                return new int[]{max.playerId, max.score};
            }

            public int[] popMax() {
                return remove(1);
            }

            private void shiftDown(int idx) {
                int leftIdx = getLeftChild(idx);
                int rightIdx = getRightChild(idx);

                if (leftIdx > this.count) {
                    return;
                }

                int left = this.data[leftIdx].score;
                int right = rightIdx > this.count ? 0 : this.data[rightIdx].score;

                if (left > this.data[idx].score && left > right) {
                    swap(this.data, leftIdx, idx);
                    shiftDown(leftIdx);
                } else if (right > this.data[idx].score) {
                    swap(this.data, rightIdx, idx);
                    shiftDown(rightIdx);
                }
            }

            private void shiftUp(int idx) {
                int parent = getParent(idx);

                if (parent == 0) {
                    return;
                }

                if (this.data[idx].score > this.data[parent].score) {
                    swap(this.data, idx, parent);
                    shiftUp(parent);
                }

            }

            private int getParent(int idx) {
                return idx / 2;
            }

            private int getLeftChild(int idx) {
                return idx << 1;
            }

            private int getRightChild(int idx) {
                return (idx << 1) + 1;
            }

            public void swap(Element[] arr, int i, int j) {
                map.put(arr[i].playerId, j);
                map.put(arr[j].playerId, i);

                Element temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }

    }

}
