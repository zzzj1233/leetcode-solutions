#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1010;

int a[N];

int up[N];
int down[N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    for (int i = 1; i <= n; ++i) {
        cin >> a[i];
        if (a[i] == a[i - 1]) {
            n--;
            i--;
        }
    }

    int ans = 0;

    // 枚举i为分界点

    for (int i = 1; i <= n; ++i) {

        up[i] = 1;

        for (int j = 1; j < i; ++j) {
            if (a[j] < a[i])
                up[i] = max(up[i], up[j] + 1);
        }

    }

    for (int i = n; i > 0; --i) {

        down[i] = 1;

        for (int j = n; j > i; --j) {
            if (a[j] < a[i])
                down[i] = max(down[i], down[j] + 1);
        }

    }

    for (int i = 1; i <= n; ++i) {
        ans = max(ans, up[i] + down[i] - 1);
    }

    cout << ans << endl;
}