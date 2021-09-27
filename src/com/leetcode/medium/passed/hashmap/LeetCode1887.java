package com.leetcode.medium.passed.hashmap;

import java.util.Map;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，你的目标是令 nums 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：
 *
 * 找出 nums 中的 最大 值。记这个值为 largest 并取其下标 i （下标从 0 开始计数）。如果有多个元素都是最大值，则取最小的 i 。
 * 找出 nums 中的 下一个最大 值，这个值 严格小于 largest ，记为 nextLargest 。
 * 将 nums[i] 减少到 nextLargest 。
 * 返回使 nums 中的所有元素相等的操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reduction-operations-to-make-the-array-elements-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1887 {
    public int reductionOperations(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        int cur = 0;
        int times = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (times == map.size()) {
                break;
            }
            cur += entry.getValue();
            ans += cur;
            times++;
        }
        return ans;
    }
}
