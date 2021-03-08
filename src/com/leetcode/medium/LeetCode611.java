package com.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/*
给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 */
public class LeetCode611 {
    List<List<Integer>> res = new LinkedList<>();
    public int triangleNumber(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i])
                        count++;
                }
            }
        }
        return count;
    }

    void getCombination(int[] nums, int start, int num, LinkedList<Integer> track) {
        if (num == 0) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (start == nums.length) {
            return;
        }

        track.add(nums[start]);
        getCombination(nums, start + 1, num - 1, track);
        track.removeLast();
        getCombination(nums, start + 1, num, track);
    }
    boolean isValidTriangle(int a, int b, int c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
}

// 双指针解法
// public int triangleNumber(int[] nums) {
//        int count = 0;
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length - 2; i++) {
//            int k = i + 2;
//            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
//                while (k < nums.length && nums[i] + nums[j] > nums[k])
//                    k++;
//                count += k - j - 1;
//            }
//        }
//        return count;
//    }
//

// 二分查找
// int binarySearch(int nums[], int l, int r, int x) {
//        while (r >= l && r < nums.length) {
//            int mid = (l + r) / 2;
//            if (nums[mid] >= x)
//                r = mid - 1;
//            else
//                l = mid + 1;
//        }
//        return l;
//    }
//    public int triangleNumber(int[] nums) {
//        int count = 0;
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length - 2; i++) {
//            int k = i + 2;
//            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
//                k = binarySearch(nums, k, nums.length - 1, nums[i] + nums[j]);
//                count += k - j - 1;
//            }
//        }
//        return count;
//    }
//
