cmake_minimum_required(VERSION 3.16)
project(cpp)

set(CMAKE_CXX_STANDARD 14)

include_directories(./acw)

# 查找指定目录下的所有源文件
file(GLOB SRC_FILES acw/*.cpp)

# 将所有源文件添加到可执行文件中
add_executable(cpp ${SRC_FILES})
