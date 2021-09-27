package com.leetcode.medium.review.dp;

/**
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件
 * 中的一个，我们就称这个数组为一个优美的排列。条件：
 *
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 *
 * N 是一个正整数，并且不会超过15。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/beautiful-arrangement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode526 {
    public int countArrangement(int n) { // 回溯解法通过但是效率比较低
        // 排列的长度 n 至多为 15，因此我们可以用一个位数为 n 的二进制数 mask 表示排列中的数被选取的情况
        int[] f = new int[1 << n];
        f[0] = 1;
        // n = 4,mask=(0110)2 表示1~4的整数中,数字 2,3 都已经被选取，并以任意顺序放置在排列中前两个位置
        for (int mask = 1; mask < (1 << n); mask++) {
            int num = Integer.bitCount(mask); // 下一个数字要放在排列中的位置
            for (int i = 0; i < n; i++) {
                //  mask 中的第 i 位为 1（从 0 开始编号），则数 i+1 已经被选取
                if ((mask & (1 << i)) != 0
                        && ((num % (i + 1)) == 0 || (i + 1) % num == 0)) { // num的范围是1~15所以这里num不用减1
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
