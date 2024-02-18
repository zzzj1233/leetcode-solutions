#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1e5 + 10;

int w[N];

int f[N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int T;

    cin >> T;

    while (T--) {

        int n;

        cin >> n;

        for (int i = 1; i <= n; ++i) {
            cin >> w[i];
        }

        f[0] = 0;
        f[1] = w[1];

        for (int i = 2; i <= n; ++i) {
            f[i] = max(f[i - 2] + w[i], f[i - 1]);
        }

        cout << f[n] << endl;
    }

}