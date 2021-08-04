package com.leetcode.hard.segmentTree;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class LeetCode327_W {
    // 设前缀和数组为preSum，则问题等价于求所有的的下标对(i, j)满足pre[j]-pre[i]在范围[low, high]中
    public int countRangeSum(int[] nums, int lower, int upper) {
        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            long[] sorted = new long[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = sum[p1++];
                    } else {
                        sorted[p++] = sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }

    // 线段树
    // 设前缀和数组为preSum，则问题等价于求所有的的下标对(i, j)满足pre[j]-pre[i]在范围[low, high]中
    // 对于每个下标j,以j为右端点的下标对的数量，等于数组pre[0,...j-1]中的所有整数，出现在区间(preSum[j]−upper,preSum[j]−lower)的次数
    public int countRangeSum_st(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        SegNode root = build(0, values.size() - 1);
        int ret = 0;
        for (long x : preSum) {
            int left = values.get(x - upper), right = values.get(x - lower);
            ret += count(root, left, right);
            insert(root, values.get(x));
        }
        return ret;
    }

    public SegNode build(int left, int right) {
        SegNode node = new SegNode(left, right);
        if (left == right) {
            return node;
        }
        int mid = (left + right) / 2;
        node.lchild = build(left, mid);
        node.rchild = build(mid + 1, right);
        return node;
    }

    public int count(SegNode root, int left, int right) {
        if (left > root.hi || right < root.lo) {
            return 0;
        }
        if (left <= root.lo && root.hi <= right) {
            return root.add;
        }
        return count(root.lchild, left, right) + count(root.rchild, left, right);
    }

    public void insert(SegNode root, int val) {
        root.add++;
        if (root.lo == root.hi) {
            return;
        }
        int mid = (root.lo + root.hi) / 2;
        if (val <= mid) {
            insert(root.lchild, val);
        } else {
            insert(root.rchild, val);
        }
    }

    class SegNode {
        int lo, hi, add;
        SegNode lchild, rchild;

        public SegNode(int left, int right) {
            lo = left;
            hi = right;
            add = 0;
            lchild = null;
            rchild = null;
        }
    }

    public int countRangeSum_overTime(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] preSum = new long[len + 1]; // nums 中 [0,...,i)的和
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        TreeMap<Long, Integer> freq = new TreeMap<>();
        for (int i = 0; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                long sum = preSum[j] - preSum[i];
                freq.put(sum, freq.getOrDefault(sum, 0) + 1);
            }
        }

        Long high = freq.floorKey((long) upper); // 小于等于key的最大值
        Long low = freq.ceilingKey((long) lower); // 大于等于key的最小值
        if (high == null || low == null) {
            return 0;
        }

        int cnt = freq.entrySet().stream()
                .filter(entry -> entry.getKey() >= lower)
                .filter(entry -> entry.getKey() <= high)
                .map(Map.Entry::getValue).reduce(Integer::sum).orElse(0);

        return cnt;
    }

    // 如果收集所有的区间和再用左右边界的二分查找则内存超限制
    private int leftBound(List<Long> arr, int target) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (right + left) / 2;
            if (target > arr.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int rightBound(List<Long> arr, int target) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (right + left) / 2;
            if (target < arr.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
