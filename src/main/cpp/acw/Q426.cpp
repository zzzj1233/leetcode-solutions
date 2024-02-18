#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 30010;

const int M = 30;

int a[M][2];

int f[N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= m; ++i) {
        cin >> a[i][0] >> a[i][1];
        a[i][1] *= a[i][0];
    }

    for (int i = 1; i <= m; ++i) {

        for (int j = n; j >= a[i][0]; --j) {

            f[j] = max(f[j], f[j - a[i][0]] + a[i][1]);

        }

    }

    cout << f[n] << endl;

}