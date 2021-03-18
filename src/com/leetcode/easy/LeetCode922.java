package com.leetcode.easy;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode922 {
    public int[] sortArrayByParityII(int[] A) {
        int l = 0;
        int r = A.length - 1;
        while (l < r) {
            if ((A[l] % 2) != 0) {
                swap(A, l, r);
                --r;
            } else {
                l++;
            }
        }
        int[] res = new int[A.length];
        for (l = 0 ; l < A.length / 2; l++) {
            res[2 * l] = A[l];
            res[2 * l + 1] = A[l + A.length / 2];
        }
        return res;
    }

    void swap (int[] A, int i, int j) {
        int temp  = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
