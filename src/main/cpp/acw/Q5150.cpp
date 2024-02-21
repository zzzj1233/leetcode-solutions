#include "iostream"
#include "cstring"

using namespace std;

const int N = 110;

bool b[N];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n;

    memset(b, true, sizeof b);

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            int v;
            cin >> v;
            if (v == 1)
                b[i] = true;
            else if (v == 2)
                b[j] = true;
            else if (v == 3)
                b[i] = b[j] = true;
        }
    }

    int cnt = 0;

    for (int i = 1; i <= n; ++i)
        if (!b[i])
            cnt++;

    cout << cnt << endl;

    for (int i = 1; i <= n; ++i)
        if (!b[i])
            cout << i << " ";

}