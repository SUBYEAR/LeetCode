package com.leetcode.medium.review.backtrack;

import java.util.*;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode40 {
//    List<List<Integer>> res = new LinkedList<>();
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        backtrack(candidates, 0, target, new LinkedList<>());
//        return res;
//
//    }
//
//    void backtrack(int[] arr, int start, int target, List<Integer> track) {
//        if (target == 0) {
//            if (res.contains(track)) {
//                return;
//            }
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        if (start == arr.length || arr[start] > target || target < 0) {
//            return;
//        }
//
//        track.add(arr[start]);
//        backtrack(arr, start + 1, target - arr[start], track);
//        track.remove(track.size() - 1);
//        backtrack(arr, start + 1, target, track);
//    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 关键步骤
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(candidates, len, 0, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param len        冗余变量
     * @param begin      从候选数组的 begin 位置开始搜索
     * @param target     表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
     * @param path       从根结点到叶子结点的路径
     * @param res
     */
    private void dfs(int[] candidates, int len, int begin, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = begin; i < len; i++) {
            // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) {
                break;
            }

            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (!set.add(candidates[i])) {
                continue;
            }

            path.addLast(candidates[i]);
            // 调试语句 ①
            // System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, len, i + 1, target - candidates[i], path, res);

            path.removeLast();
            // 调试语句 ②
            // System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
        }

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
