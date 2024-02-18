#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1010;

const int V = 1010;

int a[N][3];

int f[V];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n, m;

    cin >> n >> m;

    for (int i = 1; i <= n; ++i) {
        int v, w, s;
        cin >> v >> w >> s;

        if (s == -1) {

            for (int j = m; j >= v; --j) f[j] = max(f[j], f[j - v] + w);

        } else if (s == 0) {

            for (int j = v; j <= m; ++j) f[j] = max(f[j], f[j - v] + w);

        } else {

            for (int j = m; j >= 0; --j) {

                for (int k = 1; k <= s; ++k) {

                    int v1 = v * k;
                    int p1 = w * k;

                    if (j < v1)
                        break;

                    f[j] = max(f[j], f[j - v1] + p1);
                }

            }

        }

    }

    cout << f[m] << endl;


}