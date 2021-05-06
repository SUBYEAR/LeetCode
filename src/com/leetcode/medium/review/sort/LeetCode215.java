/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.sort;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * @since 2020-06-29
 */
public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

// 快速排除的实现
//class Solution {
//    Random random = new Random();
//
//    public int findKthLargest(int[] nums, int k) {
//        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
//    }
//
//    public int quickSelect(int[] a, int l, int r, int index) {
//        int q = randomPartition(a, l, r);
//        if (q == index) {
//            return a[q];
//        } else {
//            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
//        }
//    }
//
//    public int randomPartition(int[] a, int l, int r) {
//        int i = random.nextInt(r - l + 1) + l;
//        swap(a, i, r);
//        return partition(a, l, r);
//    }
//
//    public int partition(int[] a, int l, int r) {
//        int x = a[r], i = l - 1;
//        for (int j = l; j < r; ++j) {
//            if (a[j] <= x) {
//                swap(a, ++i, j);
//            }
//        }
//        swap(a, i + 1, r);
//        return i + 1;
//    }
//
//    public void swap(int[] a, int i, int j) {
//        int temp = a[i];
//        a[i] = a[j];
//        a[j] = temp;
//    }
//}

// 根据 k 的不同，选最大堆和最小堆，目的是让堆中的元素更小
// 思路 1：k 要是更靠近 0 的话，此时 k 是一个较小的数，用最大堆
// 例如在一个有 6 个元素的数组里找第 5 大的元素
// 思路 2：k 要是更靠近 len 的话，用最小堆

// 优先级队列的小顶堆，k大小的小顶堆，然后比较堆顶和当前的num大小就好了，最后留在小顶堆里的就是答案
//     public int findKthLargest(int[] nums, int k) {
//        int len = nums.length;
//        // 使用一个含有 len 个元素的最小堆，默认是最小堆，可以不写 lambda 表达式：(a, b) -> a - b
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>(len, (a, b) -> a - b);
//        for (int i = 0; i < len; i++) {
//            minHeap.add(nums[i]);
//        }
//        for (int i = 0; i < len - k; i++) {
//            minHeap.poll();
//        }
//        return minHeap.peek();
//    }
//
//作者：liweiwei1419
//链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/partitionfen-er-zhi-zhi-you-xian-dui-lie-java-dai-/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。