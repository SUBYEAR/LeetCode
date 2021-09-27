package com.leetcode.hard;

/**
 * 给你一个由正整数组成的数组 nums 。
 *
 * 数字序列的 最大公约数 定义为序列中所有整数的共有约数中的最大整数。
 *
 * 例如，序列 [4,6,16] 的最大公约数是 2 。
 * 数组的一个 子序列 本质是一个序列，可以通过删除数组中的某些元素（或者不删除）得到。
 *
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 计算并返回 nums 的所有 非空 子序列中 不同 最大公约数的 数目 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-different-subsequences-gcds
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1819_W {
    int[] f = new int[200010];
    // 枚举所有可能的最大公约数，判断是否为某个子序列的公约数。因此，我们要解决的问题就是如何判断一个数是否为某个子序列的公约数
    public int countDifferentSubsequenceGCDs(int[] nums) {
        // 如果我们希望构造出一个序列，使得该序列的最大公约数为 g，那么我们应该在数组中选择 g 的倍数
        // 否则构造出的序列的最大公约数一定就不是 g 的倍数
        int maxn= 0;
        int ans = 0;
        for (int i = 0;  i < nums.length; i++) {
            maxn = Math.max(maxn, nums[i]);
            f[nums[i]]++;
            if (f[nums[i]] == 1) {
                ans++;
            }
        }

        for (int i = 1; i <= maxn; i++) {
            if (f[i] != 0) {
                continue;
            }
            int g = 0;
            // 枚举每一个数i，判断2i、3i、4*i…..的公约数是否为i，如果为i，则贡献加一
            for (int j = i; j <= maxn; j += i) {
                if (f[j] != 0) {
                    g = gcd(j, g);
                    if (g == i) {
                        break;
                    }
                }
            }
            if (g == i) {
                ans++;
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

}
