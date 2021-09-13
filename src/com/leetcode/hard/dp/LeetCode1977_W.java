package com.leetcode.hard.dp;

/**
 * 你写下了若干 正整数 ，并将它们连接成了一个字符串 num 。但是你忘记给这些数字之间加逗号了。你只记得这一列数字是 非递减 的且 没有 任何数字有前导 0 。
 *
 * 请你返回有多少种可能的 正整数数组 可以得到字符串 num 。由于答案可能很大，将结果对 109 + 7 取余 后返回。
 * 提示：
 *
 * 1 <= num.length <= 3500
 * num 只含有数字 '0' 到 '9' 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-separate-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1977_W {
    // f[i][j]表示堆字符串num的[0...j]个字符进行划分,并且最后一个数使用了第[i...j]个字符的方案数.
    // dp数组的定义相当于把字符串分为两部分,第二部分为最后一个数其位数是j-i+1,num(i,j)表示后半部分的这个数
    // 状态转移过程
    // 由于f[i][j]中的最后一个数的位数位j-i+1,那么上一个数的位数小于j-i既可转移.上一个数的结尾位置在i-1,那么其开始下标大于等于
    // (i-1)-(j-i)+1 = 2i-j
    // Sum(f[2i−j][i−1],f[2i−j+1,i−1],⋯,f[i−1][i−1])
    // 此外还要比较比较num(2i−j−1,i−1) 和 num(i,j) 的值的大小关系.这两个数的位数都是 j-i+1。
    // 设pre[i][j] = f[k][j]求和sum和k的范围是[0...i]
    // pre[i−1][i−1]−pre[2i−j−1][i−1],  num(2i−j−1,i−1)>num(i,j)
    // pre[i−1][i−1]−pre[2i−j−2][i−1],  num(2i−j−1,i−1)≤num(i,j)

    // 快速比较 num(2i−j−1,i−1) 和 num(i,j) 的大小关系了。这一步可以使用预处理巧妙地解决。
    // 记 lcp[i][j] 表示在字符串nums 中，以 i 开始的后缀与以 j 开始的后缀的「最长公共前缀」的长度。直观上看，它表示：
    final  int mod = 1000000007;
    public int numberOfCombinations(String num) {
        if (num.charAt(0) == '0') {
            return 0;
        }

        int n = num.length();

        // 预处理 lcp
        int[][] lcp = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            lcp[i][n - 1] = (num.charAt(i) == num.charAt(n - 1)) ? 1 : 0;
            for (int j = i + 1; j < n - 1; ++j) {
                lcp[i][j] = (num.charAt(i) == num.charAt(j) ? lcp[i + 1][j + 1] + 1 : 0);
            }
        }

        // 辅助函数，计算 x = (x + y) % mod
//        auto update = [&](int& x, int y) {
//            x += y;
//            if (x >= mod) {
//                x -= mod;
//            }
//        };

        // 动态规划
        int[][] f = new int[n][n];
        // 边界 f[0][..] = 1
        for (int i = 0; i < n; ++i) {
            f[0][i] = 1;
        }
        for (int i = 1; i < n; ++i) {
            // 有前导零，无需转移
            if (num.charAt(i) == '0') {
                continue;
            }
            // 前缀和
            int presum = 0;
            for (int j = i; j < n; ++j) {
                int length = j - i + 1;
                f[i][j] = presum;
                if (i - length >= 0) {
                    // 使用 lcp 比较 num(2i-j-1,i-1) 与 num(i,j) 的大小关系
                    if (lcp[i - length][i] >= length
                            || num.charAt(i - length + lcp[i - length][i]) < num.charAt(i + lcp[i - length][i])) {
//                        update(f[i][j], f[i - length][i - 1]);
                        f[i][j] = (f[i][j] + f[i - length][i - 1]) % mod;
                    }
                    // 更新前缀和
//                    update(presum, f[i - length][i - 1]);
                    presum = (presum + f[i - length][i - 1]) % mod;
                }
            }
        }

        // 最终答案即为所有 f[..][n-1] 的和
        int ans = 0;
        for (int i = 0; i < n; ++i) {
//            update(ans, f[i][n - 1]);
            ans = (ans + f[i][n - 1]) % mod;
        }
        return ans;
    }

}
