package com.leetcode.hard;

/**
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，
 * 而每个 1 更改为 0。
 *
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode995_W {
    // 差分数组的元素diff[i]表示A[i-1]和A[i]的翻转次数的差，对于区间[l, r]而言差分数组的每个
    // 元素加一只会对边界有影响，即只会影响到 l 和 r+1处的差分值，故 diff[l] 增加 1，
    // diff[r+1] 减少 1。通过累加差分数组可以得到当前位置需要翻转的次数，我们用变量 revCnt 来表示这一累加值。

    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            // 遍历到 A[i] 时，若 A[i]+revCnt 是偶数，则说明当前元素的实际值为 0，需要翻转区间
            // 原因：0翻转偶数次还是原始值，1翻转奇数次变成了0，此时还需要继续翻转
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1; // 注意到若 i+K>n 则无法执行翻转操作，此时应返回 −1。
                }
                ++ans;
                ++revCnt; // 直接将 revCnt 增加 1(其实对应于diff[l] 加1)，这样第23行就不同用循环diff数组取求差分数组的累加值了
                --diff[i + K]; // diff[i+K] 减少 1
            }
        }
        return ans;
    }
}
