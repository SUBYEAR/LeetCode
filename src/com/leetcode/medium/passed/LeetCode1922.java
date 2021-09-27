package com.leetcode.medium.passed;

/**
 * 我们称一个数字字符串是 好数字 当它满足（下标从 0 开始）偶数 下标处的数字为 偶数 且 奇数 下标处的数字为 质数 （2，3，5 或 7）。
 *
 * 比方说，"2582" 是好数字，因为偶数下标处的数字（2 和 8）是偶数且奇数下标处的数字（5 和 2）为质数。
 * 但 "3245" 不是 好数字，因为 3 在偶数下标处但不是偶数。
 * 给你一个整数 n ，请你返回长度为 n 且为好数字的数字字符串 总数 。
 * 由于答案可能会很大，请你将它对 109 + 7 取余后返回 。
 *
 * 一个 数字字符串 是每一位都由 0 到 9 组成的字符串，且可能包含前导 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-good-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1922 {
    final int MOD = 1000_000_007;
    public int countGoodNumbers(long n) {

        long res = 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                res = (res * 5) % MOD;
            } else {
                res = (res * 4) % MOD;
            }
        }

        return (int) res;
    }

    public int countGoodNumbers_(long n) {
        long m1 = multiple(5, n / 2 + n % 2);
        long m2 = multiple(4, n / 2);
        return (int) (m1 * m2 % MOD);
    }

    public int multiple(int base, long power) {
        if (power == 0) return 1;
        if (power == 1) return base;
        int remainder = power % 2 == 0 ? 1 : base;
        long half = multiple(base, power / 2);
        int res = (int) ((half % MOD) * (half % MOD) * remainder % MOD);
        return res;
    }

}
