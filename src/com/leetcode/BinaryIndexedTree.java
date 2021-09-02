package com.leetcode;

// https://zhuanlan.zhihu.com/p/25185969
// 可以延伸到求区间和/单点查询的情况，处理是运用了差分的思想. 比如将区间[3, 6]加5，然后查询位置4上的值
// 那么在区间加的时候可以将L左边界3上的值加5, R右边界的下一个位置7上减5
// 然后查询x位置上的值就可以之间用query函数了
public class BinaryIndexedTree {
    private int[] tree;
    // 树状数组的下标位置i管理的范围是[i - lowbit(x) + 1, i], 其个数正好是2^k个,k是下标i最右边0的个数
    public BinaryIndexedTree(int[] nums) {
        tree = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
    }

//    public BinaryIndexedTree(int[] nums) {
//        tree = new int[nums.length + 1]; // 树状数组的下标从1开始
//        for (int i = 0; i < nums.length; i++) {
//            tree[i + 1] = nums[i];
//        }
//
//        for (int i = 1; i <tree.length; i++) {
//            int j = i + lowbit(i); // 求父节点
//            if (j < tree.length) {
//                tree[j] += tree[i];
//            }
//        }
//    }

    public void update(int idx, int val) { // udpate是指对索引index上的指加或者减少一个指,不是指将位置上的值变为新的值
        idx += 1;
        if (idx < 0 || idx >= tree.length) {
            return;
        }

        while (idx < tree.length) {
            tree[idx] += val;
            idx += lowbit(idx);
        }
    }

    public int prefixSum(int idx) {
        idx += 1;
        if (idx < 0 || idx >= tree.length) {
            throw new RuntimeException();
        }
        int ans = 0;
        while (idx > 0) {
            ans += tree[idx];
            idx -= lowbit(idx);
        }
        return ans;
    }

    public int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}

// 树状数组的思想还可以延申到二维
//void update(int x, int y, int val) {
//    for (int i = x; i <= n; i+=lowbit(x)) {
//        for (int j = y; j <= n; j+=lowbit(y)) {
//            tree[x][y] += val;
//        }
//    }
//}