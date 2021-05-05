package com.leetcode.medium.review.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 *数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
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
