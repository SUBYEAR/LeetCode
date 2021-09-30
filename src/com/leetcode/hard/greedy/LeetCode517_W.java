package com.leetcode.hard.greedy;

import java.util.Arrays;

/**
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 *
 * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 *
 * 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的
 * 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-washing-machines
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode517_W {
    // 定义 machines[i]'= machines[i]−avg，若 machines[i]′为正则说明需要移出 machines[i]′ 件衣服，
    // 为负则说明需要移入 −machines[i]′件衣服
    // 将前 i 台洗衣机看成一组，记作 A，其余洗衣机看成另一组，记作 B
    // 若 sum[i] 为正则说明需要从 A 向 B 移入sum[i] 件衣服，为负则说明需要从 B 向 A 移入 −sum[i] 件衣服。
    // 我们分两种情况来考虑操作步数：
    // A 与 B 两组之间的衣服，最多需要 max_{i=0}^{n-1}|sum[i]|
    // 组内的某一台洗衣机内的衣服数量过多，需要向左右两侧移出衣服最多需要machines[i]'最大值的次数

    public int findMinMoves(int[] machines) {
        int tot = Arrays.stream(machines).sum();
        int n = machines.length;
        if (tot % n != 0) {
            return -1;
        }
        int avg = tot / n;
        int ans = 0, sum = 0;
        for (int num : machines) {
            num -= avg;
            sum += num;
            ans = Math.max(ans, Math.max(Math.abs(sum), num));
        }
        return ans;
    }
}
