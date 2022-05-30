package com.zzzj.leet;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-05-27 18:18
 */
public class Leet500 {

    public static String[] findWords(String[] words) {
        return Arrays.asList(words)
                .stream()
                .filter(s ->
                        s.toLowerCase().replaceAll("^[qwertyuiop]*$", "").equals("")
                                || s.toLowerCase().replaceAll("^[asdfghjkl]*$", "").equals("")
                                || s.toLowerCase().replaceAll("^[zxcvbnm]*$", "").equals("")
                )
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

}
