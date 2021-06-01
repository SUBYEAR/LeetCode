package com.leetcode.medium;

import java.util.*;

/**
 * 给你一个字符串 s 以及两个整数 a 和 b 。其中，字符串 s 的长度为偶数，且仅由数字 0 到 9 组成。
 * 你可以在 s 上按任意顺序多次执行下面两个操作之一：
 * 累加：将  a 加到 s 中所有下标为奇数的元素上（下标从 0 开始）。数字一旦超过 9 就会变成 0，如此循环往复。例如，s = "3456" 且 a = 5，
 * 则执行此操作后 s 变成 "3951"。
 * 轮转：将 s 向右轮转 b 位。例如，s = "3456" 且 b = 1，则执行此操作后 s 变成 "6345"。
 * 请你返回在 s 上执行上述操作任意次后可以得到的 字典序最小 的字符串。
 * 如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符出现在字母表中的
 * 时间早于 b 中的对应字符。例如，"0158” 字典序比 "0190" 小，因为不同的第一个位置是在第三个字符，显然 '5' 出现在 '9' 之前。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lexicographically-smallest-string-after-applying-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1625 {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> q = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        q.add(s);
        char[] chars = s.toCharArray();
        Arrays.fill(chars, '9');
        String ans = new String(chars);
        while (!q.isEmpty()) {
            String cur = q.poll();
            if (cur.compareTo(ans) < 0) {
                ans = cur;
            }
            for (String str : convert(cur, a, b)) {
                if (!seen.add(str)) {
                    continue;
                }
                q.add(str);
            }
        }
        return ans;
    }

    String[] convert(String s, int a, int b) {
        int len = s.length();
        b %= len;
        StringBuilder ans = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int n = s.charAt(i) - '0';
            ans.append( i % 2 == 0 ? n : (n + a) % 10);
        }
        return new String[] {ans.toString(), s.substring(len - b) + s.substring(0, len - b)};
    }
}
