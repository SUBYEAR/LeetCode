package com.leetcode.medium.review.unionfind;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode947 {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    uf.unite(i, j);
                }
            }
        }
        return n - uf.getCount();
    }

    private class UF {
        int[] size;
        int[] parent;
        int count;
        public UF(int n) {
            count = n;
            size = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int getCount() {
            return count;
        }

        public int findRoot(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        void unite(int x, int y) {
            x = findRoot(x);
            y = findRoot(y);
            if (x == y) {
                return;
            }

            if (size[x] < size[y]) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            parent[y] = x;
            size[x] += size[y];
            count--;
        }
    }
}
