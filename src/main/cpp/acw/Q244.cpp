
#include "iostream"
#include "stdio.h"

using namespace std;

#include "zzzj.h"
#include "cassert"

const int N = 10010;

int td[N];

int lowbit(int x) {
    return x & -x;
}

void update(int x, int v) {
    for (int i = x; i < N; i += lowbit(i)) td[i] += v;
}

int query(int x) {
    int res = 0;
    for (int i = x; i > 0; i -= lowbit(i)) {
        res += td[i];
    }
    return res;
}

int check(int expect) {

    int left = 0;
    int right = N - 1;

    int res = -1;

    while (left <= right) {

        int mid = left + ((right - left) >> 1);

        int r = query(mid) - 1;

        if (r == expect) {
            res = mid;
            right = mid - 1;
        } else if (r > expect) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    assert(res != -1);

    update(res, -1);

    return res;
}

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    int w[n + 1];

    for (int i = 2; i <= n; ++i) {
        cin >> w[i];
    }

    for (int i = 1; i <= n; ++i) {
        update(i, 1);
    }

    int ans[n + 1];

    for (int i = n; i >= 1; --i) {
        ans[i] = check(w[i]);
    }

    for (int i = 1; i <= n; ++i) {
        cout << ans[i] << endl;
    }

}