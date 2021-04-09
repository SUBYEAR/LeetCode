package com.leetcode.medium;

/**
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode153 {
    public int findMin(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int i = 0;
        for (i = 1; i < len; i++) {
            if (nums[i - 1] > nums[i]) {
                break;
            }
        }
        return i == len ? nums[0] : Math.min(nums[0], nums[i]);
    }
    public int findMin_bs(int[] nums) {
        // 本题的 left + 1 ，要么是更大的值（升序），要么突然降序，掉到了最小值。
        // 所以无论如何，最小值还是包括在寻找范围内的。而右侧区域，如果草率就 right - 1 的话，可能就会错过最小值了
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int pivot = left + (right - left) / 2;
            if (nums[pivot] > nums[right]) {
                right = pivot;
            } else {
                left = pivot + 1;
            }
        }
        return nums[left];
    }
}
