package com.leetcode.medium.review;

/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode581 {
    // 假设 nums_B在 nums 中对应区间为[left,right]。注意到 nums_B 和 nums_C中任意一个数都大于等于 nums_A 中任意一个数
    // 我们可以从大到小枚举 i，用一个变量 minn 记录nums_j (i + 1 <= j <= n - 1)
    // 每次移动 i，都可以 O(1) 地更新 minn。这样最后一个使得不等式不成立的 i 即为 left。left 左侧即为 nums_A能取得的最大范围
    // 同理，我们可以用类似的方法确定 right
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
