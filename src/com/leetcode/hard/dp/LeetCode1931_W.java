package com.leetcode.hard.dp;

import java.util.*;

/**
 * 给你两个整数 m 和 n 。构造一个 m x n 的网格，其中每个单元格最开始是白色。请你用 红、绿、蓝 三种颜色为每个单元格涂色。所有单元格都需要被涂色。
 *
 * 涂色方案需要满足：不存在相邻两个单元格颜色相同的情况 。返回网格涂色的方法数。因为答案可能非常大， 返回 对 109 + 7 取余 的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/painting-a-grid-with-three-different-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1931_W {
    public int colorTheGrid(int m, int n) {
        // 将m 和 n中小的值进行枚举，枚举的涂色方案红绿蓝转换为数字0~2，那么涂色方案转换成十进制是[0,3的m次方)可以枚举这个范围内的
        // 整数转换成为长度为m的三进制串，判断是否满足相邻的两个数均不相同。
        // 将整个网格想象成类似3 X 200的网络，每一行进行枚举
        // 用f[i][mask]表示已经对0~i行进行了涂色,并且第i行的涂色方案对应的三进制表示为mask的前提下的总数
        // 转态转移时，考虑第i - 1行的涂色方案mask'，那么f[i][mask] = 求和f[i - 1][mask'] 其中mask'与mask的每一位均不相同
        // 最终答案即为所有满足mask属于[0,3的m次方)的f[n - 1][mask]之和
        // 最后需要注意的是，在状态转移方程中，f[i][..]只会从 f[i−1][..]转移而来，因此我们可以使用两个长度为 3^m 的一维数组交替地进行状态转移
        final int MOD = 1000_000_007;
        // 哈希映射valid存储所以满足要求的对一行进行涂色方案，键表示mask，值表示mask的三进制串以列表的形式存储
        Map<Integer, int[]> valid = new HashMap<>();

        // 列举[0, 3^m)范围内满足要求的mask
        int maskEnd = (int) Math.pow(3, m);
        for (int mask = 0; mask < maskEnd; mask++) {
            int[] color = new int[m];
            int mm = mask;
            for (int i = 0; i < m; i++) {
                color[i] = mm % 3;
                mm /= 3;
            }
            boolean check = true;
            for (int i = 0; i < m - 1; ++i) {
                if (color[i] == color[i + 1]) {
                    check = false;
                    break;
                }
            }
            if (check) {
                valid.put(mask, color);
            }
        }

        // 预处理所有的(mask1， mask2)二元组，满足mask1和mask2作为相邻行时，同一列上两个格子的颜色不同
        Map<Integer, List<Integer>> adjacent = new HashMap<>();
        for (Integer mask1 : valid.keySet()) {
            List<Integer> list = new ArrayList<>();
            for (Integer mask2 : valid.keySet()) {
                boolean check = true;
                for (int i = 0; i < m; i++) {
                    if (valid.get(mask1)[i] == valid.get(mask2)[i]) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    list.add(mask2);
                }
            }
            adjacent.put(mask1, list);
        }

        // dp[i][mask] 仅与dp[i-1][mask']有关可以使用一维数组实现dp
        int[] dp = new int[maskEnd];
        // 边界条件 dp[i][mask], i == 0 时
        for (int mask : valid.keySet()) {
            dp[mask] = 1;
        }
        //动态规划
        for (int i = 1; i < n; i++) {
            int[] tmp = new int[maskEnd];
            for (int mask : valid.keySet()) {
                for (int index : adjacent.get(mask)) {
                    tmp[mask] = (tmp[mask] + dp[index]) % MOD;
                }
            }
            dp = tmp;
        }

        int ans = 0;
        for (int mask : valid.keySet()) {
            ans = (ans + dp[mask]) % MOD;
        }
        return ans;
    }
}
