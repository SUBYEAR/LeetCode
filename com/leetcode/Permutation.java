/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述
 *
 * @since 2020-05-14
 */
public class Permutation {
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean isExchangeable(char[] arr, int begin, int end) {
        for (int i = begin; i < end; i++) {
            if (arr[i] == arr[end]) {
                return false;
            }
        }

        return true;
    }

    List<String> permutation(String s) {
        List<String> result = new LinkedList<>();
        getPermutation(s.toCharArray(), 0, s.length() - 1, result);

        for (String str : result) {
            System.out.println(str);
        }

        return result;
    }

    void getPermutation(char[] arr, int left, int end, List<String> result) { // 有重复
        if (arr == null) {
            return;
        }

        if (left == end) {
            result.add(new String(arr));
        }

        for (int i = left; i <= end; i++) {
            if (isExchangeable(arr, left, i)) {
                swap(arr, left, i);
                getPermutation(arr, left + 1, end, result);
                swap(arr, left, i);
            }
        }
    }
    List<String> res = new LinkedList<>();
    void getPermutations(char[] arr, int start, LinkedList<Character> track) { // 无重复
        if (track.size() == arr.length) {
            String objects = track.stream().map(String::valueOf).collect(Collectors.joining());
            res.add(new String(objects));
        }

        for (int i = 0; i < arr.length; i++) {
            if (track.contains(arr[i])) continue;
            track.add(arr[i]);
            getPermutations(arr, i + 1, track);
            track.removeLast();
        }
    }

    public List<String> combination(String s) { // 子集
        StringBuffer stringBuffer = new StringBuffer();
        List<String> str = new LinkedList<>();
        for (int i = 1; i <= s.length(); i++) {
            str.addAll(getCombination(s.toCharArray(), 0, i, stringBuffer));
        }
        for (String ss : str) {
            System.out.println(ss);
        }
        return str;
    }

    List<String> getCombination(char[] arr, int begin, int num, StringBuffer result) {
        List<String> str = new LinkedList<>();
        if (arr == null) {
            return str;
        }

        if (num == 0) {
            str.add(result.toString());
            return str;
        }

        if (begin == arr.length) {
            return str;
        }

        result.append(arr[begin]);
        str.addAll(getCombination(arr, begin + 1, num - 1, result)); // 选它
        result.deleteCharAt(result.length() - 1);
        str.addAll(getCombination(arr, begin + 1, num, result)); // 不选
        return str;
    }

    class BackTrack {
        List<List<Integer>> res = new LinkedList<>();

        /* 主函数，输入一组不重复的数字，返回它们的全排列 */
        List<List<Integer>> permute(int[] nums) {
            // 记录「路径」
            LinkedList<Integer> track = new LinkedList<>();
            backtrack(nums, track);
            return res;
        }

        // 路径：记录在 track 中
        // 选择列表：nums 中不存在于 track 的那些元素
        // 结束条件：nums 中的元素全都在 track 中出现
        void backtrack(int[] nums, LinkedList<Integer> track) {
            // 触发结束条件
            if (track.size() == nums.length) {
                res.add(new LinkedList(track));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                // 排除不合法的选择
                if (track.contains(nums[i]))
                    continue;
                // 做选择
                track.add(nums[i]);
                // 进入下一层决策树
                backtrack(nums, track);
                // 取消选择
                track.removeLast();
            }
        }
    }
}
