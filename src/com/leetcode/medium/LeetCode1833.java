package com.leetcode.medium;

import java.util.Arrays;

/**
 * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
 *
 * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于
 * 消费，他想要买尽可能多的雪糕。
 *
 * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
 *
 * 注意：Tony 可以按任意顺序购买雪糕。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1833 {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int i = 0;
        int res = 0;
        int n = costs.length;
        while (i < n) {
            coins -= costs[i];
            if (coins < 0) {
                break;
            }
            res++;
            i++;
        }
        return res;
    }
}
