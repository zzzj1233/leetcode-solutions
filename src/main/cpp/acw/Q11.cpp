#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1010;
const int V = 1010;

int a[N][2];
int f[V];
int p[V];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, v;

    cin >> n >> v;

    int mod = 1e9 + 7;

    for (int i = 1; i <= n; ++i) {
        cin >> a[i][0] >> a[i][1];
    }

    p[0] = 1;

    for (int i = 1; i <= n; ++i) {

        for (int j = v; j >= a[i][0]; --j) {
            if (f[j] == f[j - a[i][0]] + a[i][1])
                p[j] = (p[j] + p[j - a[i][0]]) % mod;
            else if (f[j] < f[j - a[i][0]] + a[i][1]) {
                p[j] = p[j - a[i][0]];
                f[j] = f[j - a[i][0]] + a[i][1];
            }
        }

    }

    int maxv = 0;

    for (int i = 0; i <= v; ++i) {
        maxv = max(maxv, f[i]);
    }

    int ans = 0;

    for (int i = 0; i <= v; ++i) {
        if (f[i] == maxv)
            ans = (ans + p[i]) % mod;
    }

    cout << ans << endl;

}