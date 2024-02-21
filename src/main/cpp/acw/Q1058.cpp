#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1e5 + 10;

const int INF = 1e8;

int f[N][3];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    // 0 = 未持有
    // 1 = 已持有
    // 2 = 已卖出

    // 0 -> 1
    // 1 -> 2
    // 2 -> 0

    f[1][0] = 0;
    f[1][1] = -INF;

    for (int i = 2; i <= n + 1; ++i) {

        int p;

        cin >> p;

        f[i][0] = max(f[i - 1][0], f[i - 2][2]);
        f[i][1] = max(f[i - 1][1], f[i][0] - p);
        f[i][2] = max(f[i - 1][2], f[i][1] + p);

        // printf(" i = %d , f0 = %d , f1 = %d , f2 = %d \n", i, f[i][0], f[i][1], f[i][2]);
    }

    cout << max(f[n + 1][2], f[n + 1][0]) << endl;

}