package com.leetcode.medium;

/*
峰值元素是指其值大于左右相邻值的元素。

给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-peak-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode162 {
    public int findPeakElement(int[] nums) {
        int[] tmp = new int[nums.length + 2];
        tmp[0] = Integer.MIN_VALUE;
        tmp[nums.length + 1] = Integer.MIN_VALUE;
        int j = 1;
        for(int num : nums) {
            tmp[j++] = num;
        }

        j = nums.length;
        for (int i = 1; i <= j; i++, j--) {
            if (tmp[i] > tmp[i - 1] && tmp[i] > tmp[i + 1]) {
                return i - 1;
            }
            if (tmp[j] > tmp[j - 1] && tmp[j] > tmp[j + 1]) {
                return j - 1;
            }
        }
        return 0;
    }

    // 使用二分法找最值，提交后执行速度更快
    public int searchIndex(int[]nums, int start, int end) {
        if (start == end) {
            return start;
        }
        if (end - start == 1) {
            return nums[start] >= nums[end] ? start : end;
        }
        int idx1 = searchIndex(nums, start, (end+start)/2);
        int idx2 = searchIndex(nums, (end+start)/2 + 1, end);
        return nums[idx1] >= nums[idx2] ? idx1 : idx2;
    }
}

// 二分查找：当是上坡时峰值肯定在右边，当是下坡时峰值肯定在左边，通过这样不断缩小搜索范围
//public class Solution {
//    public int findPeakElement(int[] nums) {
//        int l = 0, r = nums.length - 1;
//        while (l < r) {
//            int mid = (l + r) / 2;
//            if (nums[mid] > nums[mid + 1])
//                r = mid;
//            else
//                l = mid + 1;
//        }
//        return l;
//    }
//}
