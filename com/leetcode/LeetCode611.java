package com.leetcode;

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
