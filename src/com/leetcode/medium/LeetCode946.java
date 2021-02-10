package com.leetcode.medium;

import java.util.Stack;

public class LeetCode946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        int i = 0;
        for (int j = 0; j < pushed.length; j++) {
            s.push(pushed[j]);
            while (!s.empty() && pushed[j] == popped[i]) {
                s.pop();
                ++i;
            }
        }
        return s.empty();
    }
}
