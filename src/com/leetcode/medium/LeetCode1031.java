package com.leetcode.medium;

/**
 * 给出非负整数数组 A ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 L 和 M。
 * （这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）
 * 从形式上看，返回最大的 V，而 V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) 并满足下列条件之一：
 *
 *  
 *
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, 或
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 *  
 *
 * 示例 1：
 *
 * 输入：A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * 输出：20
 * 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-of-two-non-overlapping-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1031 {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int len = A.length;
        int[] arrL= getArr(A, len, L);
        int[] arrM= getArr(A, len, M);
        int res = 0;
        for (int i = 0; i < len - M; i++) {
            for (int j = len - M; j >= i + L; j--) {
                res = Math.max(res, arrL[i] + arrM[j]);
            }
        }
        if (L == M) {
            return res;
        }
        for (int i = 0; i < len - L; i++) {
            for (int j = len - L; j >= i + M; j--) {
                res = Math.max(res, arrM[i] + arrL[j]);
            }
        }

        return res;
    }

    int[] getArr(int[] A, int len, int L) {
        int[] arrL= new int[len - L + 1];
        for (int i = 0; i < L; i++) {
            arrL[0] += A[i];
        }

        for (int i = 1; i < arrL.length; i++) {
            arrL[i] = arrL[i - 1] + A[i + L - 1] - A[i - 1];
        }
        return arrL;
    }

    public int maxSumTwoNoOverlap_(int[] A, int l, int m) {
        int n = A.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i ++) sum[i] = sum[i - 1] + A[i - 1];

        int res = 0;
        for (int i = 1, j = l + 1, left = 0; j + m - 1 <= n; i ++, j ++)
        {
            left = Math.max(left, sum[i + l - 1] - sum[i - 1]);
            res = Math.max(left + sum[j + m - 1] - sum[j - 1], res);
        }

        for (int i = 1, j = m + 1, left = 0; j + l - 1 <= n; i ++, j ++)
        {
            left = Math.max(left, sum[i + m - 1] - sum[i - 1]);
            res = Math.max(left + sum[j + l - 1] - sum[j - 1], res);
        }
        return res;
    }

//    作者：schinapy
//    链接：https://leetcode-cn.com/problems/maximum-sum-of-two-non-overlapping-subarrays/solution/qian-zhui-he-hua-dong-chuang-kou-zuo-liang-bian-by/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
