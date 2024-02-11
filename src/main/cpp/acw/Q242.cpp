//
// Created by Administrator on 2024/2/10 0010.
//

#include "iostream"
#include "stdio.h"

using namespace std;

const int N = 10e5 + 10;

int td[N];
int nums[N];

int lowbit(int x) {
    return x & -x;
}

void update(int x, int v) {
    while (x < N) {
        td[x] += v;
        x += lowbit(x);
    }
}

int query(int x) {
    int v = 0;
    while (x > 0) {
        v += td[x];
        x -= lowbit(x);
    }
    return v;
}

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;
    for (int i = 1; i <= n; ++i) {
        cin >> nums[i];
    }

    while (m--) {
        char op;
        cin >> op;

        if (op == 'Q') {

            int q;

            cin >> q;

            int x = query(q);

            cout << x + nums[q] << endl;

        } else {

            int l, r, d;

            cin >> l >> r >> d;

            update(l, d);
            update(r + 1, -d);
        }

    }

}