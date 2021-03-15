package com.leetcode.medium.review;
/**
 *
 */

import java.util.LinkedList;
import java.util.List;

public class LeetCode22 {
    void dfsParenthesis(List<String> res, String str, int left, int right, int n) {
        if (left > n || right > n || right > left) {
            return;
        }

        if (left == n && right == n) {
            res.add(str);
            return;
        }
        dfsParenthesis(res, str + '(', left + 1, right, n);
        dfsParenthesis(res, str + ')', left, right + 1, n);
    }

    public List<String> generateParenthesis(int n) {
        String str = new String();
        List<String> res = new LinkedList<>();
        dfsParenthesis(res, str, 0, 0, n);
        return res;
    }
}

// 回溯解法
// class Solution {
//    public List<String> generateParenthesis(int n) {
//        List<String> ans = new ArrayList<String>();
//        backtrack(ans, new StringBuilder(), 0, 0, n);
//        return ans;
//    }
//
//    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
//        if (cur.length() == max * 2) {
//            ans.add(cur.toString());
//            return;
//        }
//        if (open < max) {
//            cur.append('(');
//            backtrack(ans, cur, open + 1, close, max);
//            cur.deleteCharAt(cur.length() - 1);
//        }
//        if (close < open) {
//            cur.append(')');
//            backtrack(ans, cur, open, close + 1, max);
//            cur.deleteCharAt(cur.length() - 1);
//        }
//    }
//}
