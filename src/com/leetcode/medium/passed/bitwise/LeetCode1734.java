package com.leetcode.medium.passed.bitwise;

/**
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。
 * 比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-xored-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1734 {
    public int[] decode(int[] encoded) {
        int len = encoded.length;
        int[] res = new int[len + 1];
        int sumXor = (len / 2) % 2 == 0 ? 1 : 0;
        int lessOne = 0;
        for (int i = 0; i < len; i += 2) {
            lessOne ^= encoded[i];
        }
        res[len] = lessOne ^ sumXor;
        for (int j = len; j >= 1; j--) {
            res[j - 1] = res[j] ^ encoded[j - 1];
        }
        return res;
    }
}
