//
// Created by Administrator on 2024/2/10 0010.
//

#include "iostream"
#include "cstdio"

#define LC(x) (x << 1)
#define RC(x) ((x << 1) | 1)
#define MID(l, r) ( l + ((r - l) >> 1) )

typedef long long LL;

using namespace std;

const int N = 1e5 + 10;

int w[N];

int p;

struct Node {
    int l, r;
    LL add, mul, sum;
} nodes[N << 2];

void eval(Node &node, LL mul, LL add) {
    node.sum = (node.sum * mul + (node.r - node.l + 1) * add) % p;
    node.add = (node.add * mul + add) % p;
    node.mul = (node.mul * mul) % p;
}

void pushdown(int rt) {

    auto &node = nodes[rt];

    if (node.mul == 1 && node.add == 0)
        return;

    eval(nodes[LC(rt)], node.mul, node.add);
    eval(nodes[RC(rt)], node.mul, node.add);

    node.mul = 1;
    node.add = 0;
}


void pushup(int rt) {
    nodes[rt].sum = (nodes[LC(rt)].sum + nodes[RC(rt)].sum) % p;
}

void buildTree(int rt, int l, int r) {

    nodes[rt] = {l, r, 0, 1, 0};

    if (l == r) {
        nodes[rt].sum = w[l];
        return;
    }

    int mid = MID(l, r);

    buildTree(LC(rt), l, mid);
    buildTree(RC(rt), mid + 1, r);

    pushup(rt);
}

LL query(int rt, int l, int r) {

    auto &node = nodes[rt];

    if (node.l >= l && node.r <= r)
        return node.sum;

    pushdown(rt);

    int mid = MID(node.l, node.r);

    LL s = 0;

    if (l <= mid)
        s = query(LC(rt), l, r) % p;
    if (r > mid)
        s = (s + query(RC(rt), l, r)) % p;

    return s;
}

void update(int rt, int l, int r, int op, int v) {

    auto &node = nodes[rt];

    if (node.l >= l && node.r <= r) {
        if (op == 1) {
            eval(node, v, 0);
        } else {
            eval(node, 1, v);
        }
    } else {

        pushdown(rt);

        int mid = MID(node.l, node.r);

        if (l <= mid)
            update(LC(rt), l, r, op, v);
        if (r > mid)
            update(RC(rt), l, r, op, v);

        pushup(rt);
    }

}

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> p;

    for (int i = 1; i <= n; ++i) {
        cin >> w[i];
    }

    buildTree(1, 1, N);

    cin >> m;

    while (m--) {

        int op, l, r, v;

        cin >> op >> l >> r;

        if (op == 3) {
            cout << query(1, l, r) << endl;
        } else {
            cin >> v;
            update(1, l, r, op, v);
        }

    }

}