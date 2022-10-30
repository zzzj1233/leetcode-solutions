package com.zzzj.link;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReflectUtil;
import com.zzzj.leet.LeetUtils;

import java.lang.reflect.Method;

public class Leet2296 {

    public static void main(String[] args) {

        TextEditor instance = new TextEditor();

        instance.addText("leetcode");
        instance.deleteText(4);
        instance.addText("practice");
        System.out.println(instance.cursorRight(3));


        System.exit(0);

        Method[] methods = {
                ReflectUtil.getMethodByName(TextEditor.class, "addText"),
                ReflectUtil.getMethodByName(TextEditor.class, "deleteText"),
                ReflectUtil.getMethodByName(TextEditor.class, "cursorLeft"),
                ReflectUtil.getMethodByName(TextEditor.class, "cursorRight"),
        };


        TextEditor textEditor = new TextEditor();
        TextEditor.TextEditor2 textEditor2 = new TextEditor.TextEditor2();

        for (int i = 0; i < 1000; i++) {

            Method invokeMethod = methods[RandomUtil.randomInt(methods.length)];

            Object[] params;

            if (invokeMethod.getParameterTypes()[0] == String.class) {
                params = new Object[]{LeetUtils.randomString(LeetUtils.random.nextInt(10) + 1, false)};
            } else {
                params = new Object[]{LeetUtils.random.nextInt(5)};
            }

            Object result1 = ReflectUtil.invoke(textEditor, invokeMethod.getName(), params);
            Object result2 = ReflectUtil.invoke(textEditor2, invokeMethod.getName(), params);

            // System.out.println(invokeMethod.getName() + " --- " + Arrays.toString(params));

            if (result1 == null) {
                continue;
            }

            // System.out.println(result1 + " --- " + result2);

            if (result1.getClass().isPrimitive()) {
                if (result1 != result2) {
                    System.out.println("Error");
                    return;
                }
            } else {
                if (!result1.equals(result2)) {
                    System.out.println("Error");
                    return;
                }
            }
        }

        System.out.println("ok");
    }


    static class TextEditor {

        static class Node {
            char ch;
            Node prev;
            Node next;

            public Node(char ch) {
                this.ch = ch;
            }
        }

        Node head;
        Node tail;
        Node cursor;

        public TextEditor() {

        }

        public void addText(String text) {
            Node[] nodes = textToNode(text);

            if (head == null) {
                head = nodes[0];
                tail = nodes[1];
                cursor = new Node('#');
            } else {
                cursor.next = nodes[0];
                nodes[0].prev = cursor;
                if (cursor == tail) {
                    tail = nodes[1];
                }
                cursor = nodes[1];
            }
        }

        public Node[] textToNode(String text) {
            char c = text.charAt(0);
            Node head = new Node(c);

            Node cur = head;

            for (int i = 1; i < text.length(); i++) {
                Node node = new Node(text.charAt(i));
                node.prev = cur;
                cur.next = node;
                cur = node;
            }

            return new Node[]{head, cur};
        }

        public int deleteText(int k) {

            int index = 0;

            if (cursor == null) {
                return 0;
            }

            Node next = cursor.next;

            for (; index < k && cursor != null; index++) {
                if (cursor.prev != null) {
                    cursor.prev.next = null;
                }
                cursor = cursor.prev;
            }

            if (cursor == null) {
                head = next;
                cursor = head;
                head.prev = null;
            }

            if (next == null) {
                tail = cursor;
            }

            return index;
        }

        public String cursorLeft(int k) {
            // 向左移动k次
            for (int i = 0; i < k && cursor != null && cursor.prev != null; i++) {
                cursor = cursor.prev;
            }

            return getLeftStr();
        }

        public String getLeftStr() {
            Node node = cursor;

            StringBuilder builder = new StringBuilder(10);

            for (int i = 0; i < 10 && node != null && node.prev != null; i++) {
                builder.append(node.prev.ch);
                node = node.prev;
            }

            return builder.reverse().toString();
        }

        public String cursorRight(int k) {
            for (int i = 0; i < k && cursor != null && cursor.next != null; i++) {
                cursor = cursor.next;
            }

            return getLeftStr();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            Node node = head;

            while (node != null) {
                if (node == cursor) {
                    builder.append("[");
                    builder.append(node.ch);
                    builder.append("]");
                } else {
                    builder.append(node.ch);
                }
                node = node.next;
            }

            return builder.toString();
        }

        static class TextEditor2 {

            class Node {

                char c;

                Node next, pre;

                Node(char c) {
                    this.c = c;
                }
            }

            private Node head, cur;

            public TextEditor2() {
                head = new Node('#');
                cur = head;
            }

            private void deleteNode(Node node) {
                Node pre = node.pre;
                Node next = node.next;
                node.pre = node.next = null;
                pre.next = next;
                if (next != null) next.pre = pre;
            }

            private void addAfter(Node node, Node newNode) {
                newNode.pre = node;
                newNode.next = node.next;
                if (node.next != null) node.next.pre = newNode;
                node.next = newNode;
            }

            private String print() {
                StringBuilder sb = new StringBuilder();
                Node node = cur;
                for (int i = 0; i < 10 && node.c != '#'; i++) {
                    sb.append(node.c);
                    node = node.pre;
                }
                sb.reverse();
                return sb.toString();
            }

            public void addText(String text) {
                for (char c : text.toCharArray()) {
                    Node node = new Node(c);
                    addAfter(cur, node);
                    cur = cur.next;
                }
            }

            public int deleteText(int k) {
                int ans = 0;
                while (k-- > 0 && cur.c != '#') {
                    Node pre = cur.pre;
                    deleteNode(cur);
                    cur = pre;
                    ans++;
                }
                return ans;
            }

            public String cursorLeft(int k) {
                while (k-- > 0 && cur.pre != null) cur = cur.pre;
                return print();
            }

            public String cursorRight(int k) {
                while (k-- > 0 && cur.next != null) cur = cur.next;
                return print();
            }

        }

    }


}
