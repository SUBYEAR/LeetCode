package com.leetcode.hard.stringProcess;

import java.util.Arrays;

/**
 * 给你两个长度为 n 的字符串 s1 和 s2 ，以及一个字符串 evil 。请你返回 好字符串 的数目。
 *
 * 好字符串 的定义为：它的长度为 n ，字典序大于等于 s1 ，字典序小于等于 s2 ，且不包含 evil 为子字符串。
 *
 * 由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-good-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1397 { // 数位dp典型应用
    private int[] next;
    public int[] getNext4(String s) {
        int[] next = new int[s.length() + 1]; // 注意这里长度是多了1个
        int i = 0, j = -1;
        next[0] = -1;
        while (i < s.length()) {
            if (j == -1 || s.charAt(j) == s.charAt(i))
                next[++i] = ++j;
            else
                j = next[j];
        }
        next[0] = 0; // 此处将 next[0] 置为 0 方便逻辑统一
        return next;
    }
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        next = getNext4(evil);
        int res1 = solve(s1, evil);
        int res2 = solve(s2, evil);
        return (res2 - res1 + modulo + (s1.contains(evil) ? 0 : 1)) % modulo; // 注意这里还要判断s1是否包含evil

    }

    private int[][] findGoodStringsDp;
    private final int modulo = 1000000007;
    private int solve(String string, String evil) {
        findGoodStringsDp = new int[string.length()][evil.length()];
        for (int i = 0; i < findGoodStringsDp.length; i++) {
            Arrays.fill(findGoodStringsDp[i], -1);
        }
        return dfs(0, true, 0, evil, string);
    }

    private int nextMatch(int index, String evil, char c) {
        while (index != 0 && evil.charAt(index) != c) {
            index = next[index];
        }
        if (evil.charAt(index) == c) {
            index++;
        }
        return index;
    }

    private int dfs(int pos, boolean limit, int match, String evil, String string) {
        if (pos == string.length()) return 1;
        if (!limit && findGoodStringsDp[pos][match] != -1) return findGoodStringsDp[pos][match];
        int up = limit ? string.charAt(pos) : 'z';
        int tmp = 0;
        for (int i = 'a'; i <= up; i++) {
            if (evil.charAt(match) != i || match != evil.length() - 1)  { // 第二个条件限制了不可能出现evil字串很妙
                tmp = (tmp % modulo) +
                      dfs(pos + 1, limit && i == string.charAt(pos), nextMatch(match, evil,(char) i), evil, string) % modulo;
                tmp %= modulo;
            }
        }
        if (!limit) findGoodStringsDp[pos][match] = tmp;
        return tmp;
    }

//    链接：https://leetcode-cn.com/problems/find-all-good-strings/solution/javadfsshu-wei-dpkmp-by-guaidaokakaxi/


    int[][] dp;
    String evil;
    public int findGoodStrings_fail(int n, String s1, String s2, String evil) {
        this.evil = evil;
        dp = new int[n + 5][evil.length() + 5];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(s1, s1.length() - 1, getMaxPrefixLen(s1, evil), true);
    }

    int dfs(String s, int pos, int len, boolean limit) { // 填到第pos位时和evil串匹配到长度为len的前缀时满足条件的个数
        if (pos == -1) {
            return 1;
        }

        if (!limit && dp[pos][len] != -1) {
            return dp[pos][len];
        }
        int ans = 0;
        int transPos = s.length() - 1 - pos;
        char up = limit ? s.charAt(transPos) : 'z';
        for (char i = 'a'; i <= up; ++i) {
            int newLen = getMaxPrefixLen(i + s.substring(transPos + 1), evil);
            ans += dfs(s, pos - 1, newLen, limit && i == s.charAt(transPos));
        }

        if (!limit) {
            dp[pos][len] = ans;
        }
        return ans;
    }

    private int[] getNextArr(String evil) {
        int[] next = new int[evil.length()];
        for (int j = 1, k = 0; j < evil.length(); j++) {
            while (k > 0 && evil.charAt(j) != evil.charAt(k)) {
                k = next[k - 1];
            }
            if (evil.charAt(j) == evil.charAt(k)) {
                k++;
            }
            next[j] = k;
        }
        return next;
    }

    private int getMaxPrefixLen(String m, String n) {
        int[] next = getNextArr(n);
        int i = 0, j = 0;
        for (; i < m.length(); i++) {
            while (j > 0 && m.charAt(i) != n.charAt(j)) {
                j = next[j];
            }
            if (m.charAt(i) == n.charAt(j)) {
                j++;
            }
            if (j == n.length()) {
                break;
            }
        }
        return j;
    }
}
