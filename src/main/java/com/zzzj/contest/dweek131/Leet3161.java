package com.zzzj.contest.dweek131;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2024-05-27 11:14
 */
public class Leet3161 {

    public static void main(String[] args) {

        // System.out.println(getResults(LeetUtils.convertInts("[[1,2],[2,3,3],[2,3,1],[2,2,2]]")));

        System.out.println(getResults(LeetUtils.convertInts("[[1,9],[1,6],[2,9,1],[2,1,2]]")));

    }

    static final int N = (int) 1e5;

    public static List<Boolean> getResults(int[][] queries) {

        Node root = new Node();
        root.l = 1;
        root.r = N;

        List<Boolean> ans = new ArrayList<>();

        // buildTree(1, N, root);

        TreeSet<Integer> sep = new TreeSet<>();

        sep.add(0);
        sep.add(N);

        for (int[] query : queries) {

            if (query[0] == 1) {

                int s = query[1];

                int prev = sep.floor(s);

                int tail = sep.ceiling(s);

                update(
                        s + 1,
                        tail,
                        root,
                        prev - s
                );

                sep.add(s);

            } else {
                int q = query(query[1], root);
                ans.add(
                        q >= query[2]
                );
            }

        }

        return ans;
    }

    private static void update(int l, int r, Node node, int v) {

        if (node.l == node.r) {
            node.d += v;
            return;
        }

        if (node.l >= l && node.r <= r) {
            node.lazy = true;
            node.d += v;
            node.u += v;
            return;
        }

        pushDown(node);

        int m = node.l + ((node.r - node.l) >> 1);

        if (l <= m)
            update(l, r, node.lc(), v);
        if (r > m)
            update(l, r, node.rc(), v);

        pushUp(node);
    }

    private static int query(int r, Node node) {

        if (node.r <= r)
            return node.d;

        pushDown(node);

        int m = node.l + ((node.r - node.l) >> 1);

        int lm = query(r, node.lc());

        int rm = r > m ? query(r, node.rc()) : 0;

        return Math.max(lm, rm);
    }

    private static void pushDown(Node node) {
        if (node.lazy) {

            Node left = node.lc();

            Node right = node.rc();

            left.lazy = true;
            left.u += node.u;
            left.d += node.u;

            right.lazy = true;
            right.u += node.u;
            right.d += node.u;

            node.lazy = false;
            node.u = 0;
        }

    }

    private static void pushUp(Node node) {
        node.d = Math.max(
                node.lc().d,
                node.rc().d
        );
    }

    private static class Node {
        int l, r, d, u;
        boolean lazy;
        Node lc, rc;

        public Node lc() {
            if (lc == null) {
                lc = new Node();
                lc.l = l;
                lc.r = l + ((r - l) >> 1);
                lc.d = lc.r;
            }
            return lc;
        }

        public Node rc() {
            if (rc == null) {
                rc = new Node();
                rc.r = r;
                rc.l = l + ((r - l) >> 1) + 1;
                rc.d = r;
            }
            return rc;
        }
    }

}
