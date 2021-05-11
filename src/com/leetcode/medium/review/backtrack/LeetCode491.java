package com.leetcode.medium.review.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是 2 。
 * <p></p>
 * 提示：
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class LeetCode491 {
    ArrayList<Integer> temp = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        helper(nums, Integer.MIN_VALUE, 0);
        return res;
    }

    void helper(int[] nums, int last, int cur) { // last 表示上一个被选中的元素
        if (cur == nums.length) {
            if (temp.size() > 1) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            helper(nums, nums[cur], cur + 1);
            temp.remove(temp.size() - 1);
        }

        if (nums[cur] != last) { // 不选的时候要去重
            helper(nums, last, cur + 1);
        }
    }
}

//     //存放结果
//    List<List<Integer>> result = new ArrayList<>();
//    //暂存结果
//    List<Integer> path = new ArrayList<>();
//
//    public List<List<Integer>> findSubsequences(int[] nums) {
//        backTrack(nums, 0);
//        return result;
//    }
//
//    private void backTrack(int[] nums, int startIndex) {
//        if(path.size() > 1){
//            result.add(new ArrayList<>(path));//注意这⾥不要加return，因为要取树上的所有节点
//        }
//        HashSet<Integer> uset = new HashSet<>();//录本层元素是否重复使⽤，新的⼀层uset都会重新定义（清空）
//        for (int i = startIndex; i < nums.length; i++) {
//            //!path.isEmpty()&&nums[i]<path.get(path.size()-1)) : 保证子序列是递增的
//            //!uset.add(nums[i]) ：保证在同一层不会重复使用相同数字
//            if ((!path.isEmpty()&&nums[i]<path.get(path.size()-1))||!uset.add(nums[i])) {
//                continue;
//            }
//            path.add(nums[i]);
//            backTrack(nums, i + 1);
//            path.remove(path.size() - 1);
//        }
//    }
//
//作者：carlsun-2
//链接：https://leetcode-cn.com/problems/increasing-subsequences/solution/491-di-zeng-zi-xu-lie-shen-sou-hui-su-xiang-jie-by/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

// class Solution {
//    // 定义全局变量保存结果
//    List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> findSubsequences(int[] nums) {
//        // idx 初始化为 -1，开始 dfs 搜索。
//        dfs(nums, -1, new ArrayList<>());
//        return res;
//    }
//
//    private void dfs(int[] nums, int idx, List<Integer> curList) {
//        // 只要当前的递增序列长度大于 1，就加入到结果 res 中，然后继续搜索递增序列的下一个值。
//        if (curList.size() > 1) {
//            res.add(new ArrayList<>(curList));
//        }
//
//        // 在 [idx + 1, nums.length - 1] 范围内遍历搜索递增序列的下一个值。
//        // 借助 set 对 [idx + 1, nums.length - 1] 范围内的数去重。
//        Set<Integer> set = new HashSet<>();
//        for (int i = idx + 1; i < nums.length; i++) {
//            // 1. 如果 set 中已经有与 nums[i] 相同的值了，说明加上 nums[i] 后的所有可能的递增序列之前已经被搜过一遍了，因此停止继续搜索。
//            if (set.contains(nums[i])) {
//                continue;
//            }
//            set.add(nums[i]);
//            // 2. 如果 nums[i] >= nums[idx] 的话，说明出现了新的递增序列，因此继续 dfs 搜索（因为 curList 在这里是复用的，因此别忘了 remove 哦）
//            if (idx == -1 || nums[i] >= nums[idx]) { // 从-1开始方便和上一个加入到path中的元素比较
//                curList.add(nums[i]);
//                dfs(nums, i, curList);
//                curList.remove(curList.size() - 1);
//            }
//        }
//    }
//}
//
//
//作者：sweetiee
//链接：https://leetcode-cn.com/problems/increasing-subsequences/solution/jin-tian-wo-you-shuang-ruo-zhuo-neng-miao-dong-la-/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。