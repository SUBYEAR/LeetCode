package com.leetcode.medium.review;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。
 */
public class LeetCode40 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, new LinkedList<>());
        return res;

    }

    void backtrack(int[] arr, int start, int target, List<Integer> track) {
        if (target == 0) {
            if (res.contains(track)) {
                return;
            }
            res.add(new LinkedList<>(track));
            return;
        }
        if (start == arr.length || arr[start] > target || target < 0) {
            return;
        }

        track.add(arr[start]);
        backtrack(arr, start + 1, target - arr[start], track);
        track.remove(track.size() - 1);
        backtrack(arr, start + 1, target, track);
    }
}

// 官方解法列表 freq 的长度即为数组 candidates 中不同数的个数。其中的每一项对应着哈希映射中的一个键值对，即某个数以及它出现的次数
//
//class Solution {
//    List<int[]> freq = new ArrayList<int[]>();
//    List<List<Integer>> ans = new ArrayList<List<Integer>>();
//    List<Integer> sequence = new ArrayList<Integer>();
//
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        for (int num : candidates) {
//            int size = freq.size();
//            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
//                freq.add(new int[]{num, 1});
//            } else {
//                ++freq.get(size - 1)[1];
//            }
//        }
//        dfs(0, target);
//        return ans;
//    }
//
//    public void dfs(int pos, int rest) {
//        if (rest == 0) {
//            ans.add(new ArrayList<Integer>(sequence));
//            return;
//        }
//        if (pos == freq.size() || rest < freq.get(pos)[0]) {
//            return;
//        }
//
//        dfs(pos + 1, rest);
//
//        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
//        for (int i = 1; i <= most; ++i) {
//            sequence.add(freq.get(pos)[0]);
//            dfs(pos + 1, rest - i * freq.get(pos)[0]);
//        }
//        for (int i = 1; i <= most; ++i) {
//            sequence.remove(sequence.size() - 1);
//        }
//    }
//}

// 标准模板写法
// class Solution {
//private:
//    vector<vector<int>> result;
//    vector<int> path;
//    void backtracking(vector<int>& candidates, int target, int sum, int startIndex, vector<bool>& used)
//    {
//        if(sum == target)
//        {
//            result.push_back(path);
//            return;
//        }
//        for(int i = startIndex; i < candidates.size() && sum + candidates[i] <=target; i++)
//        {
//            //used[i - 1] == true ,说明同一树枝candidate[i - 1] 使用过
//            //used[i - 1] == false; 说明同一树层candidate[i - 1] 使用过
//            //对同一树层使用过的元素进行跳过
//            if(i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false)
//            {
//                continue;
//            }
//            sum += candidates[i];
//            path.push_back(candidates[i]);
//            used[i] = true;
//            backtracking(candidates, target, sum, i + 1, used);//和39题的区别，这里是i+1，每个数字在每个组合中只能使用一次
//            used[i] = false;
//            sum -= candidates[i];
//            path.pop_back();
//        }
//    }
//public:
//    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
//        vector<bool> used(candidates.size(), false);
//        path.clear();
//        result.clear();
//        //首先给candidates排序，让其相同的元素都挨在一起。
//        sort(candidates.begin(), candidates.end());
//        backtracking(candidates, target, 0, 0, used);
//        return result;
//    }
//};
