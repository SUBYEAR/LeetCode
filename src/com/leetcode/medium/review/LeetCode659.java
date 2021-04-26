package com.leetcode.medium.review;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，
 * 其中每个子序列都由连续整数组成。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode659 {
    // 当 x 在数组中时，如果存在一个子序列以 x−1 结尾，长度为 k，则可以将 x 加入该子序列中，得到长度为 k+1 的子序列。
    // 如果不存在以 x−1 结尾的子序列，则必须新建一个只包含 x 的子序列，长度为 1。
    // 当 x 在数组中时，如果存在多个子序列以 x−1 结尾，应该将 x 加入其中的哪一个子序列？由于题目要求每个子序列的长度至少为 3，
    // 显然应该让最短的子序列尽可能长，因此应该将 xx 加入其中最短的子序列。基于上述分析，可以使用哈希表和最小堆进行实现。
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new PriorityQueue<>());
            }
            if (map.containsKey(num - 1)) {
                int preLength = map.get(num - 1).poll();
                if (map.get(num - 1).isEmpty()) {
                    map.remove(num - 1);
                }
                map.get(num).offer(preLength + 1);
            } else {
                map.get(num).offer(1);
            }
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> e : entrySet) {
            PriorityQueue<Integer> queue = e.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }
}
