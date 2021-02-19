package com.leetcode.hard;

import java.util.ArrayList;

/**
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 *
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 *
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-19
 */
public class LeetCode765 {
    public int minSwapsCouples(int[] row) {
        int N = row.length / 2;
        ArrayList<int[]> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (row[2 * i] > row[2 * i + 1]) {
                int temp = row[2 * i];
                row[2 * i] = row[2 * i + 1];
                row[2 * i + 1] = temp;
            }
            if (row[2 * i] % 2 == 0 && row[2 * i + 1] == (row[2 * i] + 1)) {
                continue;
            }
            nodes.add(new int[] {row[2 * i], row[2 * i + 1]});
        }
        int res = 0;
        int size = nodes.size();
        UF u = new UF(size);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (isConnected(nodes.get(i), nodes.get(j))) {
                    if (u.unite(i, j)) {
                        ++res;
                    }
                }
            }
        }

        return res;
    }

    boolean isConnected(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                if ((b[0] == a[i] + 1) || (b[1] == a[i] + 1)) {
                    return true;
                }
            } else {
                if ((b[0] == a[i] - 1) || (b[1] == a[i] - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private class UF {
        private int count;

        private int[] parent;

        private int[] size;

        private int n;

        public UF(int n) {
            this.n = n;
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public boolean unite(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }

            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            count--;
            return true;
        }
    }
}
