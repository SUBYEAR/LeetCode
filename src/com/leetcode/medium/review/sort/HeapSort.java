/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.sort;

import java.util.Arrays;

/**
 * The type Heap sort.
 *
 * @since 2020-04-21
 */
public class HeapSort {
    /**
     * Sort int [ ].
     *
     * @param arr the arr
     * @return the int [ ]
     */
    public int[] sort(int[] arr) {
        int len = arr.length;
        int[] tmpArr = Arrays.copyOf(arr, len);
        buildMaxHeap(tmpArr, len);

        for (int i = len - 1; i >= 0; i--) {
            swap(tmpArr, 0, i);
            --len;
            heapify(tmpArr, 0, len);
        }
        return tmpArr;
    }

    private void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    /**
     * 调整堆
     *
     * @param arr 待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        // 将temp作为父节点
        int temp = arr[parent];
        // 左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            // 右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * 三向切分的快速排序
     * a[left...lt-1]中的元素都小于v
     * a[gt+1....hi]中的元素都大于v
     * 双轴快排-DualPivotQuicksort 上面提到的算法是从数组中任意挑选一个元素作为基准。因此我们也可以称之为
     * 单轴快速排序法。而双轴快排(DualPivotQuicksort)，顾名思义有两个基准元素pivot1，pivot2，且pivot ≤
     * pivot2，将序列分成三段：x < pivot1、pivot1 ≤ x ≤ pivot2、x > pivot2，然后分别对三段进行递归。
     * a[lt...i-1]中的元素都等于v
     * a[i...gt]中的元素都还未确定,通过下面处理
     * 1. a[i]小于v，将a[lt]和a[i]交换，将lt和i加1
     * 2. a[i]大于v，将a[gt]和a[i]交换，将gt减1
     * 3. a[i]等于v，将i加1
     * 这些操作都会保证数组元素不变且缩小gt-i的值，这样循环才会结束
     */
    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int lt = left;
            int index = left + 1;
            int gt = right;
            int pivotValue = arr[left];
            while (index <= gt) {
                if (arr[index] < pivotValue) {
                    swap(arr, lt++, index++);
                } else if (arr[index] > pivotValue) {
                    swap(arr, index, gt--);
                } else {
                    index++;
                }
            }
            quickSort(arr, left, lt - 1);
            quickSort(arr, gt + 1, right);
        }
    }

    public int lengthOfLIS(int[] nums) { // 最长递增子序列
        int[] top = new int[nums.length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];

            /***** 搜索左侧边界的二分查找 *****/
            // 左边界的二分查找 当target不存在时，得到的索引是恰好比val大的最小元素的索引
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            /*********************************/

            // 没找到合适的牌堆，新建一堆
            if (left == piles) {
                piles++;
            }
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }

    int left_bound(int[] nums, int target) { // 二分查找
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid - 1;
            }
        }
        // 检查出界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }
}
