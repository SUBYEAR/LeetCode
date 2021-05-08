package com.leetcode.medium.review;

import java.util.TreeMap;

/**
 * 爱丽丝有一手（hand）由整数数组给定的牌 
 * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
 * 如果她可以完成分组就返回 true，否则返回 false。
 * 示例 1：
 *
 * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
 * 输出：true
 * 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 *
 * 输入：hand = [1,2,3,4,5], W = 4
 * 输出：false
 * 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
 *
 */
public class LeetCode846 {
    public boolean isNStraightHand(int[] hand, int W) {
        if ((hand.length % W) != 0) {
            return false;
        }

        TreeMap<Integer, Integer> info = new TreeMap<>();
        for (int val : hand) {
            int times = info.getOrDefault(val, 0);
            info.put(val, times + 1);
        }

        while (!info.isEmpty()) {
            Integer start = info.firstKey();

            for (Integer i = 0; i < W; i++) {
                if (info.containsKey(start)) {
                    Integer times = info.get(start);
                    if (times.equals(1)) {
                        info.remove(start);
                    } else {
                        info.put(start, times - 1);
                    }
                    ++start;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
