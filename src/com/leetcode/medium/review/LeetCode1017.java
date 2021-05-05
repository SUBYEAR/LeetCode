package com.leetcode.medium.review;

/**
 * 给出数字 N，返回由若干 "0" 和 "1"组成的字符串，该字符串为 N 的负二进制（base -2）表示。
 *
 * 除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 *
 * 示例 1：
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-to-base-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1017 { // 问题的核心是理解进制数其实是在每一位上放置余数
    // java使用的是truncate 除法https://segmentfault.com/a/1190000015581794?utm_source=sf-related
    // 即商向0取整，余数和被除数符号一致，那么这样就会导致除-2时余数可能是-1，进制表示其实是要想将余数-1转换成1
    // 考虑将余数再减去-2，其实也就是商加一
    public String baseNeg2(int N) {
        if (N < 2) {
            return Integer.toString(N);
        }

        StringBuilder res = new StringBuilder();
        while (N != 0) {
            int r = N % (-2);
            N = N / (-2);

            if (r == -1) {
                ++N;
                res.append(1);
            } else {
                res.append(r);
            }
        }
        return res.reverse().toString();
    }
}
