//
// Created by zzzj on 2024/2/7.
//

#ifndef CPP_ZZZJ_H
#define CPP_ZZZJ_H

#include <string>

using namespace std;

namespace Zzzj {

    void reopen(const char *file) {

        string fileName = file;

        size_t dotPos = fileName.find_last_of('.');

        if (dotPos != std::string::npos) {
            fileName.replace(dotPos, fileName.length() - dotPos, ".txt");
            freopen(fileName.c_str(), "r", stdin);
        }

    }

}

#endif //CPP_ZZZJ_H
