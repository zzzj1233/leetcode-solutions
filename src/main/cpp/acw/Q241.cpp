//
// Created by zzzj on 2024/2/7.
//

#include <iostream>
#include <sstream>
#include "list"

using namespace std;

typedef long long LL;

const int N = 200010;

int td[N + 1];

int lowbit(int x) {
    return x & -x;
}

void add(int x, int v) {
    while (x <= N) {
        td[x] += v;
        x += lowbit(x);
    }

}

int query(int x) {
    int sum = 0;
    while (x > 0) {
        sum += td[x];
        x -= lowbit(x);
    }
    return sum;
}

#include "zzzj.h"

int main() {

#ifdef CPP_ZZZJ_H
    Zzzj::reopen(__FILE__);
#endif

    using namespace std;

    int n;

    std::cin >> n;

    int nums[n];

    for (int i = 0; i < n; ++i) {
        scanf("%d", &nums[i]);
    }

    // high - low - high
    LL hlh = 0;

    // low - high - low
    LL lhl = 0;

    int leftLT[N];
    int leftGT[N];

    for (int i = 0; i < n; ++i) {

        leftLT[i] = query(nums[i] - 1);

        leftGT[i] = query(N) - query(nums[i]);

        add(nums[i], 1);
    }

    for (int i = 0; i < n; ++i) {
        hlh += leftGT[i] * ((LL) (query(N) - query(nums[i]) - leftGT[i]));
        lhl += leftLT[i] * ((LL) (query(nums[i] - 1) - leftLT[i]));
    }

    std::cout << hlh << " " << lhl << std::endl;

    return 0;
}