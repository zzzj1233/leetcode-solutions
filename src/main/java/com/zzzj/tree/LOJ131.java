package com.zzzj.tree;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-08-08 17:11
 */
public class LOJ131 {

    private static class BIT {

        private final int[] nums;

        private int N;

        public final int[] tree;

        public BIT(int[] nums) {

            this.nums = nums;

            this.N = nums.length;

            this.tree = new int[N + 1];

        }

        public void rangeAdd(int left, int right, int value) {
            int index = left;

            while (index <= N) {
                tree[index] += value;
                index += lowbit(index);
            }

        }

        public int get(int index) {

            int value = nums[index - 1];

            while (index > 0) {
                value += tree[index];
                index -= lowbit(index);
            }

            return value;
        }

        public static int lowbit(int x) {
            return x & (-x);
        }

    }

    public static void main(String[] args) throws Exception {

        InputStream inputStream;

        if ("zzzj".equals(System.getenv("USERNAME"))) {
            inputStream = LOJ131.class.getClassLoader().getResourceAsStream(LOJ131.class.getSimpleName() + ".txt");
        } else {
            inputStream = System.in;
        }

        Scanner scanner = new Scanner(inputStream);

        BIT bit = null;

        Integer n = null, q = null;

        int ith = 0;

        int[] nums = new int[0];

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] commandsStr = str.split(" ");
            Integer[] commands = new Integer[commandsStr.length];

            for (int i = 0; i < commandsStr.length; i++) {
                commands[i] = Integer.parseInt(commandsStr[i]);
            }

            if (ith == 0) {
                n = commands[0];
                nums = new int[n];
                q = commands[1];
            } else if (ith == 1) {
                for (int i = 0; i < n; i++) {
                    nums[i] = commands[i];
                }

                bit = new BIT(nums);
            } else {
                assert bit != null;

                if (commands[0] == 1)
                    bit.rangeAdd(commands[1], commands[2], commands[3]);
                else
                    System.out.println(bit.get(commands[1]));

                q--;
            }

            ith++;

            if (q != null && q == 0)
                break;
        }

        scanner.close();
    }

}
