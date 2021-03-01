package com.leetcode;

import java.util.Arrays;

// 并查集模板
public class UF {
    int n;
    int[] parent;
    int[] size;
    int count;

    public UF(int n) {
        this.n = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        size = new int[n];
        Arrays.fill(size, 1);
        count = n;
    }

    public int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public boolean unite(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }

        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --count;
        return true;
    }

    public int getSize(int x) {
        return size[x];
    }
}
