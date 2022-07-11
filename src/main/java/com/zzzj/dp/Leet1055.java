package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-06 17:14
 */
public class Leet1055 {

    // 输入：source = "xyz", target = "xzyxz"
    // 输出：3
    // 解释：目标字符串可以按如下方式构建： "xz" + "y" + "xz"。
    public static int shortestWay(String source, String target) {
        int i = 0;

        int ans = 0;

        while (i < target.length()) {
            int j = 0;
            int oldI = i;
            while (j < source.length() && i < target.length()) {
                if (source.charAt(j) == target.charAt(i)) {
                    j++;
                    i++;
                } else {
                    j++;
                }
            }
            if (i == oldI) {
                return -1;
            }
            ans++;
        }

        return ans;
    }

}
