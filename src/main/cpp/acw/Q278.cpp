#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 110;

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    int a[n + 1];

    for (int i = 1; i <= n; ++i) {
        cin >> a[i];
    }

    int f[m + 1];

    memset(f, 0, sizeof f);

    f[0] = 1;

    for (int i = 1; i <= n; ++i) {

        for (int j = m; j >= a[i]; --j) {

            f[j] = max(f[j], f[j - a[i]] + 1);

        }

    }

    cout << f[m] << endl;

}