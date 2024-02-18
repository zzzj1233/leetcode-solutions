#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 12;

const int M = 17;

int a[N][M];
int f[N][M];
int p[N][M];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= m; ++j)
            cin >> a[i][j];

    memset(f, 0, sizeof f);
    memset(p, 0, sizeof p);

    for (int i = n; i >= 1; --i) {

        for (int j = 1; j <= m; ++j) {

            f[i][j] = f[i + 1][j];
            p[i][j] = j;

            for (int k = 1; k <= j; ++k) {

                if (f[i + 1][j - k] + a[i][k] > f[i][j]) {
                    f[i][j] = f[i + 1][j - k] + a[i][k];
                    p[i][j] = j - k;
                }

            }

        }

    }

    cout << f[1][m] << endl;

    int r = 1;
    int c = m;

    while (r <= n) {

        cout << r << " " << c - p[r][c] << endl;
        c = p[r][c];

        r++;
    }
}