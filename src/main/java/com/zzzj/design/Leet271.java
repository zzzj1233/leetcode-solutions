package com.zzzj.design;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-01-06 18:03
 */
public class Leet271 {


    public static void main(String[] args) {
        Codec codec = new Codec();

        String encode = codec.encode(Arrays.asList("zzzj", "dl", "cjj", "java", "C++"));

        System.out.println(codec.decode(encode));
    }

    private static class Codec {

        public String encode(List<String> strs) {
            int size = strs.size();

            StringBuilder builder = new StringBuilder();

            appendInt(builder, size);

            for (String str : strs) {
                appendInt(builder, str.length());
            }

            strs.forEach(builder::append);

            return builder.toString();
        }

        public List<String> decode(String s) {
            int wordsCount = decodeInt(s, 0);

            List<Integer> list = decodePreWordsLen(s, wordsCount);

            int startIndex = (wordsCount << 2) + 4;

            List<String> ans = new ArrayList<>(wordsCount);

            for (Integer length : list) {
                ans.add(readString(s, length, startIndex));
                startIndex += length;
            }

            return ans;
        }

        public List<Integer> decodePreWordsLen(String s, int wordsCount) {
            int index = 4;

            List<Integer> length = new ArrayList<>(wordsCount);

            for (int i = 0; i < wordsCount; i++) {
                length.add(decodeInt(s, index));
                index += 4;
            }

            return length;
        }

        public String readString(String s, int length, int index) {
            return s.substring(index, index + length);
        }

        public int decodeInt(String s, int index) {
            char c0 = s.charAt(index);
            char c1 = s.charAt(index + 1);
            char c2 = s.charAt(index + 2);
            char c3 = s.charAt(index + 3);

            return c0 << 24 | c1 << 16 | c2 << 8 | c3;
        }

        public void appendInt(StringBuilder builder, int value) {
            builder.append(((char) (value >> 24 & 0xff)))
                    .append(((char) (value >> 16 & 0xff)))
                    .append(((char) (value >> 8 & 0xff)))
                    .append(((char) (value & 0xff)));
        }

    }

}
