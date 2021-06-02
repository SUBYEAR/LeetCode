package com.leetcode.medium.review;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
 * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode456_W {
    // nums[i] < nums[k] < nums[j] 分别对应序号1，2，3
    // 我们用单调栈维护所有可以作为 2 的候选元素。初始时，单调栈中只有唯一的元素a[n−1]。
    // 我们还需要使用一个变量 max_k 记录所有可以真正作为 2 的元素的最大值；随后我们从 n-2 开始从右到左枚举元素 a[i]：
    //首先我们判断 a[i] 是否可以作为 1。如果 a[i]<max_k，那么它就可以作为 1，我们就找到了一组满足 132 模式的三元组；
    //随后我们判断 a[i]是否可以作为 3，以此找出哪些可以真正作为 2 的元素。我们将 a[i] 不断地与单调栈栈顶的元素进行比较，
    // 如果 a[i]较大，那么栈顶元素可以真正作为 2，将其弹出并更新max_k；
    //最后我们将 a[i]作为 2 的候选元素放入单调栈中。这里可以进行一个优化，即如果a[i]≤max_k，那么我们也没有必要将a[i] 放入栈中，
    // 因为即使它在未来被弹出，也不会将max_k 更新为更大的值
    /*
     * 枚举到 1：满足 nums[i] < k，说明对于 i 而言，后面有一个比其大的元素（满足 i < k 的条件）
     * ，同时这个 k 的来源又是因为维护「单调递减」而弹出导致被更新的（满足 i 和 k 之间，有j元素使得比 k 要大）
     */
    public boolean find132pattern_s(int[] nums) { // 单调栈解法
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }

        return false;
    }

    public boolean find132pattern(int[] nums) { // 枚举最大的值
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        int leftMin = nums[0];
        TreeMap<Integer, Integer> rightAll = new TreeMap<>();
        for (int k = 2; k < len; k++) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < len - 1; j++) {
            if (leftMin < nums[j]) {
                Integer next = rightAll.ceilingKey(leftMin + 1); // 找比leftMin大的最小值
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            leftMin = Math.min(leftMin, nums[j]);
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }
        return false; 
    }
}
