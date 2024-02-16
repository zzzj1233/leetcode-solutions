#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1010;

int a[N];

int f[N];

int s[N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n = 1;

    while (cin >> a[n]) n++;

    int longest = 0;
    int cnt = 0;

    for (int i = 1; i < n; ++i) {

        f[i] = 1;

        for (int j = 1; j < i; ++j)
            if (a[j] > a[i])
                f[i] = max(f[i], f[j] + 1);

        longest = max(longest, f[i]);
    }

    for (int i = 1; i < n; ++i) {

        int minIndex = -1;

        for (int j = 0; j < cnt; ++j) {

            if (s[j] > a[i]) {
                if (minIndex == -1 || a[j] < a[minIndex])
                    minIndex = j;
            }

        }

        if (minIndex == -1) {
            s[cnt] = a[i];
            cnt++;
        } else {
            s[minIndex] = a[i];
        }

    }

    cout << longest << endl;

    cout << cnt << endl;
}