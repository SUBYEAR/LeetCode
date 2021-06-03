package com.leetcode.medium.review.dfs;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-and-jug-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode365_W {
    // 在任意一个时刻，我们可以且仅可以采取以下几种操作：
    //把 X 壶的水灌进 Y 壶，直至灌满或倒空；
    //把 Y 壶的水灌进 X 壶，直至灌满或倒空；
    //把 X 壶灌满；
    //把 Y 壶灌满；
    //把 X 壶倒空；
    //把 Y 壶倒空。
    public boolean canMeasureWater(int x, int y, int z) {
        Deque<int[]> stack = new LinkedList<int[]>();
        stack.push(new int[]{0, 0}); // 0表示水壶中没有装水
        Set<Long> seen = new HashSet<Long>();
        while (!stack.isEmpty()) {
            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            seen.add(hash(stack.peek()));

            int[] state = stack.pop();
            int remain_x = state[0], remain_y = state[1];
            if (remain_x == z || remain_y == z || remain_x + remain_y == z) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new int[]{x, remain_y});
            // 把 Y 壶灌满。
            stack.push(new int[]{remain_x, y});
            // 把 X 壶倒空。
            stack.push(new int[]{0, remain_y});
            // 把 Y 壶倒空。
            stack.push(new int[]{remain_x, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[]{remain_x - Math.min(remain_x, y - remain_y), remain_y + Math.min(remain_x, y - remain_y)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[]{remain_x + Math.min(remain_y, x - remain_x), remain_y - Math.min(remain_y, x - remain_x)});
        }
        return false;
    }

    public long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

    // 数学解法：每次操作只会让桶里的水总量增加 x，增加 y，减少 x，或者减少 y。在题目所给的操作下，两个桶不可能同时有水且不满
    // 因为观察所有题目中的操作，操作的结果都至少有一个桶是空的或者满的；其次，对一个不满的桶加水是没有意义的。
    // 因为如果另一个桶是空的，那么这个操作的结果等价于直接从初始状态给这个桶加满水；而如果另一个桶是满的，
    // 那么这个操作的结果等价于从初始状态分别给两个桶加满；再次，把一个不满的桶里面的水倒掉是没有意义的。因为如果另一个桶是空的，
    // 那么这个操作的结果等价于回到初始状态；而如果另一个桶是满的，那么这个操作的结果等价于从初始状态直接给另一个桶倒满
    // 目标是找到一对整数a,b 使得 ax+by=z 且 z<=x+y.
    // 若 a<0，那么可以进行以下操作：往 y 壶倒水；把 y 壶的水倒入 x 壶；如果 y 壶不为空，那么 x 壶肯定是满的，把 x 壶倒空，
    // 然后再把 y 壶的水倒入 x 壶。重复以上操作直至某一步时 x 壶进行了 a 次倒空操作，y 壶进行了 b 次倒水操作。
    // 若 b<0，方法同上，x 与 y 互换。贝祖定理告诉我们，ax+by=z 有解当且仅当 z 是 x,y 的最大公约数的倍数。
    // 因此我们只需要找到 x,y 的最大公约数并判断 z 是否是它的倍数即可
    public boolean canMeasureWater_math(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    public int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }
}
