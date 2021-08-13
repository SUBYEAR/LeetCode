package com.leetcode.hard.dp;

/**
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 */
public class LeetCode233 {
    public int countDigitOne(int n) { // 数位dp
        // n=1234567 百位上1的个数考虑百位的更上一位是千位，n有1234个1000，每1000中百位上为1的数是[100, 199]共100个数
        // [n/1000]取整乘以100，不在完整循环中的数字为n%1000,
        // 如果取余小于100，则剩余个数为0
        // 如果取余大于等于200，则百位上100个1都出现了
        // 如果取余的值大于等于100小于200，则mod - 100 + 1
        // 以同样的逻辑从地位往高位开始处理每一位上1的个数
        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k）
        // 但为了让代码看起来更加直观，这里保留了 k
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
}
