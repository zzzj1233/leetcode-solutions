package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-10 17:03
 */
public class Leet1586 {

    public static void main(String[] args) {
//        BSTIterator bstIterator = new BSTIterator(TreeNode.buildTree("[7,3,15,null,null,9,20]"));
//
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.prev());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.hasNext());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.hasNext());
//        System.out.println(bstIterator.hasPrev());
//        System.out.println(bstIterator.prev());
//        System.out.println(bstIterator.prev());
//        System.out.println(bstIterator.hasPrev());
//        System.out.println(bstIterator.prev());
//        System.out.println(bstIterator.prev());
//        System.out.println(bstIterator.hasPrev());

        BSTIterator bstIterator = new BSTIterator(TreeNode.buildTree("[5,null,10,8,12,7,9,11,19,null,null,null,null,null,null,13,20]"));

        String expression = "[\"hasNext\",\"next\",\"next\",\"hasNext\",\"next\",\"hasNext\",\"next\",\"hasPrev\",\"prev\",\"hasNext\",\"next\",\"hasPrev\",\"prev\",\"hasNext\",\"next\",\"hasNext\",\"next\",\"hasPrev\",\"prev\",\"hasNext\",\"next\"]";

        String arguments = "[[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[null]]";

        System.out.println(LeetUtils.executeExpression(expression, arguments, bstIterator));
    }

    private static class BSTIterator {

        private LinkedList<TreeNode> stack = new LinkedList<>();

        private Map<TreeNode, TreeNode> prevMap = new HashMap<>();

        private Set<Integer> exists = new HashSet<>();

        private TreeNode cur;

        private TreeNode leftist;

        public BSTIterator(TreeNode root) {
            stack.add(root);
            exists.add(root.val);
            while (stack.peekLast().left != null) {
                stack.addLast(stack.peekLast().left);
                exists.add(stack.peekLast().val);
            }
            leftist = stack.peekLast();
            cur = leftist;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public boolean hasPrev() {
            return cur != leftist;
        }

        public int next() {
            cur = stack.removeLast();

            if (cur.right != null && !exists.contains(cur.right.val)) {
                stack.add(cur.right);
                exists.add(cur.right.val);
                while (stack.peekLast().left != null) {
                    stack.addLast(stack.peekLast().left);
                    exists.add(stack.peekLast().val);
                }
            }

            prevMap.putIfAbsent(stack.peekLast(), cur);
            return cur.val;
        }

        public int prev() {
            TreeNode prev = prevMap.get(cur);
            TreeNode last = cur;

            TreeNode ret = prev;

            if (prev == last.left) {
                while (prev == last.left) {
                    stack.add(last);
                    prev = last;
                    last = prevMap.get(prev);
                }
            } else {
                stack.addLast(cur);
            }
            cur = ret;
            return ret.val;
        }

    }

}
