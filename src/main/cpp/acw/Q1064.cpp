#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 12;

const int K = N * N;

const int LIMIT = 1 << N;

long f[N][K][LIMIT];

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

    int n, k;

    cin >> n >> k;

    f[0][0][0] = 1;

    int limit = 1 << n;

    for (int i = 1; i <= n; ++i) {

        for (int j = 0; j <= k; ++j) {

            for (int stat = 0; stat < limit; ++stat) {

                int cnt = countSetBits(stat);

                if (cnt > j || hasAdjacentOnes(stat))
                    continue;

                // 检查状态是否合法
                for (int prev = 0; prev < limit; ++prev) {

                    if ((prev & stat) != 0 || hasAdjacentOnes(prev | stat))
                        continue;

                    f[i][j][stat] += f[i - 1][j - cnt][prev];
                }

            }

        }

    }

    long ans = 0;

    for (int i = 0; i < limit; ++i) {
        ans += f[n][k][i];
    }

    cout << ans << endl;
}