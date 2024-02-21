#include "iostream"
#include "algorithm"
#include "vector"

using namespace std;

const int N = 110;

const int M = 12;

const int LIMIT = 1 << M;

int w[N];

int f[2][LIMIT][LIMIT];

int cnt[LIMIT];

vector<int> a;
vector<int> pa[LIMIT];

#include "zzzj.h"

bool hasAdjacentOnes(int n) {
    return ((n & (n << 1)) != 0);
}

bool check(int s) {

    if (hasAdjacentOnes(s))
        return false;

    for (int i = 2; i < 32; ++i) {
        if (s & (1 << i) && s & (1 << (i - 2)))
            return false;
    }

    return true;
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

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= n; ++i) {
        for (int j = 0; j < m; ++j) {
            char c;
            cin >> c;
            if (c == 'H') w[i] |= 1 << j;
        }
    }

    int limit = 1 << m;

    for (int s = 0; s < limit; ++s) {
        if (!check(s))
            continue;
        a.push_back(s);
    }

    for (auto x : a) {
        for (auto y : a) {
            if (!(x & y))
                pa[x].push_back(y);
        }
        cnt[x] = countSetBits(x);
    }

    int ans = 0;

    for (int i = 1; i <= n; ++i) {

        for (int s : a) {

            if (s & w[i])
                continue;

            for (int p : pa[s]) {

                // 当前状态为s , 上一行的状态为p

                // f[i - 1][p]

                for (int pp : pa[p]) {

                    if (pp & s)
                        continue;

                    f[i & 1][s][p] = max(f[i & 1][s][p], f[(i - 1) & 1][p][pp] + cnt[s]);
                }

                ans = max(ans, f[i & 1][s][p]);
            }

        }

    }

    cout << ans << endl;
}