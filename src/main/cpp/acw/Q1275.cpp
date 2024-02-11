//
// Created by Administrator on 2024/2/10 0010.
//
#include "stdio.h"
#include "iostream"
#include "algorithm"

#define LC(x) ((x << 1))
#define RC(x) ((x << 1) | 1)
#define MID(l, r) (l + ((r - l) >> 1))

using namespace std;

typedef long long LL;

const int N = 2 * (1e5 + 10);

struct Node {
    int l, r, max;
} nodes[N << 2];

void pushUp(int rt) {
    nodes[rt].max = max(nodes[LC(rt)].max, nodes[RC(rt)].max);
}

void buildTree(int rt, int l, int r) {

    nodes[rt] = {l, r};

    if (l == r) return;

    int mid = MID(l, r);

    buildTree(LC(rt), l, mid);
    buildTree(RC(rt), mid + 1, r);

}

int query(int rt, int l, int r) {

    if (nodes[rt].l >= l && nodes[rt].r <= r)
        return nodes[rt].max;

    int mid = MID(nodes[rt].l, nodes[rt].r);

    int v = 0;

    if (l <= mid)
        v = query(LC(rt), l, r);
    if (r > mid)
        v = max(v, query(RC(rt), l, r));

    return v;
}

void update(int rt, int i, int v) {

    if (nodes[rt].l == nodes[rt].r) {
        nodes[rt].max = v;
        return;
    }

    int mid = MID(nodes[rt].l, nodes[rt].r);

    if (i <= mid)
        update(LC(rt), i, v);
    else
        update(RC(rt), i, v);

    pushUp(rt);
}

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int m, p;

    cin >> m >> p;

    buildTree(1, 1, N);

    int a = 0;

    int n = 0;

    while (m--) {

        char op;
        cin >> op;

        int x;
        cin >> x;

        if (op == 'A') {
            update(1, n + 1, ((LL) x + a) % p);
            n++;
        } else {
            a = query(1, n - x + 1, n);
            cout << a << endl;
        }

    }

}