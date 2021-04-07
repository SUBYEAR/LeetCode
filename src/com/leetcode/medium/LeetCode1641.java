package com.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
 *
 * 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：5
 * 解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-sorted-vowel-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1641 {
    List<String> res = new LinkedList<>();

    public int countVowelStrings(int n) { // 回溯算法用时比较久尝试使用动态规划
        char[] arr = new char[]{'a', 'e', 'i', 'o', 'u'};
        backtrack(arr, 0, n, new StringBuilder());
        return res.size();
    }

    void backtrack(char[] arr, int num, int n, StringBuilder path) {
        if (num == n) {
            res.add(path.toString());
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (path.length() > 0 && arr[i] < path.charAt(path.length() - 1)) {
                continue;
            }
            path.append(arr[i]);
            backtrack(arr, num + 1, n, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public int countVowelStrings_dp(int n) {
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 1][0];
            dp[i][2] = dp[i - 1][2] + dp[i - 1][1] + dp[i - 1][0];
            dp[i][3] = dp[i - 1][3] + dp[i - 1][2] + dp[i - 1][1] + dp[i - 1][0];
            dp[i][4] = dp[i - 1][4] + dp[i - 1][3] + dp[i - 1][2] + dp[i - 1][1] + dp[i - 1][0];
        }
        int res = IntStream.of(dp[n - 1]).sum();
        return res;
    }
}
