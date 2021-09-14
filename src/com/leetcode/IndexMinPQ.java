package com.leetcode;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexMinPQ <Key extends Comparable<Key>> {
    private int N;
    private int[] pq; // 索引二叉堆
    private int[] qp; // 逆序如果pq[i] = j, 满足qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        // 可存放范围为[0, maxN]
        keys = (Key[]) new Comparable[maxN + 1];
        // 索引二叉堆,存放范围为[1, maxN]
        pq = new int[maxN + 1];
        // 可存放范围为[0, maxN]
        qp = new int[maxN + 1];
        // 刚开始没有关联任何整数，都设置为-1
        Arrays.fill(qp, -1);
    }

    // 针对是pq中的索引i、j，但是实际引用的是keys中对应的元素
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public boolean contains(int k) {
        // pq_index  0  1  2  3  4  5  6  7  8  9  10  // 最小的元素是在element中下标3的位置
        // pq_value     3  10 6  1  4  8

        // qp_index  0  1  2  3  4  5  6  7  8  9  10  // 下标3的位置是第1小的元素
        // qp_value     4     1  5     3     6     2
        // 值得注意的是qp的分布其实和element是发分布是一样的, 下标0不使用
        // index     0  1  2  3  4  5  6  7  8  9  10
        // element      k     a  n     c     h     b
        return qp[k] != -1;
    }

    public void insert(int k, Key key) {
        if (!contains(k)) {
            N++;
            pq[N] = k;
            qp[k] = N;
            keys[k] = key;
            swim(N);
        }
    }

    public void replace(int k, Key key) {
        keys[k] = key;
        // 由于和k关联的新key可能大于原来的key（此时需要下沉），也有可能小于原来的key（此时需要上浮），为了简化代码，既上浮又下沉，就囊括了这两种可能。
        swim(qp[k]);
        sink(qp[k]);
    }

    // 返回最小元素
    public Key min() {
        return keys[pq[1]];
    }

    // 最小元素的关联整数
    public int minIndex() { // 其实就是element数组中的下标, pq 相当于是keys的离散化
        return pq[1]; // qp作为pq的逆序, qp的索引即为pq的值而pq作为keys数组的离散化其值是keys数组对象的索引
        // 那么qp的值是pq的索引, 也就是表示了元素是数组中的第几小的
    }

    public int delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列已经为空，不能执行删除！");
        }
        int indexOfMin = pq[1];
        // 堆顶和最后一个元素交换
        swap(1, N--);
        sink(1);
        // 最后一个元素置为空
        keys[indexOfMin] = null;
        // 同时关联整数pq[N]在pq中的的索引设置为-1，表示还没有对象与该整数关联
        qp[indexOfMin] = -1;

        return indexOfMin;
    }

    public void delete(int k) {
        if (!contains(k)) {
            throw new NoSuchElementException("没有元素与" + k + "关联！");
        }
        // index为整数k在pq中的位置
        int index = qp[k];

        swap(index, N--);
        // 这里一定要先上浮下沉后再将元素置空，因为swim方法没有N的限制，在没有交换元素的情况下，即删除的就是pq中最后一个元素，如果先置空, 会在greater方法中引发空指针
        // 而sink方法有N的限制，先置空后置空都没有影响，2k <= N会限制它进入循环，避免了空指针
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    public Key keyOf(int k) {
        if (contains(k)) {
            return keys[k];
        }
        // 没有与k关联就返回null
        return null;
    }

    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) { // 左孩子比右孩子大
                j++;
            }

            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }
}
