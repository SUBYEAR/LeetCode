package com.leetcode.hard.dualPointer;

import java.util.Arrays;

/**
 * 给定字符串 S and T，找出 S 中最短的（连续）子串 W ，使得 T 是 W 的 子序列 。
 *
 * 如果 S 中没有窗口可以包含 T 中的所有字符，返回空字符串 ""。如果有不止一个最短长度的窗口，返回开始位置最靠左的那个。
 *
 * 示例 1：
 *
 * 输入：
 * S = "abcdebdde", T = "bde"
 * 输出："bcde"
 * 解释：
 * "bcde" 是答案，因为它在相同长度的字符串 "bdde" 出现之前。
 * "deb" 不是一个更短的答案，因为在窗口中必须按顺序出现 T 中的元素。
 *
 * 遍历 T，维护 cur 数组，在遍历到 T[j] 时，让 cur[e] = s，使其满足 T[:j] 为 S[s: e+1] 的子序列。在找到包含 T[:j] 的窗口的
 * 情况下，定义 new 来找到包含 T[:j+1] 的窗口。定义的是 dp[j] 和 dp[~j] 数组，这两个数组目的是一样的，都是用来保存动态
 * 规划最后两行的数据。
 *
 * 最后，遍历所有窗口找到最小窗口作为答案。。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode727 {
    public String minWindow(String S, String T) {
        int[][] dp = new int[2][S.length()];

        for (int i = 0; i < S.length(); ++i)
            dp[0][i] = S.charAt(i) == T.charAt(0) ? i : -1;

        /*At time j when considering T[:j+1],
          the smallest window [s, e] where S[e] == T[j]
          is represented by dp[j & 1][e] = s, and the
          previous information of the smallest window
          [s, e] where S[e] == T[j-1] is stored as
          dp[~j & 1][e] = s.
        */
        for (int j = 1; j < T.length(); ++j) {
            int last = -1;
            Arrays.fill(dp[j & 1], -1);
            //Now we would like to calculate the candidate windows
            //"dp[j & 1]" for T[:j+1].  'last' is the last window seen.
            for (int i = 0; i < S.length(); ++i) {
                if (last >= 0 && S.charAt(i) == T.charAt(j))
                    dp[j & 1][i] = last;
                if (dp[~j & 1][i] >= 0)
                    last = dp[~j & 1][i];
            }
        }

        //Looking at the window data dp[~T.length & 1],
        //choose the smallest length window [s, e].
        int start = 0, end = S.length();
        for (int e = 0; e < S.length(); ++e) {
            int s = dp[~T.length() & 1][e];
            if (s >= 0 && e - s < end - start) {
                start = s;
                end = e;
            }
        }
        return end < S.length() ? S.substring(start, end + 1) : "";
    }

    public  static final String EMPTY = "";
    public String minWidow_2(String S, String T) {
        if (S == null || S.length() == 0 || T.length() == 0) {
            return EMPTY;
        }

        int n = S.length(), m = T.length();
        int i = 0,          j = 0;
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        String res = "";

        while (i < n) {
            // 正向匹配找到T中最后一个字符
            while (i < n && j < m) {
                if (S.charAt(i) == T.charAt(j)) {
                    i++;
                    j++;
                } else {
                    i++;
                }
            }

            if (j < m) { // 当前起点之后再匹配不到T了
                break;
            }

            // 逆向缩小窗口
            right = i - 1;
            left = i - 1;
            j = m - 1;
            while (left >= 0 && j >= 0) {
                if (S.charAt(left) == T.charAt(j)) {
                    left--;
                    j--;
                } else {
                    left--;
                }
            }
            left++;

            // 更新窗口长度
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                res = S.substring(left, left + minLen);
            }

            // 下一个可能的窗口起点
            i = left + 1;
            j = 0;
        }
        return res;
    }
}
