package com.leetcode.hard.segmentTree;

import java.util.*;

/**
 * 我们给出了一个（轴对齐的）矩形列表 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）
 * 是矩形 i 左下角的坐标，（x2，y2）是该矩形右上角的坐标。
 *
 * 找出平面中所有矩形叠加覆盖后的总面积。 由于答案可能太大，请返回它对 10 ^ 9 + 7 取模的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rectangle-area-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode850_W {
    // 坐标压缩法
    public int rectangleArea(int[][] rectangles) {
        int N = rectangles.length;
        Set<Integer> xVals = new HashSet<>();
        Set<Integer> yVals = new HashSet<>();

        for (int[] rect : rectangles) {
            xVals.add(rect[0]);
            xVals.add(rect[2]);
            yVals.add(rect[1]);
            yVals.add(rect[3]);
        }

        Integer[] imapx = xVals.toArray(new Integer[0]);
        Arrays.sort(imapx); // 将所有的x坐标排序
        Integer[] imapy = yVals.toArray(new Integer[0]);
        Arrays.sort(imapy); // 将所有的y坐标排序

        Map<Integer, Integer> mapx = new HashMap<>();
        Map<Integer, Integer> mapy = new HashMap<>();
        for (int i = 0; i < imapx.length; i++) {
            mapx.put(imapx[i], i); // 将排序后的x坐标映射到idx：0,1,2,...
        }
        for (int i = 0; i < imapy.length; i++) {
            mapy.put(imapy[i], i); // 将排序后的y坐标映射到idx：0,1,2,...
        }
        // 相当于把每个矩形用排序后的idx表示这样方便知道矩形之间是否重叠

        boolean[][] grid = new boolean[imapx.length][imapy.length]; // 标记重叠的区域
        for (int[] rect : rectangles) {
            for (int x = mapx.get(rect[0]); x < mapx.get(rect[2]); x++) { // 注意这里的结束条件没有等号，这样在下面求面积时加1操作就不会越界了
                for (int y = mapy.get(rect[1]); y < mapy.get(rect[3]); y++) {
                    // 映射后的矩形 (rx1, ry1, rx2, ry2)，标记满足 rx1 <= x < rx2 且 ry1 <= y < ry2 的网格 grid[x][y] = True
                    grid[x][y] = true;
                }
            }
        }

        long ans = 0;
        // 如果 x 映射得到 rx，则可以通过逆映射 imapy 从 rx 得到 x，即 imapx(rx) = x。
        // 每个网格 grid[rx][ry] 代表的实际矩形面积为 (imapx(rx+1) - imapx(rx)) * (imapy(ry+1) - imapy(ry))
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y]) {
                    ans += (long) (imapx[x + 1] - imapx[x]) * (imapy[y + 1] - imapy[y]);
                }
            }
        }
        return (int) ans % 1000_000_007;
    }

    // 线性扫描
    public int rectangleArea_(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1; // 底边添加，定边删除
        int[][] events = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] rec: rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0])); // 所有的水平间隔以 y 坐标由小到大的顺序

        List<int[]> active = new ArrayList();
        int cur_y = events[0][0];
        long ans = 0;
        for (int[] event: events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

            // Calculate query
            long query = 0;
            int cur = -1;
            for (int[] xs: active) {
                cur = Math.max(cur, xs[0]);
                query += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }

            ans += query * (y - cur_y);

            if (typ == OPEN) {
                active.add(new int[]{x1, x2});
                Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
            } else {
                for (int i = 0; i < active.size(); ++i){
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                        active.remove(i);
                        break;
                    }
                }
            }

            cur_y = y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }

    // 线段树
    class Solution {
        public int rectangleArea_ST(int[][] rectangles) {
            int OPEN = 1, CLOSE = -1;
            int[][] events = new int[rectangles.length * 2][];
            Set<Integer> Xvals = new HashSet();
            int t = 0;
            for (int[] rec: rectangles) {
                events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
                events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
                Xvals.add(rec[0]);
                Xvals.add(rec[2]);
            }

            Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

            Integer[] X = Xvals.toArray(new Integer[0]);
            Arrays.sort(X);
            Map<Integer, Integer> Xi = new HashMap();
            for (int i = 0; i < X.length; ++i)
                Xi.put(X[i], i);

            Node active = new Node(0, X.length - 1, X);
            long ans = 0;
            long cur_x_sum = 0;
            int cur_y = events[0][0];

            for (int[] event: events) {
                int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
                ans += cur_x_sum * (y - cur_y);
                cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
                cur_y = y;

            }

            ans %= 1_000_000_007;
            return (int) ans;
        }
    }

    class Node {
        int start, end;
        Integer[] X;
        Node left, right;
        int count;
        long total;

        public Node(int start, int end, Integer[] X) {
            this.start = start;
            this.end = end;
            this.X = X;
            left = null;
            right = null;
            count = 0;
            total = 0;
        }

        public int getRangeMid() {
            return start + (end - start) / 2;
        }

        public Node getLeft() {
            if (left == null) left = new Node(start, getRangeMid(), X);
            return left;
        }

        public Node getRight() {
            if (right == null) right = new Node(getRangeMid(), end, X);
            return right;
        }

        public long update(int i, int j, int val) {
            if (i >= j) return 0;
            if (start == i && end == j) {
                count += val;
            } else {
                getLeft().update(i, Math.min(getRangeMid(), j), val);
                getRight().update(Math.max(getRangeMid(), i), j, val);
            }

            if (count > 0) total = X[end] - X[start];
            else total = getLeft().total + getRight().total;

            return total;
        }
    }
}
