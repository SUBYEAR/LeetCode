package com.leetcode.medium.review;

/**
 * 对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的子序列。
 *
 * 给定源字符串 source 和目标字符串 target，找出源字符串中能通过串联形成目标字符串的子序列的最小数量。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回 -1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：source = "abc", target = "abcbc"
 * 输出：2
 * 解释：目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列。
 * 示例 2：
 *
 * 输入：source = "abc", target = "acdbc"
 * 输出：-1
 * 解释：由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串。
 * 示例 3：
 *
 * 输入：source = "xyz", target = "xzyxz"
 * 输出：3
 * 解释：目标字符串可以按如下方式构建： "xz" + "y" + "xz"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-way-to-form-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1055 {
    // i 指向target，j 指向source;
    //每次迭代寻找target中以target.charAt(i)开头的连续子字符串所能匹配的source最长子序列;
    //具体过程：
    //初始时i=0,j=0;
    //寻找target中以target.charAt(i)开头的连续子字符串所能匹配的source最长子序列，每次过后再重新令j=0,即source从头开始；
    //在每次寻找最长子序列的过程中，若source中没有子序列可以与target的子字符串匹配，则 i 不会变化，此时返回 -1 。
    public int shortestWay(String source, String target) {
        int len = 0, i = 0;
        while(i < target.length()) {
            int temp = i, j = 0;
            //寻找target中以target.charAt(i)开头的连续子字符串所能匹配的source最长子序列
            while(j<source.length()) {
                if(source.charAt(j++)==target.charAt(i)) {
                    if(++i == target.length()) break;
                }
            }
            //若source中的子序列与target的子字符串不能匹配
            if(temp == i) return -1;
            len++;
        }
        return len;
    }
}

//class Solution {
//
//    public int shortestWay(String source, String target) {
//        int minCount=0;
//        char[] cTarget= target.toCharArray();
//        int curPos=0;
//        int targetLen=cTarget.length;
//        while(curPos<targetLen){
//              int nextPos=greedyFind(source,cTarget,curPos);
//              if(nextPos==-1) {
//                  return -1;
//              } else {
//                  curPos=nextPos;
//                  minCount++;
//              }
//        }
//        return minCount;
//    }
//
//    int greedyFind(String source,char[] target,int curPos){
//        int targetPos=curPos;
//        int sourcePos=0;
//
//        char[] cSource= source.toCharArray();
//        while(targetPos<target.length && sourcePos<cSource.length ){
//            if(cSource[sourcePos]==target[targetPos]) {
//                targetPos++;
//            }
//            sourcePos++;
//        }
//        if(targetPos==curPos){
//            return -1;
//        }
//        return targetPos;
//    }
//}

//class Solution {
//    public int shortestWay(String source, String target) {
//        int len=target.length();
//        int len2=source.length();
//        if(len==0)return 0;
//        if(len2==0)return -1;
//        int []dp=new int[len];
//
//        Arrays.fill(dp,Integer.MAX_VALUE);
//        char []t=target.toCharArray();
//        char []s=source.toCharArray();
//
//        for(int i=0;i<len;i++){
//            int last=0;
//            for(int j=i;j<len;j++){
//                boolean b=false;
//                char now=t[j];
//                for(int k=last;k<len2;k++){//接着上次的位置寻找
//                    if(s[k]==now){
//                        b=true;
//                        last=k+1;
//                        break;
//                    }
//                }
//                if(b==false){//状态转移区
//                    if(j==i)return -1;
//                    if(j==0)dp[j]=1;
//                    else dp[j]=dp[j-1]+1;
//                    break;
//                }else{
//                    if(i==0)dp[j]=1;
//                    else dp[j]=Math.min(dp[j-1]+1,Math.min(dp[i-1]+1,dp[j]));
//                }
//            }
//        }
//        return dp[len-1];
//    }
//}
//


// 设d[i][j]表示target的区间[i,j]能由source的子序列构成的子序列的最小数量。
//
//初始值：长度为1的区间，d[i][j] = 1。 其它无穷大。
//
//递推关系：
//
//
//for(len in [1,tN]){
//    for(i in [0,tN - len]){
//        j = i + len - 1;
//        if(len == 1) d[i][j] = 1;
//        else{
//            if(区间[i,j]能由source中的单个子序列构成){
//                d[i][j] = 1;
//            }else for(k=i;k<j;++k){
//                d[i][j] = min(d[i][j],d[i][k] + d[k+1][j]);
//            }
//        }
//    }
//}
//效率关键点在于快速判断“区间[i,j]能由source中的单个子序列构成”。
//
//设lens[i]表示从字符target[i]开始的连续lens[i]个字符能由source中的单个子序列构成。
//
//计算lens[i]。
//
//
//for(i < tN){
//    pos = 0;
//    start = i;
//    while(i < tN){
//        r= source.find(target[i],pos);//在source中，从pos位置开始查找字符target[i]
//        if(target[i]不存在){
//            if(pos == 0){
//                source中不存在字符target[i]。
//            }
//            break;
//        }
//        //从下一个位置开始，寻找下一个字符
//        i++;
//        pos = r+1;
//    }
//    for(j in [start,i-1]){
//        lens[j] = i - j;//单个子序列能覆盖的长度
//    }
//}
//借助lens[i]对上面的递推关系进行改进。
//递推关系：
//
//
//for(len in [1,tN]){
//    for(i in [0,tN - len]){
//        j = i + len - 1;
//        if(len == 1) d[i][j] = 1;
//        else{
//            if(lens[i] >= len){
//                d[i][j] = 1;
//            }else {
//                k = i + lens[i] - 1;
//                d[i][j] = min(d[i][j],d[i][k] + d[k+1][j]);
//            }
//        }
//    }
//}
//