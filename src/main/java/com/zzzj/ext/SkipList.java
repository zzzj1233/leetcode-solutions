package com.zzzj.ext;

import java.util.Random;

public class SkipList {

    private static final int MAX_LEVEL = 32;

    private static final Random RANDOM = new Random();

    private SkipNode head;

    private int level;

    private int size;

    public SkipList() {
        this.level = 1;
        this.head = new SkipNode(MAX_LEVEL);
    }

    private int getRandomLevel() {
        int level = 1;

        while ((RANDOM.nextInt() & 0xFFFF) < (0.25 * 0xFFFF))
            level += 1;

        return Math.min(level, MAX_LEVEL);
    }

    public boolean delete(double score) {
        SkipNode[] update = new SkipNode[MAX_LEVEL];

        int targetLevel = -1;

        for (int i = 0; i < this.level || (targetLevel != -1 && i < targetLevel); i++) {
            SkipNode x = this.head;
            while (x != null && x.nodes[i].next != null) {
                if (x.nodes[i].next.score == score) {
                    targetLevel = x.nodes[i].next.level;
                    break;
                }
                x = x.nodes[i].next;
            }
            update[i] = x;
        }

        if (targetLevel == -1) {
            return false;
        }

        for (int i = 0; i < targetLevel; i++) {
            update[i].nodes[i].next = update[i].nodes[i].next.nodes[i].next;
        }
        this.size--;
        return true;
    }

    public void put(double score, int value) {
        put(score, value, getRandomLevel());
    }

    public void put(double score, int value, int level) {
        SkipNode[] update = new SkipNode[MAX_LEVEL];

        for (int i = 0; i < this.level; i++) {
            SkipNode x = this.head;
            while (i < x.nodes.length && x.nodes[i].next != null) {
                if (x.nodes[i].next.score == score) {
                    x.nodes[i].next.data = value;
                    return;
                } else if (x.nodes[i].next.score > score) {
                    x = x.nodes[i].next;
                } else {
                    break;
                }
            }
            update[i] = x;
        }

        int lev = level;

        if (lev > this.level) {
            for (int i = this.level; i < lev; i++) {
                update[i] = head;
            }
            this.level = lev;
        }

        SkipNode node = new SkipNode(lev, score, value);

        for (int i = 0; i < lev; i++) {
            try {
                node.nodes[i].next = update[i].nodes[i].next;
            } catch (Exception e) {
                return;
            }
            update[i].nodes[i].next = node;
        }

        this.size++;
    }

    public int get(double score) {
        for (int i = this.level - 1; i >= 0; i--) {
            SkipNode x = this.head;
            while (x != null) {
                x = x.nodes[i];
                if (x.score == score) {
                    return x.data;
                }
                x = x.next;
                if (x.score == score) {
                    return x.data;
                }
            }
        }

        return -1;
    }

    public void getInfo() {
        SkipNode x;

        for (int i = 0; i < this.level; i++) {
            x = this.head.nodes[i];
            while (x != null) {
                System.out.print(x.data + ", ");
                if (x.nodes != null) {
                    SkipNode node = x.nodes[i];
                    x = node.next;
                } else {
                    x = x.next;
                }
            }
            System.out.println();
        }
    }

    public static class SkipNode {
        private SkipNode next;
        private double score;
        private int data;
        private SkipNode[] nodes;
        private int level;

        public SkipNode() {

        }

        public SkipNode(int level) {
            this(level, 0, 0);
        }

        public SkipNode(int level, double score, int data) {
            this.nodes = new SkipNode[level];
            this.level = level;
            this.score = score;
            this.data = data;
            for (int i = 0; i < level; i++) {
                this.nodes[i] = new SkipNode();
                this.nodes[i].score = score;
                this.nodes[i].data = data;
                this.nodes[i].level = level;
            }
        }
    }

    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.put(5, 5);
        list.put(1, 1);
        list.put(10, 10, 5);
        list.put(3, 3, 6);
        list.put(6, 6, 4);
        list.getInfo();

        System.out.println(list.delete(10));

        list.getInfo();
    }

}