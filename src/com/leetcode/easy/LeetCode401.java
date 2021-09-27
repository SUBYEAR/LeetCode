package com.leetcode.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 */
public class LeetCode401 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new LinkedList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    String s = h + ":" + ((m < 10) ? "0" + m : m);
                    res.add(s);
                }
            }
        }
        return res;
    }

    public void backtarck(int[] nums, int start, int cnt, int limit, LinkedList<Integer> path, List<Integer> res) {
        if (path.size() + nums.length - start + 1 < cnt) {
            return;
        }

        if (cnt == path.size()) {
            path.stream().reduce(Integer::sum).filter(n -> n < limit).ifPresent(res::add);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtarck(nums, i + 1, cnt, limit, path, res);
            path.removeLast();
        }
    }


}
