package com.leetcode.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 *
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview1714 {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            q.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < q.peek()) {
                q.poll();
                q.offer(arr[i]);
            }
        }

        int[] res = Arrays.stream(q.toArray(q.toArray(new Integer[0]))).mapToInt(Integer::intValue).toArray();
        return res;
    }
}
