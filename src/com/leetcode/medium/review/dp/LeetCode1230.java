package com.leetcode.medium.review.dp;

/**
 * 有一些不规则的硬币。在这些硬币中，prob[i] 表示第 i 枚硬币正面朝上的概率。
 *
 * 请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：prob = [0.4], target = 1
 * 输出：0.40000
 * 示例 2：
 *
 * 输入：prob = [0.5,0.5,0.5,0.5,0.5], target = 0
 * 输出：0.03125
 *
 * dp[i][j] = dp[i - 1][j - 1] * prob[i - 1] + dp[i - 1][j] * (1.0 - prob[i - 1]);
 * 意思是说前i枚硬币里面有j枚朝上的概率等于前i-1枚硬币里有j-1枚硬币朝上的概率乘以当前第i枚硬币朝上的概率再加上前i-1枚硬币里有j枚硬币朝上的概率乘以当前第i枚硬币朝下的概率。
 * dp[i][j]表示从0到第i枚硬币正面朝上个数等于j的概率
 */
public class LeetCode1230 {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double[][] dp = new double[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j - 1] * prob[i - 1] + dp[i - 1][j] * (1 - prob[i - 1]);
            }
        }
        return dp[n][target];
    }
}

//class Solution {
//public:
//    double probabilityOfHeads(vector<double>& prob, int target) {
//        int n = prob.size();
//        double* d = new double[n+1];
//        memset(d, 0.0, sizeof(int)*(n+1));
//        d[0] = 1;
//
//        for (int i = 0; i < n; ++i)
//        {
//            for (int j = min(target, i+1); j >= 0; --j)
//            {
//                if (j > 0)
//                {
//                    d[j] =  d[j-1] * prob[i] + d[j] * (1-prob[i]);
//                }
//                else
//                {
//                    d[j] *= (1-prob[i]);
//                }
//            }
//        }
//
//        return d[target];
//    }
//};
//
