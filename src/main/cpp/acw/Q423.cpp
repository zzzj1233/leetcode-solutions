#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 110;

const int M = 1010;

int w[N];
int v[N];

int f[M];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int m, n;

    cin >> m >> n;

    for (int i = 1; i <= n; ++i) {
        cin >> w[i] >> v[i];
    }

    for (int i = 1; i <= n; ++i) {

        for (int j = m; j >= w[i]; --j) {
            f[j] = max(f[j], f[j - w[i]] + v[i]);
        }

    }

    cout << f[m] << endl;

}