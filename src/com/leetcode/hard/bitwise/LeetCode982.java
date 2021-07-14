package com.leetcode.hard.bitwise;

/**
 * 给定一个整数数组 A，找出索引为 (i, j, k) 的三元组，使得：
 *
 * 0 <= i < A.length
 * 0 <= j < A.length
 * 0 <= k < A.length
 * A[i] & A[j] & A[k] == 0，其中 & 表示按位与（AND）操作符。
 *
 * 输入：[2,1,3]
 * 输出：12
 * 解释：我们可以选出如下 i, j, k 三元组：
 * (i=0, j=0, k=1) : 2 & 2 & 1
 * (i=0, j=1, k=0) : 2 & 1 & 2
 * (i=0, j=1, k=1) : 2 & 1 & 1
 * (i=0, j=1, k=2) : 2 & 1 & 3
 * (i=0, j=2, k=1) : 2 & 3 & 1
 * (i=1, j=0, k=0) : 1 & 2 & 2
 * (i=1, j=0, k=1) : 1 & 2 & 1
 * (i=1, j=0, k=2) : 1 & 2 & 3
 * (i=1, j=1, k=0) : 1 & 1 & 2
 * (i=1, j=2, k=0) : 1 & 3 & 2
 * (i=2, j=0, k=1) : 3 & 2 & 1
 * (i=2, j=1, k=0) : 3 & 1 & 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triples-with-bitwise-and-equal-to-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode982 {
    public int countTriplets(int[] nums) {
        // 遍历数组， 确定形如100..00的上界， 以便之后可以用k-1作为掩码
        int k = 1;
        for (int item : nums) {
            while (k <= item) {
                k <<= 1;
            }
        }
        // mem[i]: 记录对于每个特定的值 i， 数组 nums 里的值num & i 等于 0 的元素数量
        int[] mem = new int[k];
        for (int item : nums) {
            // 需要在特定mask之下降序遍历
            int mask = (k - 1) ^ item; // 所有1的位置和item完全无重合的值位与item都是0，所以这里用了异或
            int i = mask;
            while (true) {
                mem[i]++;
                if (i == 0) break;
                i = (i - 1) & mask; // 关键算法，别记错了,与1178题的枚举二进制子集的方法是一样的
            }
        }
        int ans = 0;
        for (int a : nums) {
            for (int b : nums) {
                // 利用之前的mem累加出答案
                ans += mem[a & b];
            }
        }
        return ans;
       // 链接：https://leetcode-cn.com/problems/triples-with-bitwise-and-equal-to-zero/solution/java-shuang-bai-by-hoyt-lin-qn3o/
    }
}
