package com.leetcode.medium.review.stringprocessing;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
 *
 * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
 *
 * 在执行完所有删除操作后，返回最终得到的字符串。
 *
 * 本题答案保证唯一。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcd", k = 2
 * 输出："abcd"
 * 解释：没有要删除的内容。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1209_W {
    public String removeDuplicates(String s, int k) {
        StringBuilder stack = new StringBuilder();
        int cnt = 1;
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && cnt != k) {
                char c = s.charAt(i);
                int len = stack.length();
                char top = len == 0 ? '0' : stack.charAt(len - 1);
                if (top == c) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                stack.append(c);
                i++;
            }
            if (cnt == k) {
                stack.delete(stack.length() - k, stack.length());
                cnt = 1;
                int l = stack.length() - 1;
                while (l > 0 && stack.charAt(l) == stack.charAt(l - 1)) {
                    cnt++;
                    l--;
                }
            }
        }

        return stack.toString();
    }

    public String removeDuplicates_stack(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return sb.toString();
    }
//        作者：LeetCode
//        链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xiang-lin--4/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
