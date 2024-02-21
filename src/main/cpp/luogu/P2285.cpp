#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1010;

const int M = 1e4 + 10;

int a[N][N];

int f[M];

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int dirs[4][2] = {
            {1,  0},
            {-1, 0},
            {0,  1},
            {0,  -1}
    };

    int n, m;

    cin >> n >> m;

    for (int i = 0; i < m; ++i) {
        int time, x, y;
        cin >> time >> x >> y;
        a[x][y] = time;

    }


    int ans = 0;

    cout << ans << endl;
}