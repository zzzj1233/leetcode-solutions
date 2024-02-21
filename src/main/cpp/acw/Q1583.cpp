#include "iostream"
#include "cstring"
#include "algorithm"

using namespace std;

const int N = 1e5 + 10;

const int MOD = 1000000007;

int f[4];

// #include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    string str;

    cin >> str;

    f[0] = 1;

    for (int i = 0; i < str.length(); ++i) {

        char c = str[i];

        if (c == 'P') {
            f[1] = (f[1] + f[0]) % MOD;
        } else if (c == 'A') {
            f[2] = (f[2] + f[1]) % MOD;
        } else {
            f[3] = (f[3] + f[2]) % MOD;
        }
    }

    cout << f[3] << endl;
}