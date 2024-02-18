#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int K = 110;

const int INF = 1e8;

int f[K][2];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, k;

    cin >> n >> k;

    for (int j = 0; j <= k; ++j) {
        f[j][0] = f[j][1] = -INF;
    }

    f[0][1] = 0;

    for (int i = 1; i <= n; ++i) {

        int p;

        cin >> p;

        for (int j = 1; j <= k; ++j) {
            f[j][0] = max(f[j][0], f[j - 1][1] - p);
        }

        for (int j = 1; j <= k; ++j) {
            f[j][1] = max(f[j][1], f[j][0] + p);
        }

    }

    int ans = 0;

    for (int j = 1; j <= k; ++j)
        ans = max(ans, f[j][1]);

    cout << ans << endl;

}