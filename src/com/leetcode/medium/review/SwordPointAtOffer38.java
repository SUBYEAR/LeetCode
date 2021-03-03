package com.leetcode.medium.review;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwordPointAtOffer38 {
    List<String> res = new LinkedList<>();

    public String[] permutation(String s) {
        char[] c = s.toCharArray();
        backtrack(c, 0);
        return res.toArray(new String[0]);
    }

    void backtrack(char[] c, int x) {
        if (x == c.length - 1) {
            res.add(String.valueOf(c));
            return;
        }

        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) continue;

            set.add(c[i]);
            swap(c, i, x);
            backtrack(c, x + 1);
            swap(c, i, x);
        }
    }

    void swap(char[] c, int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }
}

//    List<String> res = new LinkedList<>();
//    char[] c;
//    public String[] permutation(String s) {
//        c = s.toCharArray();
//        dfs(0);
//        return res.toArray(new String[res.size()]);
//    }
//    void dfs(int x) {
//        if(x == c.length - 1) {
//            res.add(String.valueOf(c)); // 添加排列方案
//            return;
//        }
//        HashSet<Character> set = new HashSet<>();
//        for(int i = x; i < c.length; i++) {
//            if(set.contains(c[i])) continue; // 重复，因此剪枝
//            set.add(c[i]);
//            swap(i, x); // 交换，将 c[i] 固定在第 x 位
//            dfs(x + 1); // 开启固定第 x + 1 位字符
//            swap(i, x); // 恢复交换
//        }
//    }
//    void swap(int a, int b) {
//        char tmp = c[a];
//        c[a] = c[b];
//        c[b] = tmp;
//    }
//
