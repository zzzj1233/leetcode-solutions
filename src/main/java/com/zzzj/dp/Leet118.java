package com.zzzj.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-08 12:10
 */
public class Leet118 {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<Integer> path = new ArrayList<>(1);

        path.add(1);

        List<List<Integer>> ans = new ArrayList<>(numRows);

        for (int i = 0; i < numRows; i++) {
            ans.add(path);

            ArrayList<Integer> newPath = new ArrayList<>(path.size() + 1);

            newPath.add(path.get(0));

            for (int j = 0; j < path.size() - 1; j++) {
                newPath.add(path.get(j) + path.get(j + 1));
            }

            newPath.add(path.get(path.size() - 1));

            path = newPath;
        }

        return ans;
    }


}
