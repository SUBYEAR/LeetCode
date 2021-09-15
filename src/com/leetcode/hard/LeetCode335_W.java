package com.leetcode.hard;

/**
 * 给定一个含有 n 个正数的数组 x。从点 (0,0) 开始，先向北移动 x[0] 米，
 * 然后向西移动 x[1] 米，向南移动 x[2] 米，向东移动 x[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
 * <p>
 * 编写一个 O(1) 空间复杂度的一趟扫描算法，判断你所经过的路径是否相交。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/self-crossing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode335_W {
    // 三种相交的情况如下所示
    //    i-2 第一种 条件为 i>=3 && x[i] >= x[i-2] && x[i-1] <= x[i-3]
    //   ┌───┐
    //i-1│   │ i-3
    //   └───┼──
    //     i │

    //┌───┐i-4
    //│   │
    //|___|i

    // ---——-
    // |    |
    // |    |i-5
    // |    |
    //   ___|___________
    // |    |     i     | i-1
    // |                |
    // |                |
    // |________________|
    public  boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if (n < 4) return false;
        for (int i = 3; i < n; ++i) {
            if (distance[i - 3] >= distance[i - 1] && distance[i - 2] <= distance[i]) return true; // 第一种情况
            if (i > 3) { // 第二种情况
                if (distance[i - 4] + distance[i] >= distance[i - 2] && distance[i - 3] == distance[i - 1]) return true;
            }
            if (i > 4) { // 第三种情况
                if (distance[i] + distance[i - 4] >= distance[i - 2] && distance[i - 2] >= distance[i - 4] && distance[i - 5] + distance[i - 1] >= distance[i - 3]
                        && distance[i - 1] <= distance[i - 3]) // 这里如果x[i-1]不小于x[i-3]就会导致相交 那一条横线上抬到最顶端
                    return true;
            }
        }
        return false;
    }
//    链接：https://leetcode-cn.com/problems/self-crossing/solution/t335zhi-guan-de-shu-xue-si-xiang-by-tang-zi-xuan/

}
