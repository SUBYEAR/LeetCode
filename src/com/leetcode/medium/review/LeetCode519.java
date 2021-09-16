package com.leetcode.medium.review;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 题中给出一个 n_rows 行 n_cols 列的二维矩阵，且所有值被初始化为 0。要求编写一个 flip 函数，均匀随机的将矩阵中的 0 变为 1，
 * 并返回该值的位置下标 [row_id,col_id]；同样编写一个 reset 函数，将所有的值都重新置为 0。尽量最少调用随机函数 Math.random()，并且优化时间和空间复杂度。
 *
 * 注意:
 *
 * 1 <= n_rows, n_cols <= 10000
 * 0 <= row.id < n_rows 并且 0 <= col.id < n_cols
 * 当矩阵中没有值为 0 时，不可以调用 flip 函数
 * 调用 flip 和 reset 函数的次数加起来不会超过 1000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-flip-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode519 {
    // 解法通过但是不完美没有做到尽量少的调用random
    Map<Integer, Integer> V = new HashMap<>();
    int nr, nc, rem;
    Random rand = new Random();

    // 每次反转后将反转的1保存在映射后的数组的后半部分, 维护好这样的前半部分是0后半部分是1的数组
    // 当我们进行下一次翻转操作时，我们只需要在 [0, k) 这个区间生成随机数 x，并将 V[x] 映射到的位置进行翻转即可。
    public LeetCode519(int n_rows, int n_cols) {
        nr = n_rows;
        nc = n_cols;
        rem = nr * nc;
    }

    public int[] flip() {
        int r = rand.nextInt(rem--);
        int x = V.getOrDefault(r, r);
        V.put(r, V.getOrDefault(rem, rem));
        return new int[]{x / nc, x % nc};
    }

    public void reset() {
        V.clear();
        rem = nr * nc;
    }

}
