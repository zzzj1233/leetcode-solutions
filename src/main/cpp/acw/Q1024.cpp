#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int V = 20010;

const int N = 32;

int a[N];

int f[V];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int v, n;

    cin >> v >> n;

    for (int i = 1; i <= n; ++i)
        cin >> a[i];

    for (int i = 1; i <= n; ++i) {

        for (int j = v; j >= a[i]; --j) {
            f[j] = max(f[j], f[j - a[i]] + a[i]);
        }

    }

    cout << v - f[v] << endl;

}