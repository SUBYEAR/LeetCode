package com.leetcode.medium.review.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "havefunonleetcode", K = 5
 * 输出：6
 * 解释：
 * 这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
 * 示例 2：
 *
 * 输入：S = "home", K = 5
 * 输出：0
 * 解释：
 * 注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-length-substrings-with-no-repeated-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1100 {
    public int numKLenSubstrNoRepeats(String S, int K) {
        // 和无重复最长的子串类似，使用滑动窗口
        int length = S.length();

        int res = 0;
        int left = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (int right = 0; right < length; right++) {
            char ch = S.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
            // 添加的无重复，且窗口长度为K, 则累加，并将窗口整体右移，继续判断后面的
            if (window.get(ch) == 1 && (right - left + 1 == K)) {
                res++;
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
                continue;
            }

            // 有重复则整体右移，直到把重复的那个给排除在外
            while (window.get(ch) > 1) {
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
            }

        }

        return res;
    }
}

