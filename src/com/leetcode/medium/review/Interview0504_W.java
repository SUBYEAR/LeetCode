package com.leetcode.medium.review;

/**
 * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 *
 * 示例1:
 *
 *  输入：num = 2（或者0b10）
 *  输出：[4, 1] 或者（[0b100, 0b1]）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closed-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview0504_W {
    public int[] findClosedNumbers(int num) {
        int[] ans = new int[] {-1, -1};
        if (num == Integer.MAX_VALUE) {
            return ans;
        }

        long low = Integer.lowestOneBit(num);
        while ((low & num) != 0) { // 从低位往高位数最先遇到的0
            low *= 2;
        }
        if ((low < Integer.MAX_VALUE + 1L)) {
            if (low == Integer.highestOneBit(num) * 2L) {
                ans[0] = (int) (low + (1 << (Integer.bitCount(num) - 1)) - 1);
            } else {
                int mask = (int) low - 1;
                int rest = num & mask;
                ans[0] = num - rest + mask + (1 << (Integer.bitCount(rest) - 1));
            }
        }

        // 求最小值时要找到从高位往地位的最后一个0的位置
        int tmp = num & (num + 1); // 可以得到所求的0位置
        if (tmp != 0) { // 排除num为1时找到最末尾了
            int cnt = Integer.bitCount(num - tmp) + 1;
            int lowestOneBit = Integer.lowestOneBit(tmp);
            tmp -= lowestOneBit;
            while (cnt != 0) {
                tmp += (lowestOneBit / 2);
                lowestOneBit /= 2;
                cnt--;
            }
            ans[1] = tmp;
        }
        return ans;
    }
}
