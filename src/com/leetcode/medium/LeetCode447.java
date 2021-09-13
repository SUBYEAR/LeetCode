package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode447 {
    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        int ans = 0;

//        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.clear();
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                int dis = (int) (Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
//                List<Integer> list = map.getOrDefault(dis, new ArrayList<>());
//                list.add(j);
                map.put(dis, map.getOrDefault(dis,0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int cnt = entry.getValue();
                if (cnt < 2) {
                    continue;
                }
//                System.out.print("distance:" + entry.getKey());
//                System.out.println(" ," + i + list);
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }
}
