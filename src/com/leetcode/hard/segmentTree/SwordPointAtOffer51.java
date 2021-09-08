package com.leetcode.hard.segmentTree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwordPointAtOffer51 {
    public int reversePairs(int[] nums) {
        int len = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        BitTree bitTree = new BitTree(set.size());
        Map<Integer, Integer> map = new HashMap<>();
        int index = 1;
        for (int num : set) {
            map.put(num, index);
            index++;
        }
        int ans = 0;
        for (int i = 0; i < len; i++) { // 正难则反
            int idx = map.get(nums[i]);
            bitTree.update(idx);
            ans += (i + 1 - bitTree.preSum(idx)); // 使用已经统计了的总数 - 统计比当前小的个数则是逆序对
        }
        return ans;
    }

    private static class BitTree {
        int[] tree;
        public BitTree(int n) {
            tree = new int[n + 1];
        }

        public void update(int idx) {
            while (idx < tree.length) {
                tree[idx]++;
                idx += lowbit(idx);
            }
        }

        public int preSum(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= lowbit(idx);
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }
}