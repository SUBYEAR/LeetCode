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

    /* 二分 */
    public String smallestGoodBase_bs(String n) {
        long num = Long.parseLong(n);
        // 枚举 k进制 中 1 的个数，最多为 二进制 时的位数
        for (int i = (int) (Math.log(num) / Math.log(2) + 1); i > 2; --i) {
            // k^0 + k^1 + …… + k^(i-1) = n -- 通过二分法计算 k
            long left = 2, right = num - 1;
            while (left <= right) {
                long mid = (left + right) / 2;
                long s = 0, MaxVal = num / mid + 1;
                for (int j = 0; j < i; ++j)
                    if (s < MaxVal)
                        s = s * mid + 1;
                    else {
                        s = num + 1;
                        break;
                    }
                if (s == num)
                    return Long.toString(mid);
                else if (s > num)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return Long.toString(num - 1);
    }

//    链接：https://leetcode-cn.com/problems/smallest-good-base/solution/zui-xiao-hao-jin-zhi-er-fen-shu-xue-fang-frrv/

}
