#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 15;

const int MOD = 1e8;

const int LIMIT = 1 << N;

int w[N];

int f[N][LIMIT];

#include "zzzj.h"

bool hasAdjacentOnes(int n) {
    return ((n & (n << 1)) != 0);
}

int countSetBits(int n) {
    int count = 0;
    while (n) {
        count += n & 1;
        n >>= 1;
    }
    return count;
}

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int m, n;

    cin >> m >> n;

    int limit = 1 << n;

    for (int i = 1; i <= m; ++i) {

        int x;

        for (int j = 0; j < n; ++j) {
            cin >> x;
            x = x ^ 1;
            w[i] |= x << j;
        }

    }

    f[0][0] = 1;

    int ans = 0;

    for (int i = 1; i <= m; ++i) {
        for (int stat = 0; stat < limit; ++stat) {
            if (stat & w[i] || hasAdjacentOnes(stat))
                continue;
            for (int prev = 0; prev < limit; ++prev) {
                if (prev & stat)
                    continue;
                f[i][stat] = (f[i][stat] + f[i - 1][prev]) % MOD;
            }
            ans = (ans + f[m][stat]) % MOD;
        }
    }

    cout << ans << endl;
}