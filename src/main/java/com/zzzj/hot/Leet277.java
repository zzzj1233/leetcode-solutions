package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-05-05 17:13
 */
public class Leet277 {

    boolean knows(int a, int b) {
        return false;
    }

    public int findCelebrity(int n) {
        int star = 0;

        for (int i = 1; i < n; i++) {
            if (knows(star, i)) {
                star = i;
            }
        }

        // [[1,0],[0,1]]
        for (int i = 0; i < n; i++) {
            if ((knows(star, i) && i != star) || (!knows(i, star))) {
                return -1;
            }
        }

        return star;
    }

}
