package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个字符串 s 和 t ，你的目标是在 k 次操作以内把字符串 s 转变成 t 。
 *
 * 在第 i 次操作时（1 <= i <= k），你可以选择进行如下操作：
 *
 * 选择字符串 s 中满足 1 <= j <= s.length 且之前未被选过的任意下标 j （下标从 1 开始），并将此位置的字符切换 i 次。
 * 不进行任何操作。
 * 切换 1 次字符的意思是用字母表中该字母的下一个字母替换它（字母表环状接起来，所以 'z' 切换后会变成 'a'）。
 *
 * 请记住任意一个下标 j 最多只能被操作 1 次。
 *
 * 如果在不超过 k 次操作内可以把字符串 s 转变成 t ，那么请你返回 true ，否则请你返回 false 。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-convert-string-in-k-moves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1540 {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        if (k == 0) {
            return s.equals(t);
        }
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>(s.length()); // 所有需要切换n次的个数
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                int times = (t.charAt(i) - s.charAt(i) + 26) % 26;
                if (times > k) {
                    return false;
                }
                int cnt = map.getOrDefault(times, 0);
                map.put(times, cnt + 1);
                max = Math.max(max, cnt + 1);
            }
        }

        if (max == 1) {
            return k >= map.size();
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int diff = entry.getKey();
            if (k < (diff + (entry.getValue() - 1) * 26L)) {
                return false;
            }
        }
        return true;
    }
}
