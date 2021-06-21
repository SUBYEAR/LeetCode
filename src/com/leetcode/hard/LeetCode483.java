package com.leetcode.hard;

/**
 * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
 *
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-good-base
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode483 {
    public String smallestGoodBase(String n) {
    // 给定数字n在转换成k进制时，其最高位m < lgk_n; k的值等n的根号m次方的整数部分
        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));

        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }

    public boolean toNthBase(long num, long n) {

//        StringBuilder res = new StringBuilder();
        while (num != 0) {
            long remainder = num % n;
            if (remainder != 1) {
                return false;
            }
//            res.insert(0, remainder);
            num /= n;
        }
        return true;
    }
}
