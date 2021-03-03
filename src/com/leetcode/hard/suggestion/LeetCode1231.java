package com.leetcode.hard.suggestion;

/**
 * 你有一大块巧克力，它由一些甜度不完全相同的小块组成。我们用数组 sweetness 来表示每一小块的甜度。
 *
 * 你打算和 K 名朋友一起分享这块巧克力，所以你需要将切割 K 次才能得到 K+1 块，每一块都由一些 连续 的小块组成。
 *
 * 为了表现出你的慷慨，你将会吃掉 总甜度最小 的一块，并将其余几块分给你的朋友们。
 *
 * 请找出一个最佳的切割策略，使得你所分得的巧克力 总甜度最大，并返回这个 最大总甜度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * 输出：6
 * 解释：你可以把巧克力分成 [1,2,3], [4,5], [6], [7], [8], [9]。
 * 示例 2：
 *
 * 输入：sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * 输出：1
 * 解释：只有一种办法可以把巧克力分成 9 块。
 * 示例 3：
 *
 * 输入：sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * 输出：5
 * 解释：你可以把巧克力分成 [1,2,2], [1,2,2], [1,2,2]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-chocolate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1231 {
    public int maximizeSweetness(int[] sweetness, int K) {
        int sum=0,ans=0;
        for(int i=0;i<sweetness.length;i++)
            sum+=sweetness[i];
        if(K==0) return sum;
        int l=0,r=sum/K+1;
        while(l<=r)
        {
            int mid=(l+r)/2;
            if(check(sweetness,mid,K))
            {
                ans=mid;
                l=mid+1;
            }
            else
                r=mid-1;
        }
        return ans;
    }

    private boolean check(int[] sweetness,int sum,int k)
    {
        int nowsum=0,num=0;
        for(int i=0;i<sweetness.length;i++)
        {
            nowsum+=sweetness[i];
            if(nowsum>=sum)
            {
                num++;
                nowsum=0;
            }
        }
        return num>=k+1;
    }
}
