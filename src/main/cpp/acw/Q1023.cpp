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

    int a[] = {10, 20, 50, 100};

    int n;

    cin >> n;

    int f[n + 1];

    memset(f, 0, sizeof f);

    f[0] = 1;

    for (int i = 0; i < 4; ++i) {

        for (int j = a[i]; j <= n; ++j) {
            f[j] += f[j - a[i]];
        }

    }

    cout << f[n] << endl;
}