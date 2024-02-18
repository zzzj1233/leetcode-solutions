#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 510;

const int M = 6010;

int f[M];
int a[N][3];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= n; ++i)
        cin >> a[i][0] >> a[i][1] >> a[i][2];

    for (int i = 1; i <= n; ++i) {

        for (int k = m; k >= 0; --k) {

            for (int j = 1; j <= a[i][2]; ++j) {

                int p = a[i][0] * j;
                int v = a[i][1] * j;

                if (k - p < 0)
                    break;

                f[k] = max(f[k], f[k - p] + v);
            }

        }

    }

    cout << f[m] << endl;

}