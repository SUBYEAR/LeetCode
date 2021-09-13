package com.leetcode.medium;

/**
 * 假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：
 *
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 *
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 *
 * 注意：本题相对原题稍作改动
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rank-from-stream-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview1010 {
    BitTree bitTree;
    public Interview1010() {
        bitTree = new BitTree();
    }

    public void track(int x) {
        bitTree.update(x + 1);
    }

    public int getRankOfNumber(int x) {
        return bitTree.query(x + 1);
    }

    private class BitTree {
        int[] tree;
        public BitTree() {
            tree = new int[50_002];
        }

        private int lowbt(int x) {
            return x & (-x);
        }

        public int query(int x) {
            int ans = 0;
            while (x > 0) {
                ans += tree[x];
                x -= lowbt(x);
            }
            return ans;
        }

        public void update(int x) {
            while (x < tree.length) {
                tree[x]++;
                x += lowbt(x);
            }
        }
    }
}
