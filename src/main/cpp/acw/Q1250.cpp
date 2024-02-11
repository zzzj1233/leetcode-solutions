//
// Created by Administrator on 2024/2/8 0008.
//

#include "zzzj.h"
#include "iostream"

const int N = 210 * 210;
int n, m;

int pa[N];

int find(int x) {
    while (pa[x] != x) {
        pa[x] = pa[pa[x]];
        x = pa[x];
    }
    return x;
}

void connect(int x, int y) {
    pa[find(x)] = find(y);
}

int get(int x, int y) {
    return x * n + y;
}

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    using namespace std;

    std::cin >> n >> m;

    for (int i = 0; i <= n * n; ++i)
        pa[i] = i;

    for (int i = 0; i < m; ++i) {

        int x, y;

        char op;

        scanf("%d %d %c", &x, &y, &op);

        // printf("x = %d , y = %d , op = %c \n", x, y, op);

        x--;

        y--;

        int index = get(x, y);

        int next;

        if (op == 'D')
            next = get(x + 1, y);
        else
            next = get(x, y + 1);

        if (find(index) == find(next)) {
            cout << i + 1 << endl;
            return 0;
        } else {
            connect(index, next);
        }

    }

    cout << "draw" << endl;

    return 0;
};