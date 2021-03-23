package com.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。
 * （我们可以在任何位置插入每个字符，也可以插入 0 个字符。） 给定待查询列表 queries，和模式串 pattern，
 * 返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/camelcase-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1023 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new LinkedList<>();
        for (String s : queries) {
            if (isMatched(s, pattern)) {
                res.add(Boolean.TRUE);
            } else {
                res.add(Boolean.FALSE);
            }
        }
        return res;
    }

    boolean isMatched(String s, String pattern) {
        int n = s.length(), m = pattern.length();
        int i = 0, j = 0;
        while (i < n) {
            if (j < m && s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else if (Character.isUpperCase(s.charAt(i))) {
                return false;
            } else {
                i++;
            }
        }
        return i == n && j == m;
    }
}
