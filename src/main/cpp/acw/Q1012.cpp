#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 5010;

typedef pair<int, int> PII;

bool customComparator(const PII &p1, const PII &p2) {
    return p1.second < p2.second;
}

PII a[N];

int f[N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    for (int i = 0; i < n; ++i) {
        cin >> a[i].first >> a[i].second;
    }

    sort(a, a + n, customComparator);

    int ans = 0;

    for (int i = 0; i < n; ++i) {

        f[i] = 1;

        for (int j = 0; j < i; ++j) {

            if (a[j].first < a[i].first)
                f[i] = max(f[i], f[j] + 1);

        }

        ans = max(ans, f[i]);
    }

    cout << ans << endl;

}