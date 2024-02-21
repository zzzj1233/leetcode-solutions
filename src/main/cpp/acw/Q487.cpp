#include "iostream"
#include "cstring"
#include "algorithm"
#include "unordered_map"
#include "vector"

using namespace std;

const int V = 32010;

const int N = 65;

int w[N];

int q[N];

int f[V];

int fa[N];

unordered_map<int, vector<int>> map;

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    int v, n;

    cin >> v >> n;

    for (int i = 1; i <= n; ++i) {
        cin >> w[i] >> q[i] >> fa[i];
        q[i] *= w[i];
        if (fa[i])
            map[fa[i]].push_back(i);
    }

    for (int i = 1; i <= n; ++i) {

        if (fa[i])
            continue;

        if (map.count(i)) {

            vector<int> children = map[i];

            int size = children.size();

            int limit = 1 << size;

            for (int j = v; j >= 0; --j) {

                for (int s = 0; s < limit; ++s) {

                    int ws = w[i];
                    int qs = q[i];

                    for (int k = 0; k < size; ++k) {

                        if (s & (1 << k)) {
                            ws += w[children[k]];
                            qs += q[children[k]];
                        }

                    }

                    if (j >= ws)
                        f[j] = max(f[j], f[j - ws] + qs);
                }

            }

        } else {
            for (int j = v; j >= w[i]; --j) {
                f[j] = max(f[j], f[j - w[i]] + q[i]);
            }
        }

    }


    cout << f[v] << endl;
}