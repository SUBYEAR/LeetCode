/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.Arrays;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，
 * 在子字符串中都恰好出现了偶数次。
 * 定义 pre[i][k] 表示在字符串前 i 个字符中，第 k 个元音字母一共出现的次数
 * 枚举字符串的每个位置 i ，计算以它结尾的满足条件的最长字符串长度。其实我们要做的就是快速找到最小的 j∈[0,i)，
 * 满足 pre[i][k]−pre[j][k]（即每一个元音字母出现的次数）均为偶数，那么以 i 结尾的最长字符串 s[j+1,i]长度就是 i-j
 * 从维护元音字母出现的次数改作维护元音字母出现次数的奇偶性。这样我们只要实时维护每个元音字母出现的奇偶性，那么 s[j+1,i]
 * 满足条件当且仅当对于所有的 k，pre[i][k] 和 pre[j][k] 的奇偶性都相等，此时我们就可以利用哈希表存储每一种奇偶性
 * （即考虑所有的元音字母）对应最早出现的位置，边遍历边更新答案。
 *
 * @since 2020-05-20
 */
public class LeetCode1371 {
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0; // 是否因为这里所以才需要i + 1,表示初始状态aeiou均出现0次时位置是0
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 用异或来更新状态很妙
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                // 这里要加1是因为pos数组表示前i个字符中元音字母的奇偶性状态，索引是状态，值是字符个数
                pos[status] = i + 1; // 加1必须有不然会 "leetcodeisgreat" 测试用例失败
            }
        }
        return ans;
    }
}
