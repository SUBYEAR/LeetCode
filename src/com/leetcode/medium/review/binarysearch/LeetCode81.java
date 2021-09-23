package com.leetcode.medium.review.binarysearch;

/**
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode81 {
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if (len == 1) {
            return target == nums[0];
        }
        int i;
        for (i = 1; i < len; i++) {
            if (nums[i - 1] > nums[i]) {
                break;
            }
        }

        return search(nums, 0, i, target) || search(nums, i, len, target);
    }

    boolean search(int[] nums, int start, int end, int target) {
        int l = start;
        int r = end;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }
}

// 官方解法
//    public boolean search(int[] nums, int target) {
//        int n = nums.length;
//        if (n == 0) {
//            return false;
//        }
//        if (n == 1) {
//            return nums[0] == target;
//        }
//        int l = 0, r = n - 1;
//        while (l <= r) {
//            int mid = (l + r) / 2;
//            if (nums[mid] == target) {
//                return true;
//            }
//            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
//                ++l;
//                --r;
//            } else if (nums[l] <= nums[mid]) {
//                if (nums[l] <= target && target < nums[mid]) {
//                    r = mid - 1;
//                } else {
//                    l = mid + 1;
//                }
//            } else {
//                if (nums[mid] < target && target <= nums[n - 1]) {
//                    l = mid + 1;
//                } else {
//                    r = mid - 1;
//                }
//            }
//        }
//        return false;
//    }
