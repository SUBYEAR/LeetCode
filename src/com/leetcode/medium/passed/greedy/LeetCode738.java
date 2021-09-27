package com.leetcode.medium.passed.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode738 {
    public int monotoneIncreasingDigits(int n) {
        int num = n;
        List<Integer> arr = new ArrayList<>(11);
        while (num > 0) {
            arr.add(0,num % 10);
            num /= 10;
        }
        if (arr.size() == 1) { // n只有1位肯定满足
            return n;
        }
        int i = 1;
        for (;i < arr.size(); i++) {
            if (arr.get(i) < arr.get(i - 1)) {
                break;
            }
        }
        if (i == arr.size()) { // 已经是递增的了
            return n;
        }

        int j = i - 1; // 递增结束的地方
        while (j > 0) {
            if (!arr.get(j - 1).equals(arr.get(j))) {
                break;
            }
            j--;
        }
        arr.set(j, arr.get(j) - 1);
        for (j += 1; j < arr.size(); j++) {
            arr.set(j, 9);
        }

        num = 0;
        for (int k = 0; k < arr.size(); k++) {
            num = num * 10 + arr.get(k);
        }
        return num;
    }
}
