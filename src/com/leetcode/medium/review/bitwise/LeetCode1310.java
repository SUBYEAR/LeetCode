package com.leetcode.medium.review.bitwise;

/**
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）
 * 作为本次查询的结果。并返回一个包含给定查询 queries 所有结果的数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1310 {
    // 根据上述分析，这道题可以分两步求解。
    // 计算前缀异或数组 xors, xors[i] 为从arr[0]到arr[i−1] 的元素的异或运算结果
    // 计算每个查询的结果，第 i 个查询的结果为 xors[queries[i][0]] ⊕ xors[queries[i][1]+1]。
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xors = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xors[i + 1] = xors[i] ^ arr[i];
        }

        int len = queries.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];
        }
        return res;
    }
}
