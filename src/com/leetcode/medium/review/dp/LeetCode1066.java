package com.leetcode.medium.review.dp;

/**
 * 在由 2D 网格表示的校园里有 n 位工人（worker）和 m 辆自行车（bike），n <= m。所有工人和自行车的位置都用网格上的 2D 坐标表示。
 *
 * 我们为每一位工人分配一辆专属自行车，使每个工人与其分配到的自行车之间的曼哈顿距离最小化。
 *
 * p1 和 p2 之间的曼哈顿距离为 Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|。
 *
 * 返回每个工人与分配到的自行车之间的曼哈顿距离的最小可能总和。
 *
 * 输入：workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * 输出：6
 * 解释：
 * 自行车 0 分配给工人 0，自行车 1 分配给工人 1 。分配得到的曼哈顿距离都是 3, 所以输出为 6 。
 *
 * state 是二進制表示的 狀態
 * state & （1<<i）的意思是取并集
 * state | （1<<i）的意思是取交集
 */
public class LeetCode1066 {
    int[][] workers;
    int[][] bikes;
    public int assignBikes(int[][] workers, int[][] bikes) {
        this.workers = workers;
        this.bikes = bikes;
        int m = bikes.length;
        int[] dp = new int[1 << m];
        return dfs(0,0,dp);

    }

    private int dfs(int workerIndex, int state, int[] dp) {
        if (workerIndex == workers.length) return 0;
        int res = Integer.MAX_VALUE;
        if ( dp[state] != 0) return dp[state];
        for (int i = 0; i < bikes.length; i++) {
            if ((state & (1 << i)) == 0) {
                res = Math.min(res, dfs(workerIndex + 1, (state | (1 << i)), dp) + manDis(workerIndex, i));
            }
        }
        dp[state] = res;
        return res;
    }

    private int manDis(int i, int j) {
        return Math.abs(workers[i][0]-bikes[j][0]) + Math.abs(workers[i][1]-bikes[j][1]);
    }
}

// 原始的暴力递归方法
// class Solution {
//    public int assignBikes(int[][] workers, int[][] bikes) {
//        int[][] cost = new int[workers.length][bikes.length];
//        for (int i = 0; i < workers.length; i++) {
//            for (int j = 0; j < bikes.length; j++) {
//                cost[i][j] = Math.abs(workers[i][0]-bikes[j][0]) + Math.abs(workers[i][1]-bikes[j][1]);
//            }
//        }
//        return assign(workers, bikes, cost, 0, new int[bikes.length]);
//    }
//
//    private int assign(int[][] workers, int[][] bikes, int[][] cost, int workerIdx, int[] usedBikes){
//        if(workerIdx == workers.length) {
//            return 0;
//        }
//
//        int minCost = Integer.MAX_VALUE;
//        for (int bi = 0; bi < bikes.length; bi++){
//            if (usedBikes[bi] == 0) {
//                usedBikes[bi] = 1;
//                minCost = Math.min(minCost, cost[workerIdx][bi] + assign(workers, bikes, cost, workerIdx+1, usedBikes));
//                usedBikes[bi] = 0;
//            }
//        }
//
//        return minCost;
//    }
//}
// 稍稍进行转换，我们可以用bitmap来保存哪些worker被分配自行车的状态，从第0个worker开始，还没被分配就记为1，分配了就记0，
// 这样每个worker对应bitmap的一位，最后得到一个类似于11110000（前4个worker都已经被分配了，后四个还没被分配）的bitmap，这个就是worker分配的状态。
//
//同理，1100011000 可以表示一种bikes被分配的状态：0，1，2，5， 6，7 位置的bike都被分配了，还剩 3，4，8，9 位置的bikes没被分配。
//
//然后我们有多少可能的状态呢？对于每个worker都只有两种状态：分配到了自行车（0），或者没有（即还可以分配自行车，记为1）。
// 所以总共状态数量是 Math.pow(2, workers.length)，这个也就是dp矩阵代表worker那一维的大小。同理，自行车分配可能有Math.pow(2, bikes.length)种状态。
//
//转化后的代码如下：
// class Solution {
//    int[][] dp;
//    public int assignBikes(int[][] workers, int[][] bikes) {
//        dp = new int[1<<workers.length)][i<<bikes.length];
//        int[][] cost = new int[workers.length][bikes.length];
//        int aw = 0, ab = 0; // aw: available workers; ab: available bikes
//        for(int i = 0; i < workers.length; i++) {
//            aw ^= (1<<i);
//            for (int j = 0; j < bikes.length; j++) {
//                if (i==0) {
//                    ab ^= (1<<j);
//                }
//                cost[i][j] = Math.abs(workers[i][0]-bikes[j][0]) + Math.abs(workers[i][1]-bikes[j][1]);
//            }
//        }
//        return assign(workers, bikes, cost, 0, aw, ab);
//    }
//
//    /**
//     * wi: index of worker
//     * aw: bitmap value of available workers
//     * ab: bitmap value of available bikes
//     **/
//    private int assign(int[][] w, int[][] b, int[][] cost, int wi, int aw, int ab){
//        if(aw == 0) {
//            dp[aw][ab] = 0;
//            return 0;
//        }
//        if(dp[aw][ab] != 0) {
//            return dp[aw][ab];
//        }
//
//        int minCost = Integer.MAX_VALUE;
//        int naw = aw^(1<<wi);
//        for(int bi = 0; bi < b.length; bi++){
//            if(((ab>>bi)&1) == 1){
//                int nab = ab^(1<<bi);
//                minCost = Math.min(minCost, cost[wi][bi] + assign(w, b, cost, wi+1, naw, nab));
//            }
//        }
//        dp[aw][ab] = minCost;
//        return minCost;
//    }
//}


//  回溯算法
//思路：
//按照提示的方法求解。
//
//用回溯算法+记忆化求解。
//
//设d[cur][S]表示到自行车cur，未分配的工人集合为S时的最小曼哈顿距离。
// d[cur][S] = -1;//-1表示未计算。d[cur][S] = INT_MAX，表示计算过了，但是无效值
//
//dfs(cur,S){
//    if(d[cur][S] != -1) 返回d[cur][S];
//    if(S == 0){
//        返回d[cur][S] = 0;
//    }
//
//    if(cur == N){
//        返回d[cur][S] = INT_MAX;
//    }
//
//    a = dfs(cur+1,S);//不用自行车cur
//    if(a != INT_MAX){//有效值才取最小值
//        d[cur][S] = min(d[cur][S], a);
//    }
//    for(工人i in S){
//        为工人i分配S;
//        rs = 工人i 与 自行车cur 的距离;
//        ret = dfs(cur+1,S - 工人i);
//        if(ret != INT_MAX){
//            d[cur][S] = min(d[cur][S],ret + rs);
//        }
//    }
//    返回d[cur][S];
//}
// int d[15][1030];
//class Solution {
//public:
//    int N;
//    int W;
//    int assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
//        memset(d,0xcf,sizeof(d));
//        W = workers.size();
//        N = bikes.size();
//        return dfs(0,(1 << workers.size())-1,workers,bikes);
//    }
//
//    int dfs(int cur,int S,
//            const vector<vector<int>>& workers, const vector<vector<int>>& bikes){
//        int& ans = d[cur][S];
//        if(ans > 0){
//            return ans;
//        }
//        ans = INT_MAX;
//        if(S == 0){//所有工人都分配了，值为0
//            return ans = 0;
//        }
//        if(cur == N){//有些工人还未分配，因此无效
//            return ans = INT_MAX;
//        }
//        int a = dfs(cur+1,S,workers,bikes);
//        if(a != INT_MAX){
//            ans = min(ans,a);
//        }
//        for(int i=0;i<W;++i){
//            if((S >> i) & 0x1){
//                int rs = dist(bikes[cur],workers[i]);
//                int ret = dfs(cur+1,S & ~(1 << i),workers,bikes);
//                if(ret != INT_MAX){
//                    ans = min(ans,rs + ret);
//                }
//            }
//        }
//        return ans;
//    }
//
//    int dist(const vector<int>& W, const vector<int>& B){
//        return abs(W[0] - B[0]) + abs(W[1] - B[1]);
//    }
//};
//

// 动态规划
//将解法一改为动态规划。
//
//边界值：d[cur][S] = -1;//-1表示未计算。d[cur][S] = INT_MAX，表示计算过了，但是无效值
//
//初始值：当S=0时，没有工人时，d[cur][0] = 0。当cur=N时，自行车都考虑完了。d[N][S] = INT_MAX。
//
//递推关系：要保证，S是从0到最大值。但是S的值大，不表示S对应的集合中的人数就多。因此，与解法一不同的是，不是工人少的集合S，就一定先算。
//
//解：d[0][全集];
// for(cur in [N-1,0]){
//    for(S in [0,ALL]){
//        if(S == 0) pass;//无工人不分配
//        if(d[cur][S] < 0){//无计算过
//            d[cur][S] = INT_MAX;
//        }
//        if(d[cur+1][S] != INT_MAX){//自行车cur，不分配
//            d[cur][S] = min(d[cur][S],d[cur+1][S]);
//        }
//
//        for(i in [0,W-1]){
//            if(i in S){//给工人i分配自行车cur
//                rs = 工人i 与 自行车cur 的距离;
//                rt = d[cur+1][S 去掉 工人i];
//                if(rt != INT_MAX){
//                    d[cur][S] = min(d[cur][S],rs + rt);
//                }
//            }
//        }
//    }
//}
// int d[15][1030];
//class Solution {
//public:
//    int N;
//    int W;
//    int assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
//        memset(d,0xcf,sizeof(d));
//        W = workers.size();
//        N = bikes.size();
//        const int ALL = (1 << W) - 1;
//        for(int s=0;s <= ALL;++s){
//            d[N][s] = INT_MAX;
//        }
//        for(int i=0;i<=N;++i){
//            d[i][0] = 0;
//        }
//        for(int cur = N-1;cur >= 0;cur--){
//            for(int s=0;s <= ALL;++s){
//                int& ans = d[cur][s];
//                if(s == 0){
//                    continue;
//                }
//
//                if(ans < 0){
//                    ans = INT_MAX;
//                }
//
//                if(d[cur+1][s] != INT_MAX){
//                    ans = min(ans,d[cur+1][s]);
//                }
//                for(int i=0;i < W;++i){
//                    if((s >> i) & 0x1){
//                        int rs = dist(bikes[cur],workers[i]);
//                        int rt = d[cur+1][s & ~(1 << i)];
//
//                        if(rt != INT_MAX){
//                            ans = min(ans,rs + rt);
//                        }
//                    }
//                }
//            }
//        }
//        return d[0][ALL];
//    }
//
//    int dist(const vector<int>& W, const vector<int>& B){
//        return abs(W[0] - B[0]) + abs(W[1] - B[1]);
//    }
//};


// 动态规划2
//思路：不同于解法二。
//
//设d[S][T]表示工人集合为S，自行车集合为T时的最小距离。
//
//初始值:d[0][0] = 0。S=0，T=0，表示空集。
//
//解:min{d[S][0],d[S][1],...,d[S][N - 1]}.
//
//递推关系：枚举S和T的所有情况。给S中的每个人，分配T中的自行车，求最小值。
// for(S in [0,ALL]){
//    for(T in [0,ALL]){
//        if(S为空集 || S的元素个数 != T的元素个数) pass;
//        for(工人i in S){
//            for(自行车j in T){
//                //将自行车j 分配给 工人i
//                前一个集合preS = S - 工人i;
//                前一个结合PreT = T - 自行车j;
//                d[S][T] = min(d[S][T],d[preS][preT] + (自行车j 与 工人i 的距离));
//            }
//        }
//    }
//}
//
// int d[1030][1030];
//int cnt_of_set[1030];
//class Solution {
//public:
//    int N;
//    int W;
//    int assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
//        memset(d,0x7f,sizeof(d));
//        W = workers.size();
//        N = bikes.size();
//        const int ALL = (1 << W) - 1;
//        for(int S=0;S<(1 << max(W,N));++S){
//            cnt_of_set[S] = get_cnt(S);
//        }
//
//        d[0][0] = 0;
//        int ans = INT_MAX;
//        for(int S=0;S < (1 << W);++S){
//            for(int T=0;T < (1 << N);++T){
//                if(cnt_of_set[S] == 0 || cnt_of_set[S] != cnt_of_set[T]){
//                    continue;
//                }
//                for(int i=0;i<W;++i){
//                    if(!in_set(i,S)){
//                        continue;
//                    }
//                    for(int j=0;j<N;++j){
//                        if(!in_set(j,T)){
//                            continue;
//                        }
//                        int preS = S & ~(1 << i);
//                        int preT = T & ~(1 << j);
//                        int rs = dist(workers[i],bikes[j]);
//                        d[S][T] = min(d[S][T],d[preS][preT] + rs);
//                    }
//                }
//                if(S == ALL){
//                    ans = min(ans,d[S][T]);
//                }
//            }
//        }
//        return ans;
//    }
//
//    bool in_set(int i,int s){
//        return (s >> i) & 0x1;
//    }
//
//    int get_cnt(int S){
//        int ans = 0;
//        for(int i=0;i<10;++i){
//            if((S >> i) & 0x1){
//                ans++;
//            }
//        }
//        return ans;
//    }
//
//    int dist(const vector<int>& W, const vector<int>& B){
//        return abs(W[0] - B[0]) + abs(W[1] - B[1]);
//    }
//};
//


