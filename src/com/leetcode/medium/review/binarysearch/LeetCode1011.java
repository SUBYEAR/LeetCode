package com.leetcode.medium.review.binarysearch;

import java.util.Arrays;

/**
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 提示：
 * <p>
 * 1 <= D <= weights.length <= 5 * 104
 * <p>
 * 1 <= weights[i] <= 500
 * <p>
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
                sum = 0;
            }
            sum += weights[i];
        }

        return cnt <= D;
    }

    //判断最低运载能力为H的时候能否在D天内送达
    public boolean verification(int[] weights, int D, int H){
        //天数计数，初始化为1
        int count = 1;
        //每天的包裹总量
        int singleWeight = 0;
        for(int i = 0; i < weights.length; ++i){
            //累计包裹总量
            singleWeight += weights[i];
            //如果累计包裹总量singleWeight > H，天数+1
            if(singleWeight > H){
                ++count;
                singleWeight = weights[i];
            }
            //如果当前累计的天数count > D，说明当前H不满足条件，返回false
            if(count > D){
                return false;
            }
        }
        //说明当前H满足条件，返回true
        return true;
    }
}
