package com.leetcode.hard;

/**
 * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
 *
 * 给定一个起点 (sx, sy) 和一个终点 (tx, ty)，如果通过一系列的转换可以从起点到达终点，则返回 True ，否则返回 False。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reaching-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode780_W {
    // 从终点开始不断向上求解父点,每个子点 (x, y) 只能有一个父点，当 x >= y 时父点为 (x-y, y)；当 y > x 时父点为 (x, y-x)
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) {
                break;
            }
            if (tx > ty) { // 求解父点的运算是 tx - ty
                if (ty > sy) tx %= ty; // 当同时满足 tx > ty 和 ty > sy 时，可以一次性执行所有的这些操作，直接令 tx %= ty
                else return (tx - sx) % ty == 0; // 否则，如果满足 tx > ty 和 ty <= sy，那么 ty 不再改变，只能不断从 tx 中减去 ty。因此， (tx - sx) % ty == 0 是结果为 true 的充要条件。
            } else { // 对于 ty > tx 的情况类似
                if (tx > sx) ty %= tx;
                else return (ty - sy) % tx == 0;
            }
        }
        return tx == sx && ty == sy;
    }
}
