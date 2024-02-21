#include "cstdio"

#define LC(x) (x << 1)
#define RC(x) ((x << 1) | 1)
#define MID(l, r) ( l + ((r - l) >> 1) )

typedef long long LL;

using namespace std;

const int N = 1e5 + 10;

const int MOD = 1e9 + 7;

struct node {
    int l, r;
    LL v;
    LL add, mul;
} nodes[N << 2];

void build(int rt, int l, int r) {
    auto &node = nodes[rt];
    node.l = l;
    node.r = r;
    node.mul = 1;
    node.v = 0;
    node.add = 0;

    if (l == r)
        return;

    int mid = MID(l, r);

    build(LC(rt), l, mid);

    build(RC(rt), mid + 1, r);
}

void add(int rt, int index, int v) {

    if (nodes[rt].l == nodes[rt].r && nodes[rt].l == index) {
        nodes[rt].v = v;
        nodes[rt].mul = 1;
        nodes[rt].add = 0;
        return;
    }

    int mid = MID(nodes[rt].l, nodes[rt].r);

    if (index <= mid)
        add(LC(rt), index, v);
    else
        add(RC(rt), index, v);

}

void pushdown(int rt) {

    auto &node = nodes[rt];

    if (node.add > 0 || node.mul > 1) {

        if (node.l == node.r) {
            node.v = (node.v * node.mul + node.add) % MOD;
        } else {
            auto &left = nodes[LC(rt)];
            auto &right = nodes[RC(rt)];

            left.add = (left.add * node.mul + node.add) % MOD;
            left.mul = (left.mul * node.mul) % MOD;

            right.add = (right.add * node.mul + node.add) % MOD;
            right.mul = (right.mul * node.mul) % MOD;
        }
        node.add = 0;
        node.mul = 1;
    }

}

void update(int rt, int l, int r, int add, int mul) {

    auto &node = nodes[rt];

    if (node.l >= l && node.r <= r) {
        node.add = (node.add * mul + add) % MOD;
        node.mul = (node.mul * mul) % MOD;
        return;
    }

    pushdown(rt);

    int mid = MID(node.l, node.r);

    if (l <= mid)
        update(LC(rt), l, r, add, mul);
    if (r > mid)
        update(RC(rt), l, r, add, mul);

}

int get(int rt, int index) {

    pushdown(rt);

    if (nodes[rt].l == nodes[rt].r && nodes[rt].l == index) {
        return nodes[rt].v;
    }

    int mid = MID(nodes[rt].l, nodes[rt].r);

    if (index <= mid)
        return get(LC(rt), index);
    else
        return get(RC(rt), index);
}

class Fancy {
public:

    int index;

    Fancy() {
        index = 1;
        build(1, 1, N);
    }

    void append(int val) {
        add(1, index++, val);
    }

    void addAll(int inc) {
        update(1, 1, index - 1, inc, 1);
    }

    void multAll(int m) {
        update(1, 1, index - 1, 0, m);
    }

    int getIndex(int idx) {
        if (idx + 1 >= index)
            return -1;
        return get(1, idx + 1);
    }

};

int main() {

    auto f = Fancy();
    f.append(2);
    f.addAll(3);
    f.append(7);
    f.multAll(2);

    printf("0 - %d \n", f.getIndex(0));
    printf("1 - %d \n", f.getIndex(1));

    f.addAll(3);

    f.append(10);

    f.multAll(2);

    printf("0 - %d \n", f.getIndex(0));
    printf("1 - %d \n", f.getIndex(1));
    printf("2 - %d \n", f.getIndex(2));

    return 0;
}