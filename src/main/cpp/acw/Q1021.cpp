#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 16;

const int M = 3010;

int a[N];

long long f[M];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= n; ++i) {
        cin >> a[i];
    }

    f[0] = 1;

    for (int i = 1; i <= n; ++i) {

        for (int j = a[i]; j <= m; ++j) {
            f[j] += f[j - a[i]];
        }

    }

    cout << f[m] << endl;
}