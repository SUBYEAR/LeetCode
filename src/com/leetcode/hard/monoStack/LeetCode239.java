package com.leetcode.hard.monoStack;

import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!qmax.isEmpty() && nums[qmax.peekLast()] <= nums[i]) { // 单调递减栈
                qmax.pollLast();
            }

            qmax.addLast(i);
            if (qmax.peekFirst() == i - k) {
                qmax.pollFirst();
            }
            if (i >= k - 1) {
                res[index++] = nums[qmax.peekFirst()];
            }
        }
        return res;
    }
}

//        int n = nums.length;
//        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
//            public int compare(int[] pair1, int[] pair2) {
//                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
//            }
//        });
//        for (int i = 0; i < k; ++i) {
//            pq.offer(new int[]{nums[i], i});
//        }
//        int[] ans = new int[n - k + 1];
//        ans[0] = pq.peek()[0];
//        for (int i = k; i < n; ++i) {
//            pq.offer(new int[]{nums[i], i});
//            while (pq.peek()[1] <= i - k) {
//                pq.poll();
//            }
//            ans[i - k + 1] = pq.peek()[0];
//        }
//        return ans;