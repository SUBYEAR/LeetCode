package com.leetcode;


import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test2 {
    List<String> res = new LinkedList<String>();

    public int encodeString(String s) {
        if (s.length() < 2) {
            return -1;
        }
        StringBuilder path = new StringBuilder();
        boolean[] visited = new boolean[s.length()];
        backtrack(s, 0, path);
        if (res.size() == 0) {
            return -1;
        }

        return res.size() < 2 ? res.get(0).length() :  -1;

    }

    boolean backtrack(String str, int start, StringBuilder path) {
        if (start >= str.length()) {
            System.out.println("####终止条件：" + path);
            res.add(path.toString());
            return true;
        }
        boolean b = false;
        for (int i = start; i < str.length(); i++) {
            int j = i;
            int num = 0;
            while (Character.isDigit(str.charAt(j))) {
                num = num * 10 + str.charAt(j) - '0';
                if (j + 1 + num > str.length()) {
                    return false;
                }

                if (j + 1 + num < str.length() && ((str.charAt(j + 1 + num) == '0') || !Character.isDigit(str.charAt(j + 1 + num)))) {
                    return false;
                }

                String substring = str.substring(j + 1, j + 1 + num);
                path.append(substring);
                System.out.println("递归前：" + substring + ". path: " + path);
                b = backtrack(str, j + 1 + num, path);
                path.delete(path.length() - num, path.length());
                System.out.println("递归后"  + ". path: " + path);

                j++;
            }
            i += num;
            if (b) return b;
        }
        return true;
    }
}
