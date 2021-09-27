package com.leetcode.medium.passed.stringProcess;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个二进制字符串 s 和一个整数 k 。
 *
 * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 true ，否则请返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "00110110", k = 2
 * 输出：true
 * 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1461 {
    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i + k <= s.length(); i++) {
            String substring = s.substring(i, i + k);
            set.add(substring);
        }
        return set.size() == (1 << k);
    }
}

