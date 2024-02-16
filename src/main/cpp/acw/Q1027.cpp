

#include "iostream"
#include "cstring"
#include "algorithm"

#include "zzzj.h"

const int N = 12;

int a[N][N];

int f[N][N][N][N];

using namespace std;

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;
    while (true) {
        int x, y, w;
        cin >> x >> y >> w;
        if (!x && !y && !w) break;
        a[x][y] += w;
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    if (i + j != x + y) continue;
                    int &ans = f[i][j][x][y];
                    ans = max(ans, f[i - 1][j][x - 1][y]);
                    ans = max(ans, f[i - 1][j][x][y - 1]);
                    ans = max(ans, f[i][j - 1][x - 1][y]);
                    ans = max(ans, f[i][j - 1][x][y - 1]);
                    if (i == x && j == y) ans += a[i][j];
                    else ans += a[i][j] + a[x][y];
                }
            }
        }
    }
    cout << f[n][n][n][n] << endl;
    return 0;

}