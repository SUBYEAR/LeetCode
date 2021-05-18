package com.leetcode.medium.review.monostack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 *
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-ramp
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-23
 */
public class LeetCode962 { //单调递减栈，遍历到当前元素比栈顶表示的元素大不断出栈更新结果
    public int maxWidthRamp(int[] A) {
        int len = A.length;
        Deque<Integer> s = new LinkedList<>();
        s.push(0);
        for (int i = 1; i < len; i++) {
            if (A[i] < A[s.peek()]) {
                s.push(i);
            }
        }

        int res = 0;
        for (int i = len - 1; i >= 0; i--) {
            while (!s.isEmpty() && A[i] >= A[s.peek()]) {
                res = Math.max(res, i - s.peek());
                s.pop();
            }
        }
        return res;
    }
}

// 对于每一个形如 A[i] = v 的元素，我们将其索引 i 按照对应值 v 排序之后的顺序写下。
// 例如， A[0] = 7, A[1] = 2, A[2] = 5, A[3] = 4，我们应该这样顺序写下索引值 i=1, i=3, i=2, i=0。
//
// 然后，当我们写下一个索引 i 的时候，我们可以得到候选的宽度答案
// i - min(indexes_previously_written) （如果这个值是正数的话）。 我们可以用一个变量 m 记录已经写下的最小索引。
//         int N = A.length;
//         Integer[] B = new Integer[N]; // 类似索引优先队列
//         for (int i = 0; i < N; ++i)
//             B[i] = i;
//
//         Arrays.sort(B, (i, j) -> ((Integer) A[i]).compareTo(A[j]));
//
//         int ans = 0;
//         int m = N;
//         for (int i: B) {
//             ans = Math.max(ans, i - m); // 最小值肯定不是坡度的结尾
//             m = Math.min(m, i);
//         }
//
//         return ans;
