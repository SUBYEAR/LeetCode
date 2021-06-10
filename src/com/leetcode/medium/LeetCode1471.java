package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个整数数组 arr 和一个整数 k 。
 *
 * 设 m 为数组的中位数，只要满足下述两个前提之一，就可以判定 arr[i] 的值比 arr[j] 的值更强：
 *
 *  |arr[i] - m| > |arr[j] - m|
 *  |arr[i] - m| == |arr[j] - m|，且 arr[i] > arr[j]
 * 请返回由数组中最强的 k 个值组成的列表。答案可以以 任意顺序 返回。
 *
 * 中位数 是一个有序整数列表中处于中间位置的值。形式上，如果列表的长度为 n ，那么中位数就是该有序列表（下标从 0 开始）中位于 ((n - 1) / 2) 的元素。
 *
 * 例如 arr = [6, -3, 7, 2, 11]，n = 5：数组排序后得到 arr = [-3, 2, 6, 7, 11] ，数组的中间位置为 m = ((5 - 1) / 2) = 2 ，中位数 arr[m] 的值为 6 。
 * 例如 arr = [-7, 22, 17, 3]，n = 4：数组排序后得到 arr = [-7, 3, 17, 22] ，数组的中间位置为 m = ((4 - 1) / 2) = 1 ，中位数 arr[m] 的值为 3 。
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4,5], k = 2
 * 输出：[5,1]
 * 解释：中位数为 3，按从强到弱顺序排序后，数组变为 [5,1,4,2,3]。最强的两个元素是 [5, 1]。[1, 5] 也是正确答案。
 * 注意，尽管 |5 - 3| == |1 - 3| ，但是 5 比 1 更强，因为 5 > 1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-k-strongest-values-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1471 {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) / 2];
        Comparator<Integer> comparator = (a, b) -> {
            int disA = Math.abs(a - mid);
            int disB = Math.abs(b - mid);
            if (disA == disB) {
                return b - a;
            }
            return disB - disA;
        };
        int[] sorted = Arrays.stream(arr).boxed().sorted(comparator).mapToInt(i -> i).toArray();
        return Arrays.copyOfRange(sorted, 0, k);
    }

    public int[] getStrongest_twoPointers(int[] arr, int k) {
        int[] target = new int[k];
        int index = 0;
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) / 2];
        int left = 0;
        int right = arr.length  -1;
        while(index != k) {
            int abs1 = Math.abs(arr[left] - mid);
            int abs2 = Math.abs(arr[right] - mid);
            if(abs1 > abs2) {
                target[index++] = arr[left++];
            }else{
                target[index++] = arr[right--];
            }
        }
        return target;
    }
}
