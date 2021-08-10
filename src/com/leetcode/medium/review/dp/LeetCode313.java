package com.leetcode.medium.review.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 *
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 *
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * 提示：
 *
 * 1 <= n <= 106
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode313 {
    // 官解使用了最小堆
    public int nthSuperUglyNumber_heap(int n, int[] primes) {
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int prime : primes) {
                long next = curr * prime;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }


    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        int len = primes.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1;
        dp[1] = primes[0];
        for (int i = 2; i < n; i++) {
            int target = dp[i - 1];
            for (int j = 0; j < primes.length; j++) {
                int base = rightBound(dp, i, target / primes[j]);
                dp[i] = Math.min(dp[i], dp[base] * primes[j]);
            }
            int bound = rightBound(primes, len, target);
            if (bound < len) {
                dp[i] = Math.min(primes[bound], dp[i]);
            }
        }
        return dp[n - 1];
    }

    private int rightBound(int[] arr, int end, int target) {
        int l = 0, r = end;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target == arr[mid]) {
                l = mid + 1;
            } else if (target < arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
