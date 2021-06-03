package com.leetcode.medium.review;

/**
 * 有一个特殊的正方形房间，每面墙上都有一面镜子。除西南角以外，每个角落都放有一个接受器，编号为 0， 1，以及 2。
 * 正方形房间的墙壁长度为 p，一束激光从西南角射出，首先会与东墙相遇，入射点到接收器 0 的距离为 q 。
 * 返回光线最先遇到的接收器的编号（保证光线最终会遇到一个接收器）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/mirror-reflection
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode858_W {
    // 我们把光线的运动拆分成水平和垂直两个方向来看。在水平和竖直方向，光线都在 0 到 p 之间往返运动，并且水平方向的运动速度是竖直方向的 p/q 倍。
    // 我们可以将光线的运动抽象成：每过一个时间步，光线在水平方向从一侧跳动到另一侧（即移动 p 的距离），同时在竖直方向前进 q 的距离，
    // 如果到达了边界就折返。 需要找到最小的 k 使得 kq 是 p 的倍数，并且根据 k 的奇偶性可以得知光线到达了左侧还是右侧；根据 kq / p 的奇偶性
    // 可以得知光线到达了上方还是下方，从而得知光线到达的接收器的编号。
    public int mirrorReflection(int p, int q) {
        int gcd = getGcd(p, q);
        // 最小公倍数lcm是最小的同时可以被p 和 q 整除的数 lcm = pq / gcd, 因此最小的k就是p/gcd
        p = p / gcd; p %= 2;
        q = q / gcd; q %= 2;
        if (p == 1 && q == 1) { // 水平方向和垂直方向的运动距离都是p的奇数倍那么肯定会到底1
            return 1;
        }

        return p == 1 ? 0 : 2;
    }

    private int getGcd(int a, int b) { // 最大公约数 greatest common divisor
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
