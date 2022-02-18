package com.zzzj.dpoint;

/**
 * @author zzzj
 * @create 2021-12-21 18:47
 */
public class Leet165 {

    public static void main(String[] args) {
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
    }

    public static int compareVersion(String version1, String version2) {
        int i = 0;
        int j = 0;

        while (i < version1.length() || j < version2.length()) {
            char c1 = i < version1.length() ? version1.charAt(i++) : '0';
            char c2 = j < version2.length() ? version2.charAt(j++) : '0';

            while (i < version1.length() && version1.charAt(i) == '0') {
                i++;
            }

            while (j < version2.length() && version2.charAt(j) == '0') {
                j++;
            }

            if (c1 == '.' || c2 == '.') {
                continue;
            }

            int compare = Character.compare(c1, c2);

            if (compare != 0) {
                return compare > 0 ? 1 : -1;
            }

        }

        return 0;
    }

}
