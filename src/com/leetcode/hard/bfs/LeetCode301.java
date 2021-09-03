package com.leetcode.hard.bfs;

import java.util.*;

/**
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 */
public class LeetCode301 {
    // 使用回溯算法可以通过,思路是从字符中选择去除不匹配括号后剩下的字符个数.标准回溯模板解法通过但是效率比较低
    // 下面提供是官解的BFS解法
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }

        // 广度优先遍历须要的队列和防止重复遍历的哈希表 visited
        Set<String> visited = new HashSet<>();
        visited.add(s);
        Queue<String> queue = new LinkedList<>();
        queue.add(s);

        // 找到目标值以后推出循环
        boolean found = false;
        while (!queue.isEmpty()) {
            // 最优解一定在同一层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String front = queue.poll();
                if (isValid(front)) {
                    res.add(front);
                    found = true;
                }

                int currentWordLen = front.length();
                char[] charArray = front.toCharArray();
                for (int j = 0; j < currentWordLen; j++) {
                    if (front.charAt(j) != '(' && front.charAt(j) != ')') {
                        continue;
                    }

                    // 注意 new String() 方法的 API，第 1 个参数是字符数组，第 2 个参数是字符数组的起始下标，第 3 个参数是截取的字符的长度
                    String next = new String(charArray, 0, j) + new String(charArray, j + 1, currentWordLen - j - 1);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }

            // 这一层找到以后，退出外层循环，返回结果
            if (found) {
                break;
            }
        }
        return res;
    }

    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        int count = 0;
        for (char c : charArray) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}
