package com.zzzj.interview;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public abstract class TopKWords {

    protected final int k;

    public TopKWords(int k) {
        this.k = k;
    }

    abstract void addWord(String word);

    abstract String[] topK();

    public static void main(String[] args) {

        TopKWords s = new Solution(3);

        s.addWord("aaa");
        s.addWord("bbb");
        s.addWord("ccc");
        s.addWord("ccc");
        s.addWord("ddd");

        System.out.println(Arrays.toString(s.topK()));

        System.exit(0);

        int N = 10000;

        int K = 10;

        for (int i = 0; i < 5; i++) {

            int k = LeetUtils.random.nextInt(K);

            TopKWords right = new Violent(k);

            TopKWords solution = new Solution(k);

            for (int j = 0; j < N; j++) {

                String word = LeetUtils.randomString(LeetUtils.random.nextInt(26) + 1, false);

                right.addWord(word);

                solution.addWord(word);

                if (j % 10 == 0) {

                    String[] kWords1 = right.topK();

                    String[] kWords2 = solution.topK();

                    int len = kWords1.length;

                    if (len != kWords2.length) {
                        System.out.println("Error");
                        return;
                    }

                    for (int x = 0; x < len; x++) {

                        if (kWords1[x] != kWords2[x]) {
                            System.out.println("Error");
                            return;
                        }

                    }

                }
            }

            K *= 5;
        }

        System.out.println("Ok");
    }

    private static class Solution extends TopKWords {

        private Map<String, Node> nodesMap = new HashMap<>();

        private Map<String, Integer> heapMap = new HashMap<>();

        private Comparator<Node> comp = new Comp();

        private TreeSet<Node> treeSet = new TreeSet<>((o1, o2) -> {
            if (o1.cnt == o2.cnt) return o1.word.compareTo(o2.word);
            return o2.cnt - o1.cnt;
        });

        private Heap heap = new Heap(k, comp);

        public Solution(int k) {
            super(k);
        }

        @Override
        void addWord(String word) {

            Node node = nodesMap.get(word);

            // 第一次进来
            if (node == null) {
                node = new Node(word, 1);

                nodesMap.put(word, node);

                int index = heap.push(node);

                heapMap.put(word, index);

            } else {
                node.cnt++;

                Integer index = heapMap.get(word);

                heapMap.put(word, heap.shitUp(index));

            }

        }

        @Override
        String[] topK() {

            Node[] nodes = new Node[k];

            for (int i = 0; i < k && !heap.isEmpty(); i++) {

                Node pop = heap.pop();

                nodes[i] = pop;
            }

            for (Node node : nodes) {
                if (node == null) break;
                heap.push(node);
            }

            return Arrays.stream(nodes)
                    .map(node -> node.word)
                    .toArray(String[]::new);
        }

        public static class Node {
            String word;
            int cnt;

            public Node(String word, int cnt) {
                this.word = word;
                this.cnt = cnt;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Node node = (Node) o;

                return word.equals(node.word);
            }

            @Override
            public int hashCode() {
                return word.hashCode();
            }
        }

        public static class Comp implements Comparator<Node> {

            @Override
            public int compare(Node o1, Node o2) {
                if (o1.cnt == o2.cnt)
                    return o1.word.compareTo(o2.word);
                return o1.cnt - o2.cnt;
            }

        }

        public static class Heap {

            final Node[] data;

            int size;

            final Comparator<Node> comparator;

            int index = 1;

            public Heap(int capacity, Comparator<Node> comparator) {
                this.data = new Node[capacity + 1];
                this.size = 0;
                this.comparator = comparator;
            }

            public Node pop() {

                Node datum = this.data[1];

                swap(1, this.index - 1);

                this.size--;
                this.index--;

                shitDown(1);

                return datum;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public int push(Node node) {

                if (this.index < data.length) {
                    this.data[index] = node;
                    index++;
                    size++;
                    return shitDown(index - 1);
                } else {
                    if (this.comparator.compare(node, this.peek()) < 1) {
                        this.data[1] = node;
                        size++;
                        return shitDown(1);
                    }
                }

                return -1;
            }

            public Node peek() {
                return data[1];
            }

            public int shitDown(int index) {

                Node datum = this.data[index];

                int left = leftChild(index);

                int right = rightChild(index);

                if (left <= this.size && this.data[left] != null && this.comparator.compare(datum, this.data[left]) > 0) {
                    swap(left, index);
                    return shitDown(left);
                } else if (right <= this.size && this.data[right] != null && this.comparator.compare(datum, this.data[right]) > 0) {
                    swap(right, index);
                    return shitDown(right);
                }

                return index;
            }

            public int shitUp(int index) {

                int p = parent(index);

                if (p >= 1 && this.comparator.compare(this.data[index], this.data[p]) < 0) {
                    swap(p, index);
                    return shitUp(p);
                }

                return index;
            }

            public void swap(int x, int y) {
                Node temp = data[x];
                data[x] = data[y];
                data[y] = temp;
            }

            public int parent(int index) {
                return index / 2;
            }

            public int leftChild(int index) {
                return index << 1;
            }

            public int rightChild(int index) {
                return (index << 1) + 1;
            }
        }

    }

    private static class Violent extends TopKWords {

        Map<String, Integer> cnt;

        public Violent(int k) {
            super(k);
            cnt = new HashMap<>();
        }

        @Override
        void addWord(String word) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }

        @Override
        String[] topK() {
            return cnt.entrySet()
                    .stream()
                    .sorted(Comparator.comparingInt(entry -> ((Map.Entry<String, Integer>) entry).getValue()).reversed()
                            .thenComparing(entry -> ((Map.Entry<String, Integer>) entry).getKey())
                    )
                    .map(Map.Entry::getKey)
                    .limit(k)
                    .toArray(String[]::new);
        }

    }

}
