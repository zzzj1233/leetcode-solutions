
#include "iostream"
#include "cstring"
#include "algorithm"

const int N = 110;

int a[N];

const int INF = 1e8;

int f[N];

using namespace std;

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int k;

    cin >> k;

    while (k--) {

        int n;

        cin >> n;

        for (int i = 1; i <= n; ++i) {
            cin >> a[i];
        }

        a[0] = -INF;

        int ans = 0;

        for (int i = 1; i <= n; ++i) {

            f[i] = 1;

            for (int j = 1; j < i; ++j) {
                if (a[j] > a[i])
                    f[i] = max(f[i], f[j] + 1);
            }

            ans = max(ans, f[i]);
        }

        memset(f, 0, sizeof f);

        for (int i = n; i > 0; --i) {

            f[i] = 1;

            for (int j = n; j > i; --j) {

                if (a[j] > a[i])
                    f[i] = max(f[i], f[j] + 1);

            }

            ans = max(ans, f[i]);
        }

        cout << ans << endl;
    }

}