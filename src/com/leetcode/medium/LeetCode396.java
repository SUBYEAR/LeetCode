package com.leetcode.medium;

/**
 * 给定一个长度为 n 的整数数组 A 。
 *
 * 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：
 *
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
 *
 * 计算F(0), F(1), ..., F(n-1)中的最大值。
 * 示例:
 *
 * A = [4, 3, 2, 6]
 *
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 *
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 * 注意:
 * 可以认为 n 的值小于 10^5。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-function
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode396 {
    public int maxRotateFunction(int[] nums) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        int pre = 0;
        for (int i = 1; i <= len; i++) {
            preSum[i] += preSum[i - 1] + nums[i - 1];
            pre += (i - 1) * nums[i - 1];
        }

        int cur = 0;
        int ans = pre;
        for (int i = 1; i < len; i++) { // 选择次数
            cur = pre + preSum[len - i] + (preSum[len] - preSum[len - i + 1]) - (len - 1) * nums[len - i];
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }
}
