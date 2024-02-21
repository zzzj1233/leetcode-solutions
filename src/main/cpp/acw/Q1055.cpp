#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 10010;

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    int f[2];

    f[0] = 0;
    f[1] = -1e8;

    for (int i = 1; i <= n; ++i) {

        int p;

        cin >> p;

        // 持有
        int a = max(f[1], f[0] - p);
        // 未持有
        int b = max(f[0], f[1] + p);

        f[1] = a;
        f[0] = b;
    }

    cout << f[0] << endl;
}