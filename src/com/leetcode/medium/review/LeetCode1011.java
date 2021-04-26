package com.leetcode.medium.review;

import java.util.Arrays;

/**
 *传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1011 {
    public int shipWithinDays(int[] weights, int D) {
        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(weights, mid, D)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    boolean check(int[] weights, int load, int D) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < weights.length; i++) {
            if (sum + weights[i] > load) {
                ++cnt;
                sum=0;
            }
            sum += weights[i];
        }

        return cnt <= D;
    }
}
