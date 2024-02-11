//
// Created by Administrator on 2024/2/10 0010.
//

#include "iostream"
#include "stdio.h"

#define LC(x) (x << 1)
#define RC(x) ((x << 1) | 1)
#define MID(l, r) ( l + ((r - l) >> 1) )

typedef long long LL;

using namespace std;

const int N = 10e5 + 10;

int w[N];

struct Node {
    int l, r;
    LL sum, add;
} nodes[N << 2];

void pushup(int rt) {
    nodes[rt].sum = nodes[LC(rt)].sum + nodes[RC(rt)].sum;
}

void pushDown(int rt) {

    Node &node = nodes[rt];

    if (node.add == 0) return;

    Node &left = nodes[LC(rt)];

    Node &right = nodes[RC(rt)];

    left.add += node.add;
    right.add += node.add;

    int mid = MID(node.l, node.r);

    int leftCnt = mid - node.l + 1;

    int rightCnt = node.r - mid;

    left.sum += leftCnt * node.add;
    right.sum += rightCnt * node.add;

    node.add = 0;
}

void buildTree(int rt, int l, int r) {

    nodes[rt] = {l, r};

    if (nodes[rt].l == nodes[rt].r) {
        nodes[rt].sum = w[l];
        return;
    }

    int mid = MID(l, r);

    buildTree(LC(rt), l, mid);
    buildTree(RC(rt), mid + 1, r);

    pushup(rt);
}

void update(int rt, int l, int r, int v) {

    Node &node = nodes[rt];

    if (node.l >= l && node.r <= r) {
        node.add += v;
        node.sum += v * (node.r - node.l + 1);
        return;
    }

    pushDown(rt);

    int mid = MID(node.l, node.r);

    if (l <= mid)
        update(LC(rt), l, r, v);
    if (r > mid)
        update(RC(rt), l, r, v);

    pushup(rt);
}

LL query(int rt, int l, int r) {

    Node &node = nodes[rt];

    if (node.l >= l && node.r <= r)
        return node.sum;

    pushDown(rt);

    int mid = MID(node.l, node.r);

    LL res = 0;

    if (l <= mid)
        res = query(LC(rt), l, r);
    if (r > mid)
        res += query(RC(rt), l, r);

    return res;
}

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= n; ++i)
        cin >> w[i];

    buildTree(1, 1, N);

    while (m--) {

        char op;

        cin >> op;

        int l, r;

        cin >> l >> r;

        if (op == 'Q') {
            cout << query(1, l, r) << endl;
        } else {

            int v;

            cin >> v;

            update(1, l, r, v);
        }
    }

}