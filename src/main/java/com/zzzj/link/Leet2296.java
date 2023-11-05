package com.zzzj.link;

public class Leet2296 {

    public static void main(String[] args) {

        TextEditor instance = new TextEditor();

        instance.addText("leetcode");

        instance.deleteText(4);

        instance.addText("practice");

        System.out.println(instance.cursorRight(3));
    }

    static class TextEditor {

        private final StringBuilder builder;

        private int cursor;

        public TextEditor() {
            this.builder = new StringBuilder();
        }

        public void addText(String text) {
            if (cursor == builder.length())
                builder.append(text);
            else
                builder.insert(cursor, text);
            cursor += text.length();
        }

        public int deleteText(int k) {
            // 删除左边的文本
            int start = Math.max(0, cursor - k);

            builder.delete(start, cursor);

            int deleted = cursor - start;

            cursor = Math.max(0, start);

            return deleted;
        }

        public String cursorLeft(int k) {
            // 光标左边 min(10, len) 个字符
            cursor = Math.max(0, cursor - k);
            return leftStr();
        }

        public String cursorRight(int k) {
            // 光标左边 min (10, len)个字符
            cursor = Math.min(builder.length(), cursor + k);
            return leftStr();
        }

        private String leftStr() {

            int start = Math.max(0, cursor - 10);

            int end = cursor;

            int len = end - start;

            char[] chars = new char[len];

            for (int i = 0; i < chars.length; i++)
                chars[i] = builder.charAt(i + start);

            return new String(chars);
        }

    }


}
