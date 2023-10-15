package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class Leet2430 {

    public static void main(String[] args) {


        System.out.println(deleteString("ilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkeilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkebqumabhsdwjesovwhqjvphhigtqubeazichenrkipozlnrjpmxojqmonwdwkeyfptfkwcwzyqglgrqoaiufbhcplnrhvljsjqvvcryxfiifzkvdwtrveehprjrycsiljusynfxtgvzhzczqhbmrfutryzguvpmsrsoudxbinfrdwadboontbjjbzbyhextdynmdwhodebxwgcdrvwtttvhuxzqweahbmfecmoawmwunzlltklrcchkvbpkbpfrupkwmbfmbmldefzichboxigbgusfyirfaypemrjqjaprtblyjdyyycqymoxuuwyvbyqeihlysakceywfjqczosimkpkiqyautfymxsgkiagshmdxgsudaijuxcrkbsrcedirltpjhrdozrghtvinqofwomywcharaabfprotzlmfhoxbzbamqypjwrlivyrytehwsmdxpdrlnxdiqagtfmuqvckqabvlmedptfaerixwsnzzzucildclmximjhpkcvvwadzwsquflhznwoeyhtiydbsivhimkynqydfkdjonicpywvtgtmwisetxsyjpuffvkhmdptagagrivjnsabgqoltdypvirauiwgjlupliioletrpthrslwyofyomldadbtqhoczvwczmfcfazncwrsvkotsognwpdcuounqhuhtstfaenrzwmhatnwqzdtqoscuxgxnsavyhplcgqczfamyyzyukfswpnjbyexesuhgmjhzfdrdmcjxcieiejwwenwnwbfxesanttgwarcykrclbzwmyj"));
        System.out.println(right("ilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkeilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkebqumabhsdwjesovwhqjvphhigtqubeazichenrkipozlnrjpmxojqmonwdwkeyfptfkwcwzyqglgrqoaiufbhcplnrhvljsjqvvcryxfiifzkvdwtrveehprjrycsiljusynfxtgvzhzczqhbmrfutryzguvpmsrsoudxbinfrdwadboontbjjbzbyhextdynmdwhodebxwgcdrvwtttvhuxzqweahbmfecmoawmwunzlltklrcchkvbpkbpfrupkwmbfmbmldefzichboxigbgusfyirfaypemrjqjaprtblyjdyyycqymoxuuwyvbyqeihlysakceywfjqczosimkpkiqyautfymxsgkiagshmdxgsudaijuxcrkbsrcedirltpjhrdozrghtvinqofwomywcharaabfprotzlmfhoxbzbamqypjwrlivyrytehwsmdxpdrlnxdiqagtfmuqvckqabvlmedptfaerixwsnzzzucildclmximjhpkcvvwadzwsquflhznwoeyhtiydbsivhimkynqydfkdjonicpywvtgtmwisetxsyjpuffvkhmdptagagrivjnsabgqoltdypvirauiwgjlupliioletrpthrslwyofyomldadbtqhoczvwczmfcfazncwrsvkotsognwpdcuounqhuhtstfaenrzwmhatnwqzdtqoscuxgxnsavyhplcgqczfamyyzyukfswpnjbyexesuhgmjhzfdrdmcjxcieiejwwenwnwbfxesanttgwarcykrclbzwmyj"));

        System.exit(0);

        for (int i = 0; i < 1000000; i++) {

            int M = 10;

            String str = LeetUtils.randomString(M, false);

            int r1 = deleteString(str);

            int r2 = right(str);

            if (r1 != r2) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println("deleteString(str) = " + r1);
                System.out.println("right(str) = " + r2);
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int deleteString(String s) {

        int N = s.length();

        long[][] hash = new long[N][N];

        long MOD = Long.MAX_VALUE;

        final int R = 26;

        for (int i = 0; i < N; i++) {

            long h = 0;

            for (int j = i; j < N; j++) {
                h = (h * 1331) % MOD + (s.charAt(j) - 'a');
                hash[i][j] = h;
            }

        }

        LinkedList[] indexes = new LinkedList[R];

        for (int i = 0; i < R; i++) {
            indexes[i] = new LinkedList();
        }

        int[] dp = new int[N];
        dp[0] = 1;
        indexes[s.charAt(0) - 'a'].add(0);

        boolean[] completed = new boolean[N];
        completed[0] = true;

        int ans = 1;

        for (int i = 1; i < N; i++) {

            dp[i] = 1;

            int index = s.charAt(i) - 'a';

            LinkedList<Integer> indexList = indexes[index];

            OUTER:
            for (Integer preIndex : indexList) {
                // [ preIndex - (index - 1) ]
                int length = i - preIndex - 1;
                if (i + length >= N)
                    break;
                if (!completed[preIndex])
                    continue;

                // 比较hash
                if (hash[preIndex][i - 1] != hash[i][i + length]) {
                    continue;
                }

                // 比较成功
                dp[i] = Math.max(dp[i], dp[preIndex] + 1);
                completed[i] = true;
                ans = Math.max(ans, dp[i]);
            }

            indexList.addFirst(i);
        }

        return ans;
    }

    public static int right(String s) {

        int N = s.length();

        long[] preSum = new long[N + 1];

        final int R = 26;

        LinkedList[] indexes = new LinkedList[R];

        for (int i = 0; i < R; i++) {
            indexes[i] = new LinkedList();
        }

        int[] dp = new int[N];
        dp[0] = 1;
        indexes[s.charAt(0) - 'a'].add(0);

        boolean[] completed = new boolean[N];
        completed[0] = true;

        int ans = 1;

        for (int i = 1; i < N; i++) {

            dp[i] = 1;

            int index = s.charAt(i) - 'a';

            LinkedList<Integer> indexList = indexes[index];

            OUTER:
            for (Integer preIndex : indexList) {
                // [ preIndex - (index - 1) ]
                int length = i - preIndex - 1;
                if (i + length >= N)
                    break;
                if (!completed[preIndex])
                    continue;

//                // 比较字符串
                for (int j = 1; j <= length; j++) {
                    if (s.charAt(preIndex + j) != s.charAt(i + j)) {
                        continue OUTER;
                    }
                }

                // 比较成功
                dp[i] = Math.max(dp[i], dp[preIndex] + 1);
                completed[i] = true;
                ans = Math.max(ans, dp[i]);
            }

            indexList.addFirst(i);
        }

        return ans;
    }

}
