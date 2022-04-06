package com.zzzj.dpoint;

/**
 * @author zzzj
 * @create 2022-03-30 16:54
 */
public class Leet1265 {

    interface ImmutableListNode {
        void printValue();

        ImmutableListNode getNext();
    }

    public static void printLinkedListInReverse(ImmutableListNode head) {
        if (head == null){
            return;
        }
        dfs(head);
    }

    public static void dfs(ImmutableListNode head) {
        if (head.getNext() == null) {
            head.printValue();
        } else {
            dfs(head.getNext());
            head.printValue();
        }
    }


}
