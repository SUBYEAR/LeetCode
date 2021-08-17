package com.leetcode.hard;

import javafx.geometry.Point3D;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 */
public class LeetCode149 {
    public int maxPoints(int[][] points) {
        int res = 1;
        Map<Point3D, Set<Point>> map = new HashMap<>();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // 直线的表示是Ax + By + C = 0;
                // 对于任意两点(X1,Y1)、(X2,Y2)，都有 A = Y2 - Y1 B = X1 - X2 C = X2Y1 - X1Y2
                int A = points[j][1] - points[i][1];
                int B = points[i][0] - points[j][0];
                int C = points[j][0] * points[i][1] - points[i][0] * points[j][1];
                int gcd = getGcd(getGcd(A, B), C);
                A /= gcd;
                B /= gcd;
                C /= gcd;
                Point3D point3D = new Point3D(A, B, C);// int hash = hash(A, B, C);
                Set<Point> set = map.getOrDefault(point3D, new HashSet<>());
                set.add(new Point(points[i][0], points[i][1]));
                set.add(new Point(points[j][0], points[j][1]));
                map.put(point3D, set);
                if (set.size() > res) {
                    res = set.size();
                }
            }
        }
        return res;
    }

    private int getGcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private int hash(int a, int b, int c) { // 这个hash 函数不太好
        a = (a ^ (a >>> 16) & 0xffff) << 20;
        b = (b ^ (b >>> 16) & 0xffff) << 10;
        c = (c ^ (c >>> 16) & 0xffff);
        return a | b | c;
    }

    // 当前枚举到点 i，如果直线同时经过另外两个不同的点 j 和 k，那么可以发现点 i 和点 j 所连直线的斜率恰等于点 i 和点 k 所连直线的斜率。
    // 于是我们可以统计其他所有点与点 i 所连直线的斜率，出现次数最多的斜率即为经过点数最多的直线的斜率，其经过的点数为该斜率出现的次数加一
    public int maxPoints_officials(int[][] points) {
        int n = points.length;
        if (n <= 2) { // 点的总数量小于等于 2 的情况下，我们总可以用一条直线将所有点串联
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            // 当我们枚举到点 i（假设编号从 0 开始）时，我们至多只能找到 n-i 个点共线。假设此前找到的共线的点的数量的最大值为 k，
            // 如果有 k≥n−i，那么此时我们即可停止枚举，因为不可能再找到更大的答案了。
            if (ret >= n - i || ret > n / 2) { // 找到一条直线经过了图中超过半数的点时，我们即可以确定该直线即为经过最多点的直线
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // 用斜率标识而不是使用系数A B C
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1); //（点 i 自身也要被统计）
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
