#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int INF = 1e8;

int f1[2];
int f2[2];

// #include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    int p;

    cin >> p;

    // 持有
    f1[0] = -p;
    // 未持有
    f1[1] = 0;

    f2[0] = -INF;
    f2[1] = -INF;

    for (int i = 1; i < n; ++i) {

        cin >> p;

        f1[0] = max(f1[0], -p);
        f1[1] = max(f1[1], f1[0] + p);

        f2[0] = max(f2[0], f1[1] - p);
        f2[1] = max(f2[1], f2[0] + p);
    }

    cout << f2[1] << endl;
}