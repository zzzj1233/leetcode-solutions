package com.zzzj.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-08 11:48
 */
public class Leet1628 {

    public static void main(String[] args) {
        final TreeBuilder treeBuilder = new TreeBuilder();

        Node node = treeBuilder.buildTree(new String[]{"3", "4", "+", "2", "*", "7", "/"});

        System.out.println(node.evaluate());
    }

    static abstract class Node {
        public abstract int evaluate();
    }

    static class NodeImpl extends Node {

        NodeImpl left;

        NodeImpl right;

        String term;

        Integer value;

        NodeImpl(String term) {
            this.term = term;
        }

        public NodeImpl(Integer value) {
            this.value = value;
        }

        @Override
        public int evaluate() {
            if (this.value != null) {
                return this.value;
            } else {
                if (this.term.equals("+")) {
                    return this.right.evaluate() + this.left.evaluate();
                } else if (this.term.equals("-")) {
                    return this.right.evaluate() - this.left.evaluate();
                } else if (this.term.equals("*")) {
                    return this.right.evaluate() * this.left.evaluate();
                } else {
                    return this.right.evaluate() / this.left.evaluate();
                }
            }
        }

        @Override
        public String toString() {
            if (value == null) {
                return term;
            }
            return value.toString();
        }
    }

    static class TreeBuilder {

        Node buildTree(String[] postfix) {
            LinkedList<String> list = new LinkedList<>();

            list.addAll(Arrays.asList(postfix));

            NodeImpl node = buildTree(list);

            return node;
        }

        final List<String> OPERATE = Arrays.asList("+", "-", "*", "/");

        NodeImpl buildTree(LinkedList<String> list) {
            if (list.isEmpty()) {
                return null;
            }
            String last = list.removeLast();
            if (OPERATE.contains(last)) {
                NodeImpl node = new NodeImpl(last);
                node.left = buildTree(list);
                node.right = buildTree(list);
                return node;
            } else {
                return new NodeImpl(Integer.parseInt(last));
            }
        }


    }

}
