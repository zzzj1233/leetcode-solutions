#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 420;

const int INF = 1E8;

int a[N];

int f[N][N];
int f2[N][N];

int s[N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    for (int i = 0; i < n; ++i) {
        int v;
        cin >> v;
        a[i] = a[i + n] = v;
    }

    int m = n << 1;

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < m; ++j) {
            f2[i][j] = INF;
        }
        f2[i][i] = 0;
    }

    for (int i = 1; i <= m; ++i)
        s[i] = s[i - 1] + a[i];

    for (int len = 1; len < m; ++len) {

        for (int left = 0; left + len < m; ++left) {

            int right = left + len;

            for (int k = left; k < right; ++k) {
                f[left][right] = max(f[left][right], f[left][k] + f[k + 1][right] + s[right + 1] - s[left]);
                f2[left][right] = min(f2[left][right], f2[left][k] + f2[k + 1][right] + s[right + 1] - s[left]);
            }

        }

    }

    int maxv = 0;
    int minv = INF;

    for (int i = 0; i < n; ++i) {
        maxv = max(maxv, f[i][i + n - 1]);
        minv = min(minv, f2[i][i + n - 1]);
    }

    cout << minv << endl;
    cout << maxv << endl;
}