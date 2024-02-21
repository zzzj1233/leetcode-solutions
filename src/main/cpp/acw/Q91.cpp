#include "iostream"
#include "cstring"
#include "algorithm"
#include "vector"

using namespace std;

const int N = 21;

const int INF = 0x3f3f3f3f;

int dis[N][N];

vector<int> as[N];

int countSetBits(int n) {
    int count = 0;
    while (n) {
        count += n & 1;
        n >>= 1;
    }
    return count;
}

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    int limit = 1 << n;

    for (int i = 0; i < n; ++i) {
        for (int other = 0; other < n; ++other) {
            cin >> dis[i][other];
        }
    }

    for (int s = 0; s < limit; ++s) {
        // 必须包含节点1
        if (s & 1)
            as[countSetBits(s)].push_back(s);
    }

    int f[n][limit];

    memset(f, INF, sizeof f);

    f[0][1] = 0;

    for (int i = 1; i <= n; ++i) {

        for (int s : as[i]) {

            for (int end = 0; end < n; ++end) {

                if (s & (1 << end)) {

                    int ps = s ^(1 << end);

                    for (int prev = 0; prev < n; ++prev) {

                        if (prev != end && s & (1 << prev)) {

                            if (f[prev][ps] != INF)
                                f[end][s] = min(f[end][s], f[prev][ps] + dis[prev][end]);

                        }

                    }

                }

            }

        }

    }


    cout << f[n - 1][limit - 1] << endl;

}