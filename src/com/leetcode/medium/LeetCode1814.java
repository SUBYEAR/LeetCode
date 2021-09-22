package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。
 * 比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：
 *
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * 请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-nice-pairs-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1814 {
    public int countNicePairs(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            nums[i] -= reverse(nums[i]);
        }
        long ans = 0;
        final int mod = 1000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // System.out.println(map);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            ans = (ans + (((long) value * (value - 1) / 2) % mod)) % mod;
        }

        return (int) ans;
    }


    int reverse(int num) {
        int tmp = num;
        int reverse = 0;
        while (tmp > 0) {
            reverse = reverse * 10 + tmp % 10;
            tmp /= 10;
        }
        return reverse;
    }
}
