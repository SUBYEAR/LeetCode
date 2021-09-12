package com.leetcode.medium.review.stringprocessing;

import java.util.*;

/**
 * 给定字符串列表，你需要从它们中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 *
 * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
 *
 * 输入将是一个字符串列表，输出是最长特殊序列的长度。如果最长特殊序列不存在，返回 -1 。
 * 示例：
 *
 * 输入: "aba", "cdc", "eae"
 * 输出: 3
 *  
 *
 * 提示：
 *
 * 所有给定的字符串长度不会超过 10 。
 * 给定字符串列表的长度将在 [2, 50 ] 之间。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-uncommon-subsequence-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode522 {
    public boolean isSubsequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++)
            if (x.charAt(j) == y.charAt(i))
                j++;
        return j == x.length();
    }
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator < String > () {
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });
        for (int i = 0, j; i < strs.length; i++) {
            boolean flag = true;
            for (j = 0; j < strs.length; j++) {
                if (i == j)
                    continue;
                if (isSubsequence(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return strs[i].length();
        }
        return -1;
    }


    public void backtrack(String s, int start, int limit, StringBuilder path, List<String> sub) {
        if (path.length() == limit) {
            sub.add(path.toString());
            System.out.println(path);
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                continue;
            }
            set.add(c);
            path.append(c);
            backtrack(s, i + 1, limit, path,sub);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
