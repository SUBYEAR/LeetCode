package com.leetcode.hard.greedy;

/**
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 
 * 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/patching-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode330_W {
    /* 具体实现方面，任何时候都应满足区间 [1,x−1] 内的所有数字都被覆盖。令 x 的初始值为 1，数组下标 index 的初始值为 0，
       则初始状态下区间 [1,x-1] 为空区间，满足区间内的所有数字都被覆盖。进行如下操作。
       如果 index 在数组 nums 的下标范围内且 nums[index]≤x，则将 nums[index] 的值加给 xx，并将 index 的值加 1。
       被覆盖的区间从 [1,x-1][ 扩展到 [1,x+nums[index]−1]，对 x 的值更新以后，被覆盖的区间为 [1,x-1]。

       否则，x 没有被覆盖，因此需要在数组中补充 xx，然后将 x 的值乘以 2。
       在数组中补充 x 之后，被覆盖的区间从 [1,x−1] 扩展到 [1,2x−1]，对 x 的值更新以后，被覆盖的区间为 [1,x−1]。
       重复上述操作，直到 x 的值大于 n。
     */
    public int minPatches(int[] nums, int n) {
        int cnt = 0;
        long x = 1;
        int index = 0;
        int len = nums.length;
        while (x <= n) {
            if (index < len && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x = 2 * x;
                cnt++;
            }
        }
        return cnt;
    }
}
