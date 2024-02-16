#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 110;

int a[N][N];

int f[N][N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> a[i][j];
        }
    }

    // memset(f, INF, sizeof f);

    f[0][0] = a[0][0];

    for (int i = 1; i < n; ++i) {
        f[0][i] = f[0][i - 1] + a[0][i];
    }

    for (int i = 1; i < n; ++i) {
        f[i][0] = f[i - 1][0] + a[i][0];
    }

    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < n; ++j) {
            f[i][j] = min(
                    f[i - 1][j],
                    f[i][j - 1]
            ) + a[i][j];
        }
    }

    cout << f[n - 1][n - 1] << endl;

}