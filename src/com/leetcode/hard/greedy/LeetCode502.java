package com.leetcode.hard.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
 * 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 *
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 *
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 *
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 *
 * 答案保证在 32 位有符号整数范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ipo
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = capital[i];
            data[i][1] = profits[i];
        }
        Arrays.sort(data,(o1, o2) -> o1[0] - o2[0]); // 启动资本从小到大排序
        if (data[0][0] > w) { // 最小的启动资本比w大
            return w;
        }

        // 堆顶带来最大增益的项目
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o2[1] == o1[1]) return o1[0] - o2[0];
            return o2[1] - o1[1];
        });
        int count = 0;
        int index = 0;
        while (count < Math.min(k, n)) {
            while (index < n && data[index][0] <= w) { // 把符合条件的启动资本都加入到队列
                pq.add(data[index]);
                index++;
            }
            if (!pq.isEmpty()) {
                int[] poll = pq.poll();
                w = w + poll[1];
                count++;
            }
            if (pq.isEmpty() && ((index == n) || (data[index][0] > w))) {
                break;
            }
        }
        return w;
    }
}
