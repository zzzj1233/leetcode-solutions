#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1010;

const int M = 510;

const int K = 110;

int a[K][2];

int f[N][M];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m, k;

    cin >> n >> m >> k;

    for (int i = 1; i <= k; ++i) {
        cin >> a[i][0] >> a[i][1];
    }

    int maxc = 0;

    for (int i = 1; i <= k; ++i) {
        for (int j = n; j >= a[i][0]; --j) {
            for (int x = m; x >= a[i][1]; --x) {
                f[j][x] = max(f[j][x], f[j - a[i][0]][x - a[i][1]] + 1);
            }
        }
    }

    int maxH = 0;

    for (int i = m - 1; i >= 0; --i) {
        maxc = max(maxc, f[n][i]);
        if (f[n][i] == maxc)
            maxH = i;
    }

    cout << maxc << " " << m - maxH << endl;
}