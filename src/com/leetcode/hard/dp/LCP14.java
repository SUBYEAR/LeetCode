package com.leetcode.hard.dp;

/**
 * 给定一个整数数组 nums ，小李想将 nums 切割成若干个非空子数组，使得每个子数组最左边的数和最右边的数的最大公约数大于 1 。
 * 为了减少他的工作量，请求出最少可以切成多少个子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qie-fen-shu-zu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LCP14 {
    public int splitArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (check(nums[i], nums[j])) {
                    dp[i] = Math.min(j == 0 ? 1: dp[j - 1] + 1, dp[i]);
                }
            }
        }

        return dp[n - 1];
    }

    boolean check(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a > 1;
    }
}

// 超出内存限制的解法
//         int n = nums.length;
//        int[][] dp = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = 1;
//        }
//
//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = i + 1; j < n; j++) {
//                if (check(nums[i], nums[j])) {
//                    dp[i][j] = 1;
//                } else {
//                    dp[i][j] = dp[i + 1][j] + 1;
//                    for (int k = i + 1; k < j; k++) {
//                        if (check(nums[i], nums[k])) {
//                            dp[i][j] = Math.min(dp[i][j], 1 + dp[k + 1][j]);
//                        }
//                    }
//                }
//            }
//        }
//
//        return dp[0][n - 1];

// public int splitArray(int[] nums) {
//        // 预置 minPrime
//        int[] minPrime = new int[1000000 + 1];
//        for (int i = 2; i < minPrime.length; i++) {
//            if (minPrime[i] < 2) {
//                for (int j = i; j < minPrime.length; j += i) {
//                    minPrime[j] = i;
//                }
//            }
//        }
//
//        // 记录执行到位置的次数
//        int[] dp = new int[nums.length];
//        // 记录因子位置
//        Map<Integer,Integer> posMap=new HashMap<Integer,Integer>();
//
//        for (int i = 0; i < nums.length; i++) {
//            // 预设次数
//            dp[i] = i > 0 ? dp[i - 1] + 1 : 1;
//
//            // 分解 $nums[$i] 的因子;
//            int n = nums[i];
//            while (n > 1) {
//                int factor = minPrime[n];
//                int minIndex = i;
//                if (posMap.containsKey(factor)) {
//                    minIndex = posMap.get(factor);
//                }else {
//                     posMap.put(factor, minIndex);
//                }
//
//                if (minIndex == 0) {
//                    dp[i] = 1;
//                } else {
//                    // 和 已记录处理的位置+1 对比去一个低的
//                    if ( ( dp[minIndex - 1] + 1 ) < dp[i] ){
//                        dp[i] = dp[minIndex - 1] + 1;
//                    }
//                }
//                // 更新posMap
//                if (dp[i] < dp[minIndex]) {
//                    posMap.put(factor, i);
//                }
//                n = n / factor;
//            }
//        }
//        return dp[nums.length - 1];
//
//作者：FlagMain
//链接：https://leetcode-cn.com/problems/qie-fen-shu-zu/solution/qie-fen-shu-zu-dong-tai-gui-hua-java-by-flagmain/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。