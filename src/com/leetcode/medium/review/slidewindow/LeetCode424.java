package com.leetcode.medium.review.slidewindow;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后
 * ，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过 104。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode424 {
    public int characterReplacement(String s, int k) {
        int res = 0;
        char[] chars = s.toCharArray();
        int maxCount = 0;
        int[] map = new int[26];
        int left = 0, right = 0;
        while (right < s.length()) {
            ++map[chars[right] - 'A'];
            maxCount = Math.max(maxCount, map[chars[right] - 'A']);
            ++right;

            if (right - left > maxCount + k) {
                --map[chars[left] - 'A'];
                left++;
                // 求最小值的逻辑在此处更新
            }
            res = Math.max(res, right - left); // 求最大值在此处更新
        }
        return res;
    }
}
