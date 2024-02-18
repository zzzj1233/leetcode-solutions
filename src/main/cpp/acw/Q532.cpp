#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 110;

const int M = 25010;

int a[N];

bool f[M];

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

        for (int i = 0; i < n; ++i) {
            cin >> a[i];
        }

        sort(a, a + n);

        int m = a[n - 1];

        int ans = 0;

        for (bool &i: f) {
            i = false;
        }

        f[0] = true;

        for (int i = 0; i < n; ++i) {

            if (!f[a[i]])
                ans++;

            for (int j = a[i]; j <= m; ++j) {
                f[j] |= f[j - a[i]];
            }

        }

        cout << ans << endl;
    }

}