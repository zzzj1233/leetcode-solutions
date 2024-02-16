#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 3010;

int a[N];
int b[N];

int f[N][N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    for (int i = 1; i <= n; ++i) {
        cin >> a[i];
    }

    for (int i = 1; i <= n; ++i) {
        cin >> b[i];
    }

    for (int i = 1; i <= n; ++i) {

        int maxv = 0;

        for (int j = 1; j <= n; ++j) {

            f[i][j] = f[i - 1][j];

            if (b[j] < a[i])
                maxv = max(maxv, f[i][j]);

            if (a[i] == b[j]) {
                f[i][j] = max(f[i][j], maxv + 1);
            }

        }

    }

    int ans = 0;

    for (int i = 1; i <= n; ++i) {
        ans = max(ans, f[n][i]);
    }

    cout << ans << endl;
}