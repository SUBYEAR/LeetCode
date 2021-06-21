package com.leetcode.medium;

import java.util.Arrays;

/**
 * 给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
 *
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值更新为 val
 * int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + nums[left + 1], ..., nums[right]）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode307 {
    // 将数组分割成块，块的长度为 sqrt(n)。然后我们计算每个块的和，并将其存储在辅助存储器 b 中
//    int[] nums;
//    int[] block;
//    int len;
//    public LeetCode307(int[] nums) {
//        int n = nums.length;
//        this.nums = nums;
//        double l = Math.sqrt(n);
//        len = (int) Math.ceil(n / l);
//        block = new int[len];
//        for (int i = 0; i < n; i++) {
//            block[i / len] += nums[i];
//        }
//    }
//
//    public void update(int index, int val) {
//        int dif = val - nums[index];
//        block[index / len] += dif;
//        nums[index] = val;
//    }
//
//    public int sumRange(int left, int right) {
//        int sum = 0;
//        int startBlock = left / len;
//        int endBlock = right / len;
//        if (startBlock == endBlock) {
//            for (int i = left; i <= right; i++) {
//                sum += nums[i];
//            }
//        } else {
//            for (int i = left; i <= (startBlock + 1) * len - 1; i++) {
//                sum += nums[i];
//            }
//            for (int i = startBlock + 1; i <= endBlock - 1; i++) {
//                sum += block[i];
//            }
//            for (int i = endBlock * len; i <= right; i++) {
//                sum += nums[i];
//            }
//        }
//
//        return sum;
//    }

    // 线段树解法更好，用于解决多种范围查询问题，比如在对数时间内从数组中找到最小值、最大值、总和、最大公约数、最小公倍数等
    // 线段树可以分为以下三个步骤：从给定数组构建线段树的预处理步骤。 修改元素时更新线段树。  使用线段树进行区域和检索。
    // 线段树既可以用数组也可以用树来实现。对于数组实现，
    // 如果索引 i 处的元素不是一个叶节点，那么其左子节点和右子节点分别存储在索引为 2i 和 2i+1 的元素处。

    int[] tree;
    int n;
    public LeetCode307(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = nums[j];
        }
        // 左节点的索引是2i,总是偶数
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int l, int r) {
        // 这里采用数组存储线段树的结构其实是一种完全二叉树的数组表示
        l += n;
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) { // 说明在这一层树上最左的边界是一个单独的节点，是一种2i+1类型的right节点
                sum += tree[l];
                l++;
            }
            if ((r % 2) == 0) { // 说明在这一层树上最右的边界是一个单独的节点，是一种2i类型的left节点
                sum += tree[r];
                r--;
            }

            l /= 2;
            r /= 2;
        }

        return sum;
    }
}
