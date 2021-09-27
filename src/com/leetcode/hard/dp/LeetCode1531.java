package com.leetcode.hard.dp;

import java.util.Arrays;

/**
 * 行程长度编码 是一种常用的字符串压缩方法，它将连续的相同字符（重复 2 次或更多次）替换为字符和表示字符计数的数字（行程长度）。
 * 例如，用此方法压缩字符串 "aabccc" ，将 "aa" 替换为 "a2" ，"ccc" 替换为` "c3" 。因此压缩后的字符串变为 "a2bc3" 。
 *
 * 注意，本问题中，压缩时没有在单个字符后附加计数 '1' 。
 *
 * 给你一个字符串 s 和一个整数 k 。你需要从字符串 s 中删除最多 k 个字符，以使 s 的行程长度编码长度最小。
 *
 * 请你返回删除最多 k 个字符后，s 行程长度编码的最小长度 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aaabcccd", k = 2
 * 输出：4
 * 解释：在不删除任何内容的情况下，压缩后的字符串是 "a3bc3d" ，长度为 6 。最优的方案是删除 'b' 和 'd'，这样一来，压缩后的字符串为 "a3c3" ，长度是 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-compression-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1531 {
    public int getLengthOfOptimalCompression(String s, int k) {
        int len = s.length();
        int T = len - k;
        // dp[i][j] 从字符串的第i位开始的情况下已经选择了j个字符
        int[][] dp = new int[len + 1][T +1];
        for (int[] arr : dp) {
            Arrays.fill(arr, 100);
        }
        dp[len][T] = 0;

        for (int p = len - 1; p >= 0; p--) {
            for (int cnt = 0; cnt <= T; ++cnt) {
                // 从字符串的第p位开始选择了cnt个字符

                for (int j = p, same = 0; j < len; j++) {
                    // 枚举每一个字符选取出来组成连续的情况
                    same += s.charAt(j) == s.charAt(p) ? 1 : 0;
                    if (cnt + same > T) {
                        break;
                    }
                    // 要理解的是位置p和j之间的相同字符个数就是此处累计的cnt
                    // calc(same) + dp[j + 1][cnt + same]的含义是j位是字面a,后面选取的都是a那么可以编码成a_same
                    dp[p][cnt] = Math.min(dp[p][cnt], calc(same) + dp[j + 1][cnt + same]);
                }
                dp[p][cnt] = Math.min(dp[p][cnt], dp[p + 1][cnt]); // 丢弃该字符
            }
        }
        return dp[0][0];
    }

    private int calc(int x) { // 字符串的长度最长是100
        return x <= 1 ? x : ((x <= 9) ? 2 : ((x <= 99) ? 3 : 4));
    }
}
//https://leetcode-cn.com/problems/string-compression-ii/solution/dong-tai-gui-hua-shi-jian-on3kong-jian-on2-by-newh/