
#include "stdio.h"
#include "iostream"

#define LC(x) (x << 1)
#define RC(x) ((x << 1) | 1)
#define MID(l, r) ( l + ((r - l) >> 1) )

using namespace std;

#include "zzzj.h"

const int N = 500010;

struct Node {
    int l, r;
    int sum, prefixMax, suffixMax, max;
} nodes[N << 2];

int w[N];

void pushup(Node &node, Node &left, Node &right) {
    node.sum = left.sum + right.sum;
    node.prefixMax = max(left.prefixMax, left.sum + right.prefixMax);
    node.suffixMax = max(right.suffixMax, right.sum + left.suffixMax);
    node.max = max(max(left.max, right.max), left.suffixMax + right.prefixMax);
}

void pushup(int rt) {
    pushup(nodes[rt], nodes[LC(rt)], nodes[RC(rt)]);
}

void buildTree(int rt, int l, int r) {

    nodes[rt] = {l, r};

    if (l == r) {
        nodes[rt].sum = w[l];
        nodes[rt].prefixMax = w[l];
        nodes[rt].suffixMax = w[l];
        nodes[rt].max = w[l];
        return;
    }

    int mid = MID(l, r);

    buildTree(LC(rt), l, mid);
    buildTree(RC(rt), mid + 1, r);

    pushup(rt);
}

void update(int rt, int u, int v) {

    Node &node = nodes[rt];

    if (node.l == node.r) {
        node.sum = v;
        node.prefixMax = v;
        node.suffixMax = v;
        node.max = v;
        return;
    }

    int mid = MID(node.l, node.r);

    if (u <= mid)
        update(LC(rt), u, v);
    else
        update(RC(rt), u, v);

    pushup(rt);
}

Node query(int rt, int l, int r) {

    Node node = nodes[rt];

    if (node.l >= l && node.r <= r)
        return node;

    if (node.l == node.r)
        return node;

    int mid = MID(node.l, node.r);

    if (l > mid)
        return query(RC(rt), l, r);
    else if (r <= mid)
        return query(LC(rt), l, r);
    else {
        Node left = query(LC(rt), l, r);
        Node right = query(RC(rt), l, r);
        Node res;
        pushup(res, left, right);
        return res;
    }
}

int main() {


#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= n; ++i) {
        cin >> w[i];
    }

    buildTree(1, 1, N);

    while (m--) {

        int op, x, y;

        cin >> op >> x >> y;

        if (op == 1) {

            if (x > y) swap(x, y);

            cout << query(1, x, y).max << endl;
        } else {
            update(1, x, y);
        }

    }

}