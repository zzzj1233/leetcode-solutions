package com.zzzj.tree;

/**
 * 线段树
 *
 * @author zzzj
 * @create 2021-04-25 11:10
 */
public class SegmentTree$OLD {

    private int[] tree;

    private int[] data;

    public SegmentTree$OLD(int[] arr) {
        this.data = arr.clone();
        // 完全二叉树的最大长度就是 4n
        tree = new int[(arr.length << 2)];
        // 构建线段树
        buildSegmentTree(0, 0, arr.length - 1);
    }

    private static int getLeftChild(int index) {
        return (index << 1) + 1;
    }

    private static int getRightChild(int index) {
        return (index << 1) + 2;
    }

    private void buildSegmentTree(int index, int rangeL, int rangeR) {
        if (rangeL == rangeR) {
            tree[index] = data[rangeL];
            return;
        }

        int mid = (rangeR + rangeL) / 2;

        int leftChild = getLeftChild(index);
        int rightChild = getRightChild(index);

        // 左边 rangeL ~ mid
        buildSegmentTree(leftChild, rangeL, mid);
        // 右边 mid + 1 ~ rangeR
        buildSegmentTree(rightChild, mid + 1, rangeR);

        // sum :
        tree[index] = tree[leftChild] + tree[rightChild];
    }

    public void update(int index, int value) {
        data[index] = value;
        update(index, 0, 0, data.length - 1, value);
    }

    private void update(int index, int treeIndex, int rangeL, int rangeR, int value) {
        if (rangeL == index && rangeR == index) {
            tree[treeIndex] = value;
            return;
        }

        int mid = (rangeR + rangeL) / 2;

        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);

        // 左边
        if (index <= mid) {
            update(index, leftChild, rangeL, mid, value);
            // 右边
        } else {
            update(index, rightChild, mid + 1, rangeR, value);
        }

        // 递归回来更新值
        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }

    public int getRange(int searchL, int searchR) {
        if (searchL < 0 || searchR >= data.length || searchL > searchR) {
            return -1;
        }
        return getRange(0, 0, data.length - 1, searchL, searchR);
    }

    private int getRange(int index, int rangeL, int rangeR, int searchL, int searchR) {
        if (rangeL >= searchL && rangeR <= searchR) {
            return tree[index];
        }

        int leftChild = getLeftChild(index);
        int rightChild = getRightChild(index);

        int leftValue = 0;
        int rightValue = 0;

        int mid = (rangeR + rangeL) / 2;

        if (mid >= searchL) {
            leftValue = getRange(leftChild, rangeL, mid, searchL, searchR);
        }

        if ((mid + 1) <= searchR) {
            rightValue = getRange(rightChild, mid + 1, rangeR, searchL, searchR);
        }

        return leftValue + rightValue;
    }


    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7};

        SegmentTree$OLD segmentTree$OLD = new SegmentTree$OLD(data);

        System.out.println(segmentTree$OLD.getRange(2, 5));

        segmentTree$OLD.update(3, 9);

        System.out.println(segmentTree$OLD.getRange(2, 5));
    }

}
