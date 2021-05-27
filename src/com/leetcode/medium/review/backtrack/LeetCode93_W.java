/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.backtrack;

import java.util.ArrayList;
import java.util.List;


/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *
 * @since 2020-06-28
 */
public class LeetCode93_W {
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        dfs(results, sb, chars, 0, 0);
        return results;
    }

    /**
     * 回溯查找可能的ip数字
     *
     * @param count 当前已经确定的数字
     * @param i 下一个待处理的字符下标
     */
    private void dfs(List<String> results, StringBuilder sb, char[] chars, int count, int i) {
        // 找到了4个数字，并且字符刚好用完，标志着我们找到了一个解
        if (count == 4 && i == chars.length) {
            results.add(sb.toString());
            return;
        }

        int remainCount = 4 - count;
        int remainsChars = chars.length - i;
        // 判断剩余的字符是否有解
        if (remainCount > remainsChars || remainCount * 3 < remainsChars) {
            return;
        }

        int len = sb.length();
        // 不允许出现以0开头的多位数
        int maxLen = chars[i] == '0' ? 1 : 3;
        for (int j = 0; j < maxLen && i + j < chars.length; j++) {
            // 取三位数时，需要判断是否超出255
            if (j == 2 && (chars[i] - '0') * 100 + (chars[i + 1] - '0') * 10 + chars[i + 2] - '0' > 255) {
                continue; // 这里用break 和continue效果一样，一般情况都用break
            }
            for (int k = 0; k <= j; k++) {
                sb.append(chars[i + k]);
            }
            // 第四个数字后面不需要加点
            if (count < 3) {
                sb.append('.');
            }
            dfs(results, sb, chars, count + 1, i + j + 1);
            // 因为第四个数字后面不需要加点，回溯时少删除一位
            sb.delete(len, count < 3 ? len + j + 2 : len + j + 1);
        }
    }
}

//     class Solution {
//     static final int SEG_COUNT = 4;
//     List<String> ans = new ArrayList<String>();
//     int[] segments = new int[SEG_COUNT];
//
//     public List<String> restoreIpAddresses(String s) {
//         segments = new int[SEG_COUNT];
//         dfs(s, 0, 0);
//         return ans;
//     }
//
//     public void dfs(String s, int segId, int segStart) { // 从 s[segStart] 的位置开始，搜索 IP 地址中的第 segId 段
//         // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
//         if (segId == SEG_COUNT) {
//             if (segStart == s.length()) {
//                 StringBuffer ipAddr = new StringBuffer();
//                 for (int i = 0; i < SEG_COUNT; ++i) {
//                     ipAddr.append(segments[i]);
//                     if (i != SEG_COUNT - 1) {
//                         ipAddr.append('.');
//                     }
//                 }
//                 ans.add(ipAddr.toString());
//             }
//             return;
//         }
//
//         // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
//         if (segStart == s.length()) {
//             return;
//         }
//
//         // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
//         if (s.charAt(segStart) == '0') {
//             segments[segId] = 0;
//             dfs(s, segId + 1, segStart + 1); // 注意这里不能return
//         }
//
//         // 一般情况，枚举每一种可能性并递归
//         int addr = 0;
//         for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
//             addr = addr * 10 + (s.charAt(segEnd) - '0');
//             if (addr > 0 && addr <= 0xFF) {
//                 segments[segId] = addr;
//                 dfs(s, segId + 1, segEnd + 1);
//             } else {
//                 break; // 这里用break是因为继续循环的话addr的值更加比255大，所以不用继续在选择列表中向后尝试了
//             }
//         }
//     }
// }
//
// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/restore-ip-addresses/solution/fu-yuan-ipdi-zhi-by-leetcode-solution/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
