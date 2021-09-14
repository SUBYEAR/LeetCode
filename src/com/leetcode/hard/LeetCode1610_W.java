package com.leetcode.hard;

import java.util.*;


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
