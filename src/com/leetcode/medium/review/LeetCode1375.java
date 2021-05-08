package com.leetcode.medium.review;

/**
 * 房间中有 n 枚灯泡，编号从 1 到 n，自左向右排成一排。最初，所有的灯都是关着的。
 *
 * 在 k  时刻（ k 的取值范围是 0 到 n - 1），我们打开 light[k] 这个灯。
 *
 * 灯的颜色要想 变成蓝色 就必须同时满足下面两个条件：
 *
 * 灯处于打开状态。
 * 排在它之前（左侧）的所有灯也都处于打开状态。
 * 请返回能够让 所有开着的 灯都 变成蓝色 的时刻 数目 。
 *
 * 输入：light = [2,1,3,5,4]
 * 输出：3
 * 解释：所有开着的灯都变蓝的时刻分别是 1，2 和 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bulb-switcher-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1375 {
    public int numTimesAllBlue(int[] light) {
        int ans = 0;
        int curMax = 0; // 记录当前最大亮起来的灯，如果最大亮起来的灯等于遍历过的灯的数量
        for (int i = 0; i < light.length; i++) {
            curMax = Math.max(curMax, light[i]);
            if (curMax == i + 1) {
                ans++;
            }
        }
        return ans;
    }
}
