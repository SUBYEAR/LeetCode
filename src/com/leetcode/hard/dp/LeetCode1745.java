package com.leetcode.hard.dp;

/**
 * 给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
 *
 * 当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1745 {
    // 回溯超时
    public boolean checkPartitioning(String s) {
        int length = s.length();
        //dp[i][j]：表示字符串s从下标i到j是否是回文串
        boolean[][] dp = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                //如果i和j指向的字符不一样，那么dp[i][j]就
                //不能构成回文字符串
                if (s.charAt(i) != s.charAt(j))
                    continue;
                dp[i][j] = j - i <= 2 || dp[i + 1][j - 1];
            }
        }

        //然后再截取3段，判断这3段是否都是回文的
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                if (dp[0][i] && dp[i + 1][j] && dp[j + 1][length - 1])
                    return true;
            }
        }
        return false;
        // 链接：https://leetcode-cn.com/problems/palindrome-partitioning-iv/solution/shu-ju-jie-gou-he-suan-fa-dong-tai-gui-h-klhs/
    }
}
