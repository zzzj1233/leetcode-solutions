package com.zzzj.link;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-05-17 18:13
 */
public class Leet2326 {

    public static void main(String[] args) {

//        System.out.println(Arrays.deepToString(spiralMatrix(3, 5, ListNode.build(3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0))));
        // [[8],[24],[5],[21],[10],[11],[11],[12],[6],[17]]
        System.out.println(Arrays.deepToString(spiralMatrix(10, 1, ListNode.build(8, 24, 5, 21, 10, 11, 11, 12, 6, 17))));

        //
    }


    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        /*
        一个模拟的万能转向trick(参考灵神)
         */
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(res[i], -1);
        }
        ListNode cur = head;
        // 上右下左四个进给方向
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // idx为进给方向,x与y是遍历指针
        int idx = 0, x = 0, y = -1; // 为了进给第一步向右走了之后才赋值,初始化(x,y)=(0.-1)
        // 链表还没走完就继续循环
        while (cur != null) {
            int newX = x + dirs[idx][0], newY = y + dirs[idx][1];
            // 越界或者碰到已经覆盖过的->转向
            if (newX < 0 || newX >= m || newY < 0 || newY >= n || res[newX][newY] != -1) idx = (idx + 1) % 4;
            x += dirs[idx][0];
            y += dirs[idx][1];
            res[x][y] = cur.val;
            cur = cur.next;
        }
        return res;
    }


}
