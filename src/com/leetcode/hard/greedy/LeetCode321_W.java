package com.leetcode.hard.greedy;

/**
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode321_W { // 单调栈和贪心思想
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end  = Math.min(k, m);
        // start: 如果k小于等于n那么在num1数组中最小的选择长度是0个, 反之则在num2全被选择后还剩下k-n个数在num1中选择
        // end: 最多再num1中选择k个数
        for (int i = start; i <= end; i++) { // i 是枚举选择子序列的长度
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }

        return maxSubsequence;
    }

    // 在数组中选择长度为k的最大子序列,使用单调递减栈
    private int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k]; // 使用数组作为递减栈
        int remain = length - k; // 需要remain的原因是为了避免如果在维护递减栈时num剩下的元素不够形成k个子序列了
        int top = -1;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain >0) {
                top--;
                remain--; // 这里减是因为在压栈操作时没有更新remain，所以在这里减就知道当前元素入栈时后面的元素还剩多少个
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else { // 当前数组下标递减栈的个数已经是k个了,虽然num也比栈顶小但是不用入栈了,只更新remain
                remain--;
            }
        }
        return stack;
    }

    private int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2); // 短的数组遍历完了,长度的数组还有长度
    }
}
