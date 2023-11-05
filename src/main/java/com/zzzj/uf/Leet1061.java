package com.zzzj.uf;

/**
 * @author zzzj
 * @create 2022-09-20 10:55
 */
public class Leet1061 {

    public static void main(String[] args) {
        System.out.println(smallestEquivalentString("parker", "morris", "parser"));
    }

    private static class UF {
        char[] parent = new char[128];


        public UF() {
            for (char i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public void connect(char x, char y) {
            char p1 = getParent(x);
            char p2 = getParent(y);

            if (p1 == p2) {
                return;
            }
            parent[p1] = p2;
        }

        public boolean isConnected(char x, char y) {
            return getParent(x) == getParent(y);
        }

        private char getParent(char i) {
            while (parent[i] != i) {
                i = parent[i];
            }
            return i;
        }

    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {

        int N = s1.length();

        UF uf = new UF();

        for (int i = 0; i < N; i++) {
            uf.connect(s1.charAt(i), s2.charAt(i));
        }

        StringBuilder builder = new StringBuilder(baseStr.length());

        OUTER:
        for (int i = 0; i < baseStr.length(); i++) {
            char c = baseStr.charAt(i);

            for (int j = 0; j < 26; j++) {
                char c1 = (char) (j + 'a');
                if (uf.isConnected(c, c1)) {
                    builder.append(c1);
                    continue OUTER;
                }
            }

            builder.append(c);
        }

        return builder.toString();
    }

}
