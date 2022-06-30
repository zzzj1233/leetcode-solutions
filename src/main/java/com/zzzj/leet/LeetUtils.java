package com.zzzj.leet;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2021-10-25 10:08
 */
public class LeetUtils {

    public static Random random = new Random();

    public static int[] newArray(int n) {
        return newArray(n, 10);
    }

    public static int[] newArray(int n, int m) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = random.nextInt(m) + 1;
        }
        return result;
    }

    public static String newString(int n, int max) {
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            result[i] = (char) (random.nextInt(max) + 97);
        }
        return String.valueOf(result);
    }

    public static String newString(int n) {
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            result[i] = (char) (random.nextInt(26) + 97);
        }
        return String.valueOf(result);
    }


    public static String randomTreeStr(int n) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && random.nextInt(10) == 9) {
                list.add("null");
            } else {
                list.add(String.valueOf(random.nextInt(100)));
            }
        }

        String str = String.join(",", list);

        return "[" + str + "]";
    }

    public static TreeNode randomTree(int n) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && random.nextInt(10) == 9) {
                list.add("null");
            } else {
                list.add(String.valueOf(random.nextInt(100)));
            }
        }

        String str = String.join(",", list);

        return TreeNode.buildTree("[" + str + "]");
    }

    public static String randomFullTree() {
        ArrayList<String> list = new ArrayList<>();

        int N = random.nextInt(100);

        for (int i = 0; i < N; i++) {
            list.add(String.valueOf(random.nextInt(100)));
        }

        String str = String.join(",", list);

        return "[" + str + "]";
    }


    public static String randomNumberString(int length) {
        String str = "0123456789";
        return randomString0(str, length);
    }

    public static String randomUpperString(int length) {
        return randomString0("ABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    // 是否包含大写字母
    public static String randomString(int length, boolean containsUpper) {
        String str;

        if (containsUpper) {
            str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        } else {
            str = "abcdefghijklmnopqrstuvwxyz";
        }


        return randomString0(str, length);
    }

    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return randomString0(str, length);
    }

    public static String randomString0(String candidate, int length) {
        Random random = new Random();

        StringBuffer sb = new StringBuffer(length);

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(candidate.length());

            sb.append(candidate.charAt(number));
        }

        return sb.toString();
    }

    public static String randomString(int length, String candidate) {
        Random random = new Random();

        StringBuffer sb = new StringBuffer(length);

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(candidate.length());

            sb.append(candidate.charAt(number));
        }

        return sb.toString();
    }


    public static int[] randomBinaryArr(int n) {
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            if (random.nextInt() % 2 == 0) {
                result[i] = 1;
            }
        }

        return result;
    }

    public static int[][] random2DInts(int M, int N, int rangeL, int rangeR) {
        int[][] result = new int[M][N];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = random.nextInt(rangeR) + rangeL;
            }
        }

        return result;
    }

    public static char[][] random2DChars(int M, int N, String candidate) {
        char[][] result = new char[M][N];

        for (int i = 0; i < result.length; i++) {

            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = candidate.charAt(random.nextInt(candidate.length()));
            }

        }

        return result;
    }

    public static char[][] random2DChars(int M, int N, boolean containsUpper) {
        return random2DChars(M, N, containsUpper ? "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" : "abcdefghijklmnopqrstuvwxyz");
    }


    public static char[] convertChars1(String source) {
        return convertChars("[" + source + "]")[0];
    }


    // source e.g : [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
    public static char[][] convertChars(String source) {

        source = source.substring(1, source.length() - 1);

        String[] split = source.split("],?");

        char[][] result = new char[split.length][];

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].substring(1);

            String[] chars = oneD.split(",");

            // 去除双引号
            result[i] = new char[chars.length];

            for (int j = 0; j < chars.length; j++) {
                String singleChar = chars[j].trim().replaceAll("\"", "");
                result[i][j] = singleChar.charAt(0);
            }

        }

        return result;
    }

    public static List<List<Integer>> convertLists(String source) {
        source = source.substring(1, source.length() - 1);

        String[] split = source.split("]\\s*,?");

        List<List<Integer>> result = new ArrayList<>(split.length);

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].replaceAll("\\s*", "").replaceAll("\\[", "");
            String[] chars = oneD.split(",\\s*");
            // 去除双引号
            ArrayList<Integer> list = new ArrayList<>(chars.length);
            result.add(list);

            for (int j = 0; j < chars.length; j++) {
                list.add(Integer.parseInt(chars[j].trim()));
            }
        }

        return result;
    }

    // e.g : [[4, 2, 4], [2, 3, 5], [4, 4, 6]]
    public static int[][] convertInts(String source) {
        source = source.substring(1, source.length() - 1);

        String[] split = source.split("]\\s*,?");

        int[][] result = new int[split.length][];

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].replaceAll("\\s*", "").replaceAll("\\[", "");
            String[] chars = oneD.split(",\\s*");
            // 去除双引号
            result[i] = new int[chars.length];

            for (int j = 0; j < chars.length; j++) {
                result[i][j] = Integer.parseInt(chars[j].trim());
            }

        }

        return result;
    }

    public static String charsToLeetCode(char[][] chars) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (int i = 0; i < chars.length; i++) {
            String str = charsToLeetCode(chars[i]);
            builder.append(str);
            if (i < chars.length - 1) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();
    }

    public static String charsToLeetCode(char[] chars) {
        StringBuilder builder = new StringBuilder((chars.length << 2) + 1);

        builder.append("[");

        for (char aChar : chars) {
            builder.append("\"");
            builder.append(aChar);
            builder.append("\"");
            builder.append(",");
        }

        builder.setLength(builder.length() - 1);

        builder.append("]");

        return builder.toString();
    }

    public static int[][] randomMatrix(int N, int M, int rangL, int rangR) {
        int[][] result = new int[N][M];

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {
                result[i][j] = random.nextInt(rangR) + rangL;
            }

        }

        return result;
    }

    public static char[][] intsToChars(int[][] ints) {
        int N = ints.length;
        int M = ints[0].length;

        char[][] res = new char[N][M];

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {
                res[i][j] = Character.forDigit(ints[i][j], 10);
            }

        }

        return res;
    }

    public static void shuffle(int[] nums) {
        for (int i = 0; i < nums.length / 2; i++) {
            // 每次只需拿第一个元素进行交换即可
            swap(nums, 0, random.nextInt(nums.length));
        }
    }


    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static TreeNode randomTree(int N, int rangeL, int rangeR) {
        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = String.valueOf(random.nextInt(rangeR) + rangeL);
        }

        return TreeNode.buildTree(ArrayUtil.join(arr, StrUtil.COMMA));
    }

    public static LinkedList<Object> executeExpression(String expression, String arguments, Object instance) {
        if (expression.startsWith("[")) {
            expression = expression.substring(1, expression.length() - 1);
        }

        if (arguments.startsWith("[")) {
            arguments = arguments.substring(1, arguments.length() - 1);
        }

        List<String> items = StrUtil.split(expression, ",")
                .stream().map(s -> StrUtil.removeAll(s, '"', ' '))
                .collect(Collectors.toList());

        List<String> args = StrUtil.split(arguments, "],")
                .stream().map(s -> StrUtil.removeAll(s, '"', ' ', '[', ']'))
                .collect(Collectors.toList());

        LinkedList<Object> resultList = new LinkedList<>();

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);

            if (item.equals(instance.getClass().getSimpleName())) {
                continue;
            }

            Method method = ReflectUtil.getMethodByName(instance.getClass(), item);

            Class<?>[] types = method.getParameterTypes();

            if (types.length == 0) {
                ReflectUtil.invoke(instance, method);
            } else {

                String methodArgs = args.get(i);

                String[] split = methodArgs.split(",");

                Object[] invokeArgs = new Object[split.length];

                for (int j = 0; j < invokeArgs.length; j++) {
                    invokeArgs[j] = Convert.convert(types[j], split[j]);
                }

                Object result = ReflectUtil.invoke(instance, method, invokeArgs);

                if (result != null) {
                    resultList.add(result);
                }
            }
        }

        return resultList;
    }
}
