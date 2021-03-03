package com.leetcode.medium.review;

/**
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 *
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 *
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 *
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-23
 */
public class LeetCode1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int len = grumpy.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }

        int res = 0;
        int left = 0, right = 0;
        while (right < len) {
            if (grumpy[right] == 1) {
                sum += customers[right];
            }
            right++;

            if ((right - left) == X) {
                res = Math.max(res, sum);
                if (grumpy[left] == 1) {
                    sum -= customers[left];
                }
                left++;
            }

        }
        return res;
    }
}
