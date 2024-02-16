

#include "iostream"
#include "cstring"
#include "algorithm"


#include "zzzj.h"

int w[110][110];

int f[2][110];

using namespace std;

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int T;

    cin >> T;

    while (T--) {

        int n, m;

        cin >> n >> m;

        memset(f, 0, sizeof f);

        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= m; ++j)
                cin >> w[i][j];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                f[i % 2][j] = max(f[(i - 1) % 2][j], f[i % 2][j - 1]) + w[i][j];
            }
        }

        cout << f[n % 2][m] << endl;

    }

}