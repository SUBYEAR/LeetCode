package com.leetcode;

public class SegmentTree<E> {
    private E[] tree; // tree 的索引使用node
    private E[] data; // data的索引 使用start end
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger merger) {
        int len = arr.length;
        this.merger = merger;
        data = (E[]) new Object[len];
        tree = (E[]) new Object[4 * len]; // 大小为4n保证够用
        for (int i = 0; i < len; i++) {
            data[i] = arr[i];
        }
        // 构建线段树
        buildSegmentTree(0 , 0, len - 1);
    }


    public int getSize() {
        return data.length;
    }

    public E get(int idx) {
        if (idx < 0 || idx >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[idx];
    }

    private int leftChild(int node) {
        return 2 * node + 1;
    }

    private int rightChild(int ndoe) {
        return 2 * ndoe + 2;
    }

    void buildSegmentTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = data[start];
            return;
        }

        int mid = (end - start) / 2 + start;
        int leftNode = leftChild(node);
        int rightNode = rightChild(node);

        buildSegmentTree(leftNode, start, mid);
        buildSegmentTree(rightNode, mid + 1, end);
        tree[node] = merger.merge(tree[leftNode], tree[rightNode]);
    }

    public E query(int left, int right) {
        if (left < 0 || left >= data.length || right < 0 || right >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return query(0, 0, data.length - 1, left, right);
    }

    private E query(int node, int start, int end, int left, int right) {
        if (start == left && end == right) {
            return tree[node];
        }

        int mid = (end - start) / 2 + start;
        int leftNode = leftChild(node);
        int rightNode = rightChild(node);
        if (left > mid) { // 如果左边界大于中间节点，则查询右区间
            return query(rightNode, mid + 1, end, left, right);
        }
        if (right <= mid) { // 如果右边界小于等于中间节点，则查询左区间
            return query(leftNode, start, mid, left, right);
        }
        // // 如果上述两种情况都不是，则根据中间节点，拆分为两个查询区间
        E leftRes = query(leftNode, start, mid, left, mid);
        E rightRes = query(rightNode, mid + 1, end, mid + 1, right);
        return merger.merge(leftRes, rightRes);
    }

    public void update(int idx, E val) {
        if (idx < 0 || idx >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        update(0, 0, data.length - 1, idx, val);
    }

    private void update(int node, int start, int end, int idx, E val) {
        if (start == end) {
            tree[node] = val;
        }

        int mid = (end - start) / 2 + start;
        int leftNode = leftChild(node);
        int rightNode = rightChild(node);

        if (idx > mid) {
            update(rightNode, mid + 1, end, idx, val);
        } else {
            update(leftNode, start, mid, idx, val);
        }
        tree[node] = merger.merge(tree[leftNode], tree[rightNode]);
    }

    public String toSting() {
        StringBuffer res = new StringBuffer();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else res.append("null");
            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
