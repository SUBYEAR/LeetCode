package com.leetcode.medium.review.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode90 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        boolean[] used = new boolean[len];
        backtrack(nums, 0, used, new LinkedList<>());
        return res;
    }

    void backtrack(int[] nums, int start, boolean[] used, List<Integer> path) {
        res.add(new LinkedList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            System.out.println(path);
            backtrack(nums, i + 1, used, path);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
