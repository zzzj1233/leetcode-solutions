package com.zzzj.contest.week387;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Q4 {

    final int range = 10000010;
    final int INF = 0x3f3f3f3f;

    static class Node {

        int key, val, cnt, size;
        Node left, right;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            // System.out.println(val);
            this.cnt = this.size = 1;
        }

    }

    int rand() {
        return new Random().nextInt(2 * range);
    }

    Node zig(Node node) {//右旋, 返回此时的该位置节点
        Node p = node.left;
        node.left = p.right;
        p.right = node;
        pushup(p.right);
        pushup(p);
        return p;
    }

    Node zag(Node node) {//左旋
        Node p = node.right;
        node.right = p.left;
        p.left = node;
        pushup(p.left);
        pushup(p);
        return p;
    }

    void pushup(Node node) {

        int lsize, rsize;
        lsize = rsize = 0;
        if (node.left != null) lsize = node.left.size;
        if (node.right != null) rsize = node.right.size;
        node.size = node.cnt + lsize + rsize;

    }

    Node insert(Node node, int key) {

        if (node == null) return new Node(key, rand());

        if (node.key == key) node.cnt++;

        if (key < node.key) {
            node.left = insert(node.left, key);
            if (node.left.val > node.val) node = zig(node);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
            if (node.right.val > node.val) node = zag(node);
        }
        pushup(node);
        return node;
    }

    Node remove(Node node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = remove(node.left, key);
            pushup(node);
            return node;
        }

        if (key > node.key) {
            node.right = remove(node.right, key);
            pushup(node);
            return node;
        }

        //相等的时候
        if (node.cnt > 1) {
            node.cnt--;
            pushup(node);
            return node;
        }
        if (node.left == null && node.right == null) return null;
        else {
            if (node.left == null) {//左旋
                node = zag(node);
                node.left = remove(node.left, key);
                pushup(node);
                return node;
            }
            if (node.right == null) {//右旋
                node = zig(node);
                node.right = remove(node.right, key);
                pushup(node);
                return node;
            }
            if (node.left.key > node.right.key) {//左孩子上来， 右旋
                node = zig(node);
                node.right = remove(node.right, key);
            } else {//右孩子上来， 左旋
                node = zag(node);
                node.left = remove(node.left, key);
            }
        }

        pushup(node);
        return node;
    }


    int getKeyByRang(Node node, int range) {
        if (node == null) return INF;

        Node left = node.left;
        Node right = node.right;
        int lsize = left == null ? 0 : left.size;

        if (lsize >= range) return getKeyByRang(node.left, range);

        if (lsize + node.cnt >= range) return node.key;

        return getKeyByRang(node.right, range - lsize - node.cnt);
    }

    int getRangByKey(Node node, int key) {

        if (node == null)
            return 0;

        Node right = node.right;

        int rsize = right == null ? 0 : right.size;

        if (key < node.key)
            return rsize + getRangByKey(node.left, key) + (key == node.key ? 0 : node.cnt);

        return getRangByKey(node.right, key);
    }

    int getPre(Node node, int key) {
        if (node == null) return -INF;
        if (key <= node.key) return getPre(node.left, key);
        return Math.max(node.key, getPre(node.right, key));
    }

    int getNext(Node node, int key) {
        if (node == null) return INF;
        if (key >= node.key) return getNext(node.right, key);
        return Math.min(node.key, getNext(node.left, key));
    }


    Node build() {
        Node root = new Node(-INF, rand());
        root.right = new Node(INF, rand());
        pushup(root);
        if (root.val < root.right.val) root = zag(root);
        return root;
    }

    public int[] resultArray(int[] nums) {

        Node left = build();
        Node right = build();

        int N = nums.length;

        left = insert(left, nums[0]);

        right = insert(right, nums[1]);

        List<Integer> l = new ArrayList<>();

        List<Integer> r = new ArrayList<>();

        l.add(nums[0]);
        r.add(nums[1]);

        for (int i = 2; i < N; i++) {

            int leftCnt = getRangByKey(left, nums[i]);

            int rightCnt = getRangByKey(right, nums[i]);

            if (leftCnt > rightCnt) {
                left = insert(left, nums[i]);
                l.add(nums[i]);
            } else if (leftCnt < rightCnt) {
                right = insert(right, nums[i]);
                r.add(nums[i]);
            } else if (left.size <= right.size) {
                left = insert(left, nums[i]);
                l.add(nums[i]);
            } else {
                right = insert(right, nums[i]);
                r.add(nums[i]);
            }

        }

        l.addAll(r);

        return l.stream().mapToInt(value -> value).toArray();
    }

    // 输入：
    // [9,58,43,42,46,25,38,2,42,72,54,96,78,44,2,52,74,70,66,8]
    // 输出：
    // [9,72,54,96,78,44,2,52,74,70,66,8,58,43,42,46,25,38,2,42]
    // 预期：
    // [9,72,54,96,78,44,52,74,70,66,8,58,43,42,46,25,38,2,42,2]
    public static void main(String[] args) {

//        System.out.println(Arrays.toString(resultArray(new int[]{2, 1, 3, 3})));

//        System.out.println(Arrays.toString(resultArray(new int[]{5, 14, 3, 1, 2})));

//        System.out.println(Arrays.toString(resultArray(new int[]{3, 3, 3, 3})));

        Q4 q4 = new Q4();

        // System.out.println(Arrays.toString(q4.resultArray(new int[]{9, 58, 43, 42, 46, 25, 38, 2, 42, 72, 54, 96, 78, 44, 2, 52, 74, 70, 66, 8})));

        System.out.println(Arrays.toString(q4.resultArray(new int[]{2, 97, 3, 19, 78, 5, 10, 87, 70, 7, 34, 86, 40, 15, 26, 45, 6, 99, 30, 20, 67, 67, 36, 9, 85, 73, 75, 81, 90, 4, 99, 76, 85, 74, 69})));

    }

}
