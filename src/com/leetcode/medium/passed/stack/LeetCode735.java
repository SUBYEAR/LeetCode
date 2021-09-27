package com.leetcode.medium.passed.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/asteroid-collision
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode735 {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        List<Integer> removed = new ArrayList<>();

        // 正数向右,负数向左
        for (int i = 0; i < asteroids.length; i++) {
            int stone = asteroids[i];
            if (stack.size() > 0  && stone < 0) {
                while (stack.size() > 0 && asteroids[stack.peekLast()] < -stone) {
                    int last = stack.pollLast();
                    removed.add(last);
                }

                if (stack.size() > 0) {
                    if (asteroids[stack.peekLast()] == -stone) {
                        removed.add(stack.pollLast());
                    }
                    removed.add(i);
                }
            }
            if (stone > 0) {
                stack.addLast(i);
            }
        }

        int idx = 0;
        int[] ans = new int[asteroids.length - removed.size()];
        for (int i = 0; i < asteroids.length; i++) {
            if (removed.contains(i)) {
                continue;
            }
            ans[idx++] = asteroids[i];
        }
        return ans;
    }
}
