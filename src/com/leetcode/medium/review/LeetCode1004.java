package com.leetcode.medium.review;

/**
 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

 返回仅包含 1 的最长（连续）子数组的长度。
 示例 1：

 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 输出：6
 解释：
 [1,1,1,0,0,1,1,1,1,1,1]
 粗体数字从 0 翻转到 1，最长的子数组长度为 6。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1004 {
    public int longestOnes(int[] A, int K) {
        int len = A.length;
        int res = 0, left = 0, right = 0;
        int zeroCount = 0;
        while (right < len) {
            if (A[right] == 0) {
                zeroCount++;
            }
            right++;

            if (zeroCount > K) {
                if (A[left] == 0) {
                    zeroCount--;
                }
                left++;
                continue; // 这一题与424提有一个不一样的地方是此处的continue处理这里在更新结果时一定要保证0的数目是不大于K个
                // 424题的替换字母可以是是任意字母被替换所以每一次都要更新结果res
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}

// int res = 0, i = 0, j = 0;
// 		for (; i < A.length; i++) {
// 			if (A[i] == 0) {
// 				if (K > 0) {
// 					K--;
// 				} else {
// 					res = Math.max(res, i - j);
// 					while (A[j++] == 1);
// 				}
// 			}
// 		}
// 		return Math.max(res, i - j);