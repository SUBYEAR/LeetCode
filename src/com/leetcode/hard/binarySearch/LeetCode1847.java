package com.leetcode.hard.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一个酒店里有 n 个房间，这些房间用二维整数数组 rooms 表示，其中 rooms[i] = [roomIdi, sizei] 表示有一个房间
 * 号为 roomIdi 的房间且它的面积为 sizei 。每一个房间号 roomIdi 保证是 独一无二 的。
 *
 * 同时给你 k 个查询，用二维数组 queries 表示，其中 queries[j] = [preferredj, minSizej] 。第 j 个查询
 * 的答案是满足如下条件的房间 id ：
 *
 * 房间的面积 至少 为 minSizej ，且
 * abs(id - preferredj) 的值 最小 ，其中 abs(x) 是 x 的绝对值。
 * 如果差的绝对值有 相等 的，选择 最小 的 id 。如果 没有满足条件的房间 ，答案为 -1 。
 *
 * 请你返回长度为 k 的数组 answer ，其中 answer[j] 为第 j 个查询的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closest-room
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1847 {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        Arrays.sort(rooms, Comparator.comparingInt(o -> o[1]));

        int k = queries.length;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            int minSize = queries[i][1];
            int preferred = queries[i][0];
            int idx = leftBound(rooms, minSize);
            if (idx == n) {
                res[i] = -1;
                continue;
            }
            int dif = Integer.MAX_VALUE;
            int minId = idx;
            for (int j = idx; j < n; j++) {
                if (Math.abs(rooms[j][0] - preferred) == 0) {
                    minId = rooms[j][0];
                    break;
                }
                if (Math.abs(rooms[j][0] - preferred) < dif) {
                    dif = Math.abs(rooms[j][0] - preferred);
                    minId = rooms[j][0];
                } else if (Math.abs(rooms[j][0] - preferred) == dif) {
                    minId = Math.min(minId, rooms[j][0]);
                }
            }
            res[i] = minId;
        }
        return res;
    }

    int leftBound(int[][] rooms, int minSize) {
        int left = 0, right = rooms.length;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (rooms[mid][1] == minSize) {
                right = mid;
            } else if (minSize > rooms[mid][1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
