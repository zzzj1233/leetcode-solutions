//
// Created by Administrator on 2024/2/8 0008.
//

#include "iostream"
#include "algorithm"

using namespace std;

const int N = 10010;

int pa[N];

int value[N];

int price[N];

int find(int x) {
    while (pa[x] != x) {
        pa[x] = pa[pa[x]];
        x = pa[x];
    }
    return x;
}


#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m, V;

    cin >> n >> m >> V;

    for (int i = 0; i <= n; ++i)
        pa[i] = i;

    for (int i = 1; i <= n; ++i)
        cin >> price[i] >> value[i];

    for (int i = 0; i < m; ++i) {

        int c1, c2;

        cin >> c1 >> c2;

        int fx = find(c1);

        int fy = find(c2);

        if (fx != fy) {
            pa[fx] = fy;
            value[fy] += value[fx];
            price[fy] += price[fx];
        }

    }

    int f[V + 1];

    std::fill(f, f + V + 1, 0);

    for (int i = 1; i <= n; ++i) {

        int fi = find(i);

        if (fi != i)
            continue;

        int val = value[fi];

        int pri = price[fi];

        for (int j = V; j >= pri; j--)
            f[j] = max(f[j], f[j - pri] + val);

    }

    cout << f[V] << endl;
}
