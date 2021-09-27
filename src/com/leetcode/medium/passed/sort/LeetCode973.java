package com.leetcode.medium.passed.sort;

import java.util.Arrays;

/**
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode973 {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (o1, o2) -> {
            int dis1 = (int)(Math.pow(o1[0], 2) + Math.pow(o1[1], 2));
            int dis2 = (int)(Math.pow(o2[0], 2) + Math.pow(o2[1], 2));
            return dis1 - dis2;
        });

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i][0] = points[i][0];
            res[i][1] = points[i][1];
        }
        return res;
    }
}
