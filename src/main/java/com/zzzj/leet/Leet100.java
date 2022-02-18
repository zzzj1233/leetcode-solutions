package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-19 11:38
 */
public class Leet100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) {
            return false;
        }

        if (q == null && p != null) {
            return false;
        }

        if (p != null && p.val != q.val) {
            return false;
        }

        if (p != null){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        return true;
    }

}
