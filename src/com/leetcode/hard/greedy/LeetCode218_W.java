package com.leetcode.hard.greedy;

import java.util.*;

/**
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 *
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 *
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * heighti 是第 i 座建筑物的高度。
 * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
 * 列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 *
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
 * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 * 提示：
 *
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings 按 lefti 非递减排序
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-skyline-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode218_W {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]); // 高度降序排列
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) { // 收集遍历的数据集
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);
        List<List<Integer>> res = new ArrayList<>();
        int n = buildings.length;
        int index = 0;
        // 如果建筑的左边界left和右边界right包含该坐标，将建筑加入到优先级队列中
        for (int boundary : boundaries) {
            while (index < n && buildings[index][0] <= boundary) { // 满足约束条件boundary的加入队列
                pq.add(new int[] {buildings[index][1], buildings[index][2]});
                index++;
            }

            while (!pq.isEmpty() && pq.peek()[0] <= boundary) { // 右边界比当前左边小是在当前坐标的左边并不包含该坐标
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (res.size() == 0 || res.get(res.size() - 1).get(1) != maxn) { // 关键点为右边缘时建筑物的高度对纵坐标是没有贡献的
                res.add(Arrays.asList(boundary, maxn));
            }
        }
        return res;
    }
}
