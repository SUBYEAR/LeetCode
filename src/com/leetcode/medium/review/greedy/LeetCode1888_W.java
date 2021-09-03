package com.leetcode.medium.review.greedy;

/**
 * 给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：
 *
 * 类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
 * 类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
 * 请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。
 *
 * 我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。
 *
 * 比方说，字符串 "010" 和 "1010" 都是交替的，但是字符串 "0100" 不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1888_W {
    public int minFlips(String s) {
        // 用 pre[i][j] 表示对于字符串的前缀 s[0..i]，如果我们希望通过类型 2 的操作修改成「以j结尾的交替字符串」，那么最少需要的操作次数。
        // 这里 j 的取值为 0 或 1 pre[i][0]=pre[i−1][1]+(s[i]==1); pre[i][1]=pre[i−1][0]+(s[i]==0)
        // 同理，我们用 suf[i][j] 表示对于字符串的后缀 s[i..n−1]，如果我们希望通过类型 2 的操作修改成「以 j 开头的交替字符串」，那么最少需要的操作次数
        // suf[i][0]=suf[i+1][1]+(s[i]==1); suf[i][1]=suf[i+1][0]+(s[i]==0)
        // n 是偶数，我们无需求出 suf, 答案可以为 pre[n−1][0] 或者 pre[n−1][1]

        int n = s.length();
        int[][] pre = new int[n][2];

        for (int i = 0; i < n; i++) {
            pre[i][0] = (i == 0 ? 0 : pre[i - 1][1]) + (s.charAt(i) == '1' ? 1 : 0);
            pre[i][1] = (i == 0 ? 0 : pre[i - 1][0]) + (s.charAt(i) == '0' ? 1 : 0);
        }

        int ans = Math.min(pre[n - 1][0], pre[n - 1][1]);
        if (n % 2 != 0) {
            // 要么由两个交替字符串拼接而成，其中左侧的交替字符串以 0 结尾，右侧的交替字符串以 0 开头。
            // 如果 n 是奇数，还需要求出 suf
            int[][] suf = new int[n][2];
            for (int i = n - 1; i >= 0; i--) {
                suf[i][0] = (i == n - 1 ? 0 : suf[i + 1][1]) + (s.charAt(i) == '1' ? 1 : 0);
                suf[i][1] = (i == n - 1 ? 0 : suf[i + 1][0]) + (s.charAt(i) == '0' ? 1 : 0);
            }

            for (int i = 0; i + 1 < n; i++) {
                ans = Math.min(ans, pre[i][0] + suf[i + 1][0]);
                ans = Math.min(ans, pre[i][1] + suf[i + 1][1]);
            }
        }
        return ans;
    }
}
