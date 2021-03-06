package com.leetcode.hard.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算
 * 出能够拼写关键词中所有字符的最少步数。最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的
 * 一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 *
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，
 * 并且这个字符必须等于字符 key[i]。如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。
 * 按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/freedom-trail
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode514 {
    //定义dp[i][j] 表示从前往后拼写出 key 的第 i 个字符，ring 的第 j 个字符与 12:00方向对齐的最少步数（下标均从 0 开始）。
    public int findRotateSteps(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26]; // 注意这里的位置数组的形式，因为可能存在字符重复出现
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) { // 匹配到key当前字符串位置的枚举
                for (int k : pos[key.charAt(i - 1) - 'a']) { // 匹配key的前一个字符串位置的枚举
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }
}
