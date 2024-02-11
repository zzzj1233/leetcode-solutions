
#include "iostream"
#include "stdio.h"

using namespace std;

const int N = 30010;

int pa[N];
int s[N];
int dis[N];

int find(int x) {
    if (pa[x] != x) {
        int root = find(pa[x]);
        dis[x] += dis[pa[x]];
        pa[x] = root;
    }
    return pa[x];
}

void connect(int x, int y) {
    int fx = find(x);
    int fy = find(y);
    if (fx == fy)
        return;
    pa[fx] = fy;
    dis[fx] = s[fy];
    s[fy] += s[fx];
}

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    for (int i = 0; i < N; ++i)
        pa[i] = i, s[i] = 1;

    for (int i = 0; i < n; ++i) {
        char op;
        int x, y;
        cin >> op >> x >> y;
        if (op == 'M') {
            connect(x, y);
        } else {
            if (x == y) {
                puts("0");
                continue;
            }
            int fx = find(x);
            int fy = find(y);
            if (fx != fy)
                puts("-1");
            else
                cout << abs(dis[x] - dis[y]) - 1 << endl;
        }

    }

}