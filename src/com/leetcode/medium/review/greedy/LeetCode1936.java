package com.leetcode.medium.review.greedy;

/**
 * 给你一个 严格递增 的整数数组 rungs ，用于表示梯子上每一台阶的 高度 。当前你正站在高度为 0 的地板上，并打算爬到最后一个台阶。
 *
 * 另给你一个整数 dist 。每次移动中，你可以到达下一个距离你当前位置（地板或台阶）不超过 dist 高度的台阶。当然，你也可以在任何正 整数 高度处插入尚不存在的新台阶。
 *
 * 返回爬到最后一阶时必须添加到梯子上的 最少 台阶数。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-minimum-number-of-rungs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1936 {
    public int addRungs(int[] rungs, int dist) {
//        int pre = 0;
//        int ans = 0;
//        for (int i = 0; i < rungs.length; i++) {
//            int diff = rungs[i] - pre;
//            if (diff <= dist) {
//                pre = rungs[i];
//                continue;
//            }
//            while (diff > dist) {
//                diff -= dist;
//                ++ans;
//            }
//            pre = rungs[i];
//        }
//        return ans;
        int res = 0;   // 需要增设的梯子数目
        int curr = 0;   // 当前高度
        for (int h: rungs){
            // 遍历数组计算高度差和最少添加数目，并更新当前高度
            int d = h - curr;
            res += (d - 1) / dist;
            curr = h;
        }
        return res;
    }
}
