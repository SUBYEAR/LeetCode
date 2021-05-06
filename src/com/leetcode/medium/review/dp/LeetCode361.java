package com.leetcode.medium.review.dp;

/**
 * 想象一下炸弹人游戏，在你面前有一个二维的网格来表示地图，网格中的格子分别被以下三种符号占据：
 *
 * 'W' 表示一堵墙
 * 'E' 表示一个敌人
 * '0'（数字 0）表示一个空位
 *
 * 请你计算一个炸弹最多能炸多少敌人。
 *
 * 由于炸弹的威力不足以穿透墙体，炸弹只能炸到同一行和同一列没被墙体挡住的敌人。
 *
 * 注意：你只能把炸弹放在一个空的格子里
 *
 * 示例:
 *
 * 输入: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * 输出: 3
 * 解释: 对于如下网格
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * 假如在位置 (1,1) 放置炸弹的话，可以炸到 3 个敌人
 * <p></p>
 * f[i][j]状态表示在第i行第j列位置，放置在炸弹左边能炸到的dir数量
 * 属性：Max
 */
public class LeetCode361 {
    int[][] f1, f2, f3, f4;
    public int maxKilledEnemies(char[][] grid){
        int r = grid.length;
        if(r == 0)  return 0;
        int c = grid[0].length;
        if(c == 0)  return 0;
        f1 = new int[r + 2][c + 2]; f2 = new int[r + 2][c + 2];
        f3 = new int[r + 2][c + 2]; f4 = new int[r + 2][c + 2];
        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){
                f1[i][j] = f1[i][j - 1];
                if(grid[i - 1][j - 1] == 'E') f1[i][j]++;
                else if(grid[i - 1][j - 1] == 'W') f1[i][j] = 0;
            }

            for(int j = c; j >= 1; j--){
                f2[i][j] = f2[i][j + 1];
                if(grid[i - 1][j - 1] == 'E') f2[i][j]++;
                else if(grid[i - 1][j - 1] == 'W') f2[i][j] = 0;
            }
        }
        int ans = 0;
        for(int j = 1; j <= c; j++){
            for(int i = 1; i <= r; i++){
                f3[i][j] = f3[i - 1][j];
                if(grid[i - 1][j - 1] == 'E') f3[i][j]++;
                else if(grid[i - 1][j - 1] == 'W') f3[i][j] = 0;
            }

            for(int i = r; i >= 1; i--){
                f4[i][j] = f4[i + 1][j];
                if(grid[i - 1][j - 1] == 'E') f4[i][j]++;
                else if(grid[i - 1][j - 1] == 'W') f4[i][j] = 0;
                if(grid[i - 1][j - 1] == '0'){
                    ans = Math.max(ans, f1[i][j] + f2[i][j] + f3[i][j] + f4[i][j]);
                }
            }
        }
        return ans;
    }
}

/**
 * 动态规划：
 * dp为分别记录每个点朝四个方向走到尽头(或碰到墙)所遇到的敌人总数
 *
 * 实际上需要走两遍dp，
 * 一遍从上至下，从左往右，记录每个坐标向上，向左延伸能碰到几个敌人
 * 一遍从下至上，从右往左，记录每个坐标向下，向右延伸能碰到几个敌人
 *
 * 最后把是‘0’的坐标的记录的四个方向的值加起来，找最大值即可
 *
 */
// class Solution {
//    public int maxKilledEnemies(char[][] grid) {
//        int ans = 0;
//        if(grid.length ==0 || grid[0].length==0)
//            return ans;
//
//        int[][][] dp = new int[grid.length+2][grid[0].length+2][4];
//        int p,q;
//        for(int i=0;i<grid.length; i++){
//            for(int j=0; j<grid[0].length; j++){
//                if(grid[i][j]=='E'){
//                    dp[i+1][j+1][0] = dp[i][j+1][0]+1;
//                    dp[i+1][j+1][1] = dp[i+1][j][1]+1;
//                }else if(grid[i][j]=='W'){
//                    dp[i+1][j+1][0] = 0;
//                    dp[i+1][j+1][1] = 0;
//                }else{
//                    dp[i+1][j+1][0] = dp[i][j+1][0];
//                    dp[i+1][j+1][1] = dp[i+1][j][1];
//                }
//
//                p = grid.length-i-1;
//                q = grid[0].length-j-1;
//                if(grid[p][q]=='E'){
//                    dp[p+1][q+1][2] = dp[p+2][q+1][2]+1;
//                    dp[p+1][q+1][3] = dp[p+1][q+2][3]+1;
//                }else if(grid[p][q]=='W'){
//                    dp[p+1][q+1][2] = 0;
//                    dp[p+1][q+1][3] = 0;
//                }else{
//                    dp[p+1][q+1][2] = dp[p+2][q+1][2];
//                    dp[p+1][q+1][3] = dp[p+1][q+2][3];
//                }
//
//            }
//        }
//
//        for(int i=0;i<grid.length; i++)
//            for(int j=0; j<grid[0].length; j++){
//                if(grid[i][j]=='0'){
//                    ans = Math.max(ans, dp[i+1][j+1][0]+dp[i+1][j+1][1]+dp[i+1][j+1][2]+dp[i+1][j+1][3]);
//                }
//            }
//        return ans;
//    }
//
//}
