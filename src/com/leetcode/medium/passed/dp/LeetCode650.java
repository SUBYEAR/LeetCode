package com.leetcode.medium.passed.dp;

import java.util.Arrays;

/**
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 *
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/2-keys-keyboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode650 {
    boolean[] isPrimed;
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        isPrimed = new boolean[n + 1];
        check(n);

        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrimed[i]) {
                dp[i] = i;
            } else {
                int tmp = i / 2;
                while (tmp > 1) {
                    if (i % tmp == 0) {
                        break;
                    }
                    --tmp;
                }
                dp[i] = dp[tmp] + i / tmp;
            }
        }
        return dp[n];
    }

    void check(int n) {
        Arrays.fill(isPrimed, true);
        for (int i = 2; i * i <= n; i++) {
            if (isPrimed[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrimed[j] = false;
                }
            }
        }
    }
}
