package com.leetcode.medium;

/**
 *
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
