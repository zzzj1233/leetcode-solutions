#include "iostream"
#include "vector"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 110;
const int V = 110;

vector<int> g[N];

int vi[N];
int wi[N];
int v;
int f[N][V];

#include "zzzj.h"

void dfs(int node) {

    for (auto child: g[node]) {
        dfs(child);
    }

    for (auto child: g[node]) {

        for (int k = v; k >= 0; --k) {

            for (int x = 0; x <= k; ++x) {

                f[node][k] = max(f[node][k], f[child][x] + f[node][k - x]);

            }

        }

    }

    for (int i = v; i >= vi[node]; --i) {
        f[node][i] = f[node][i - vi[node]] + wi[node];
    }

    for (int i = 0; i < vi[node]; ++i) {
        f[node][i] = 0;
    }

}

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int n;

    cin >> n >> v;

    // cout << "v = " << v << endl;

    int root = -1;

    memset(f, 0, sizeof f);

    for (int i = 1; i <= n; ++i) {

        int p;

        cin >> vi[i] >> wi[i] >> p;

        if (p == -1) {
            root = i;
        } else {
            g[p].push_back(i);
        }

    }

    dfs(root);

    cout << f[root][v] << endl;
}
