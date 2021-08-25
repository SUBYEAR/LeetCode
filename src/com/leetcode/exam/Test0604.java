package com.leetcode.exam;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Test0604 {
    // #Title_2
    // 某地铁站有一个闸机同一时刻只允许一名乘客进出站一个乘客通过闸集需要1秒钟.如果同一时刻一个乘客要进站另一个乘客要出站按如下规则进出站
    // 如果上一秒闸机没有被使用(即使更早使用过)那么进站的乘客优先通过
    // 如果上一秒闸机有乘客进站那么进站的乘客优先
    // 如果上一秒闸机有乘客出站那么出战的乘客优先
    // 现有一群乘客要通过这个闸机乘客到达的时间记录于数组arrTime中(升序可能有重复值)下标表示乘客的编号;乘客的进出站方向记录于direction中
    // 0表示出站,1表示进站,按乘客的编号返回每个乘客实际通过闸机的时刻
    private int[] handleOnePerson(PriorityQueue<Integer>[] pq, int first, int count, int[] result, int order) {
        if (!pq[1].isEmpty()) {
            result[pq[first].poll()] = order;
            count++;
            return new int[] {first, count};
        }
        int second = 1 - first;
        if (!pq[second].isEmpty()) {
            result[pq[0].poll()] = order;
            count++;
            return new int[] {second, count};
        }
        return new int[] {-1, count};
    }

    // 思路:采用两个队列队列0表示出战队列1表示进站.进站条件:上一秒没有使用或者上一秒有乘客进站
    // 出战条件上一秒有乘客出战
    // 定义一个变量flag: -1表示上一秒没有被使用;0表示上一秒有乘客出战;1表示上一秒有乘客进站.由于每一时刻只能有一个人出战进站,从时刻0开始遍历
    // 判断每一时刻闸机是有人进站, 出战或者未被使用. 知道所有乘客遍历结束为止,每遍历一个时刻更新flag和进出站队列
    public int[] getTimes(int[] arrTime, int[] direction) {
        int len  = arrTime.length;
        int[] result = new int[len];
        PriorityQueue<Integer>[] pq = new PriorityQueue[2];
        for (int i = 0; i < 2; i++) {
            pq[i] = new PriorityQueue<>();
        }

        int flag = -1;
        int maxTime = (int) 1e9; // 最大时长是1e9
        int j = 0;
        int count = 0;
        for (int i = 0; i < maxTime; i++) { // 遍历每一时刻
            if (count == len) {
                break;
            }

            while (j < len && arrTime[j] <= i) {
                pq[direction[j]].add(j);
                j++;
            }
            if (flag == -1 || flag == 1) {
                int[] ret = handleOnePerson(pq, 1, count, result, i);
                flag = ret[0];
                count = ret[1];
            } else if (flag == 0) {
                int[] ret = handleOnePerson(pq, 0, count, result, i);
                flag = ret[0];
                count = ret[1];
            }
        }
        return result;
    }

    // #Title_3 有一条笔直的路,有俩个数组一个表示包裹的位置一个表示快递员的位置每一时刻快递员只能往左或者往右一格,各个快递员可以并行工作,
    // 求快递员获取所有包裹的最短时间
    // 示例快递员位置[2,8,7],包裹位置[1,3,11,7] 输出3
    public int getShortestTime(int[] persons, int[] positions) {
        // 指定时间下考虑是否可以把所有快递都收走:对于每一个快递员有两种出发方向,先向左走(后面可以选择拐回右边)和先向右走(后面可以选择拐回左边)
        // 针对最左边的快递员位于他左边的快递一定是他负责.那么可以计算他向俩个方向出发的情况下,这个过程他最右可以走多远(向右走的越远可以拿走右边
        // 更多的快递,为右边的快递员节省时间.注意他一定要收走位于他左边的快递).然后把最左的快递员和他拿走的快递从系统中去掉,那么原本左边第二个
        // 快递员就相当于最左的快递员了,类似处理下取即可
        // 失败条件:如果给定时间下"最左的快递员"拿不到"位于他左边的快递",那么给定时间下一定无法完成

        Arrays.sort(persons);
        Arrays.sort(positions);

        int l = 0, r = (int )2e9;
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(persons, positions, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private boolean check(int[] persons, int[] positions, int mid) {
        int idx = 0;
        for (int p : persons) {
            if (idx == positions.length) { // 快递都被收走了,后面的快递员直接下班
                return true;
            }
            int left = Math.min(positions[idx], p);

            if (mid < p - left) { // mid时间下快递员没有走到最左边的位置
                return false;
            }

            // 先向右和先向左两种方案中可以达到的最右的位置
            int right = Math.max(p + (mid - (p - left)) / 2, left + mid - (p - left));

            while (idx < positions.length && positions[idx] <= right) {
                ++idx; // 把right位置及左边的快递都收走
            }
        }
        return idx == positions.length;

    }
}
