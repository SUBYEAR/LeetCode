package com.leetcode.medium.review;

/**
 * 给你一份 n 位朋友的亲近程度列表，其中 n 总是 偶数 。
 *
 * 对每位朋友 i，preferences[i] 包含一份 按亲近程度从高到低排列 的朋友列表。换句话说，排在列表前面的朋友与 i 的亲近程度比排在列表后面的朋友
 * 更高。每个列表中的朋友均以 0 到 n-1 之间的整数表示。
 *
 * 所有的朋友被分成几对，配对情况以列表 pairs 给出，其中 pairs[i] = [xi, yi] 表示 xi 与 yi 配对，且 yi 与 xi 配对。
 *
 * 但是，这样的配对情况可能会是其中部分朋友感到不开心。在 x 与 y 配对且 u 与 v 配对的情况下，如果同时满足下述两个条件，x 就会不开心：
 *
 * x 与 u 的亲近程度胜过 x 与 y，且
 * u 与 x 的亲近程度胜过 u 与 v
 * 返回 不开心的朋友的数目 。
 *
 * 2 <= n <= 500
 * n 是偶数
 * preferences.length == n
 * preferences[i].length == n - 1
 * 0 <= preferences[i][j] <= n - 1
 * preferences[i] 不包含 i
 * preferences[i] 中的所有值都是独一无二的
 * pairs.length == n/2
 * pairs[i].length == 2
 * xi != yi
 * 0 <= xi, yi <= n - 1
 * 每位朋友都 恰好 被包含在一对中
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-unhappy-friends
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-unhappy-friends
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1583_W {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        // 创建n行n列的二维数组order，order[i][j]表示朋友j在i的朋友列表中亲近程度的下标，所有的朋友被分成 n/2 对，为了快速知道每位朋友的配
        // 对的朋友，对于配对情况也需要进行预处理。创建长度为 n 的数组 match，如果 x 和 y 配对，则有 match[x]=y 以及 match[y]=x
        int[][] order = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                order[i][preferences[i][j]] = j; // 下标越靠前越亲近
            }
        }
        int[] match = new int[n];
        for (int[] pair : pairs) {
            int x = pair[0], y = pair[1];
            match[x] = y;
            match[y] = x;
        }

        int ans = 0;
        for (int x = 0; x < n; x++) {
            int y = match[x]; // 找到与x配对的朋友y
            int index = order[x][y]; // 找到朋友y在x的朋友列表中亲近程度的下标

            for (int i = 0; i < index; i++) {
                int u = preferences[x][i]; // x 与 u 的亲近程度胜过x 与 y
                int v = match[u];
                if (order[u][x] < order[u][v]) { // u 与 x 的亲近程度胜过 u 与 v
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
