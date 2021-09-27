package com.leetcode.hard.slideWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
 *
 * 最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。
 * 你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
 * 对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
 *
 * 同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
 * 提示：
 *
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 100
 *
 * 返回你能看到的点的最大数目。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-visible-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1610_W {
    final double EPSILON = 1e-8;
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int x = location.get(0), y = location.get(1);
        int same = 0;
        List<Double> v = new ArrayList<>();
        for (List<Integer> p : points) {
            int px = p.get(0), py = p.get(1);
            if (px == x && py == y)
                same++;
            else
                v.add(Math.atan2(py - y, px - x) * 180 / Math.PI);
        }
        Collections.sort(v);
        int m = v.size();
        for (int i = 0; i < m; ++i)
            v.add(v.get(i) + 360);
        int r = 0, hi = 0;
        for (int l = 0; l < m; ++l) {
            while (r + 1 < v.size() && v.get(r + 1) - v.get(l) <= (double)angle + EPSILON)
                r++;
            hi = Math.max(hi, r - l + 1);
        }
        return hi + same;
    }
    // 链接：https://leetcode-cn.com/problems/maximum-number-of-visible-points/solution/ji-jiao-xu-shuang-zhi-zhen-by-lucifer1004/
}
