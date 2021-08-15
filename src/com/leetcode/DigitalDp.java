package com.leetcode;

import java.util.Arrays;

public class DigitalDp {
    // https://zhuanlan.zhihu.com/p/50791875
    // https://www.jianshu.com/p/1434fd741362
    // https://blog.csdn.net/wust_zzwh/article/details/52100392
    // 数位dp：统计一个区间[left, right]内满足一些条件个数的个数

    // 给定两个正整数 a 和 b，求在 [a,b] 中的所有整数中，每个数码(digit)各出现了多少次。
    long[][] dp = new long[20][20];
    int[] num = new int[20];
    public long[] countDigitInRange(long a, long b) {
        long[] ans = new long[10]; // 十进制
        for (int i = 0; i < 10; i++) {
            ans[i] = solve(b, i) - solve(a - 1, i);
        }
        return ans;
    }

    private long solve(long x ,int digit) {
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int len = 0;
        while (x != 0) {
            num[++len] = (int) (x % 10);
            x /= 10;
        }
        // 从最高位开始枚举肯定是有限制并且有前导零的
        return dfs(len, true, true, digit, 0);
    }

    /**
     *
     * @param pos     位置
     * @param limit   数位上界变量
     * @param lead    为true表示有前导0
     * @param digit   当前处理的数码
     * @param sum
     * @return
     */
    private long dfs(int pos, boolean limit, boolean lead, int digit, int sum) {
        long ans = 0;
        if (pos <= 0) { // 边界条件
            return sum;
        }
        if (!limit && !lead && dp[pos][sum] != -1) { // 如果limit为false那么后面的状态和up值没有关系了，可以记录下来
            return dp[pos][sum];
        }
        int up = limit ? num[pos] : 9;
        for (int j = 0; j <= up; j++) {
            int add = !(lead && j == 0) && j == digit ? 1 : 0; // 没有前导0且为需要的数码
            ans += dfs(pos - 1, j == num[pos] && limit, lead && j == 0, digit, sum + add);
        }
        if (!limit && !lead) dp[pos][sum] = ans;
        return ans;
    }

    // 题意：求[l,r]之间的Round Number数，RN数即化为二进制后0的个数不少于1的个数的数。
    //思路：之前用组合数求写过，最近学数位dp，又用数位dp来写一次。用dp[pos][n0][n1]表示长为pos+1的数（我从0开始定义的），之前已经
    // 有n0个0和n1个1的前提下RN数有多少，用lead表示是否前导0，最后的递归终止条件为if(pos==-1) return n0>=n1。
    // #include<cstdio>
    //#include<cstring>
    //using namespace std;
    //
    //int a[35],dp[35][35][35];
    //
    //int dfs(int pos,int n0,int n1,bool lead,bool limit){
    //    if(pos==-1) return n0>=n1;
    //    if(!limit&&!lead&&dp[pos][n0][n1]!=-1) return dp[pos][n0][n1];
    //    int up=limit?a[pos]:1,tmp=0;
    //    for(int i=0;i<=up;++i){
    //        if(lead){
    //            if(!i) tmp+=dfs(pos-1,n0,n1,lead&&!i,limit&&i==a[pos]);
    //            else tmp+=dfs(pos-1,n0,n1+1,lead&&!i,limit&&i==a[pos]);
    //        }
    //        else{
    //            if(!i) tmp+=dfs(pos-1,n0+1,n1,lead&&!i,limit&&i==a[pos]);
    //            else tmp+=dfs(pos-1,n0,n1+1,lead&&!i,limit&&i==a[pos]);
    //        }
    //    }
    //    if(!limit&&!lead) dp[pos][n0][n1]=tmp;
    //    return tmp;
    //}
    //
    //int solve(int x){
    //    int pos=0;
    //    while(x){
    //        a[pos++]=x%2;
    //        x/=2;
    //    }
    //    return dfs(pos-1,0,0,true,true);
    //}
    //
    //int main(){
    //    memset(dp,-1,sizeof(dp));
    //    int l,r;
    //    scanf("%d%d",&l,&r);
    //    printf("%d",solve(r)-solve(l-1));
    //    return 0;
    //}
}
