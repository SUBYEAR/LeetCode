package com.leetcode.medium.review.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 你打算做甜点，现在需要购买配料。目前共有 n 种冰激凌基料和 m 种配料可供选购。而制作甜点需要遵循以下几条规则：
 * <p>
 * 必须选择 一种 冰激凌基料。
 * 可以添加 一种或多种 配料，也可以不添加任何配料。
 * 每种类型的配料 最多两份 。
 * 给你以下三个输入：
 * <p>
 * baseCosts ，一个长度为 n 的整数数组，其中每个 baseCosts[i] 表示第 i 种冰激凌基料的价格。
 * toppingCosts，一个长度为 m 的整数数组，其中每个 toppingCosts[i] 表示 一份 第 i 种冰激凌配料的价格。
 * target ，一个整数，表示你制作甜点的目标价格。
 * 你希望自己做的甜点总成本尽可能接近目标价格 target 。
 * <p>
 * 返回最接近 target 的甜点成本。如果有多种方案，返回 成本相对较低 的一种。
 * 输入：baseCosts = [1,7], toppingCosts = [3,4], target = 10
 * 输出：10
 * 解释：考虑下面的方案组合（所有下标均从 0 开始）：
 * - 选择 1 号基料：成本 7
 * - 选择 1 份 0 号配料：成本 1 x 3 = 3
 * - 选择 0 份 1 号配料：成本 0 x 4 = 0
 * 总成本：7 + 3 + 0 = 10 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closest-dessert-cost
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1774 {
    int res=Integer.MAX_VALUE;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        for(int base: baseCosts){
            dfs(0,base,target,toppingCosts);
        }
        return res;
    }
    private void dfs(int cur,int cost,int target,int[] toppingCosts){
        if(Math.abs(target-cost)<Math.abs(target-res)||Math.abs(target-cost)==Math.abs(target-res)&&cost<res){
            res=cost;
        }
        if(cost>=target||cur==toppingCosts.length){
            return;
        }
        dfs(cur+1,cost,target,toppingCosts);
        dfs(cur+1,cost+toppingCosts[cur],target,toppingCosts);
        dfs(cur+1,cost+2*toppingCosts[cur],target,toppingCosts);
        // 使用for循环的规则是
        // for(int i=cur;i<toppingCosts.length;i++){
        //    dfs(i+1,cost+toppingCosts[i],target,toppingCosts);
        //    dfs(i+1,cost+2*toppingCosts[i],target,toppingCosts);
        // }
    }

//    链接：https://leetcode-cn.com/problems/closest-dessert-cost/solution/liang-chong-xie-fa-de-qing-xi-dfsdui-bi-v0637/


//    int diff = 100001;
//    int ans = 0;
//
//    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
//        Arrays.sort(baseCosts);
//        Arrays.sort(toppingCosts);
//        int len = toppingCosts.length;
//        int[] times = new int[ 2 * len];
//        for (int i = 0; i < len; i++) {
//            times[ 2 * i] = toppingCosts[i];
//            times[ 2 * i + 1] = toppingCosts[i];
//        }
//        TreeSet<Integer> s = new TreeSet<>();
//        for (int base : baseCosts) {
//            diff = Math.abs(base - target);
//            ans = base;
//            s.add(ans);
//            dfs(times, 0, base, target, s);
////            System.out.println("#########ans:" + ans);
//        }
//        int res = 0;
//        int pre = 100001;
//        for (int val : s) {
//            if (Math.abs(val - target) < pre) {
//                pre = Math.abs(val - target);
//                res = val;
//            }
//        }
//        return res;
//    }
//
//    void dfs(int[] toppings, int start, int cur, int target, TreeSet<Integer> set) {
//        int tmp = diff;
//        for (int i = start; i < toppings.length; i++) {
//            int topping = toppings[i];
//            int curDiff = Math.abs(cur + topping - target);
//            if (curDiff < diff) {
//                if (curDiff <= Math.abs(ans - target)) {
//                    ans = cur + topping;
//                    set.add(ans);
//                    if (ans == target) {
//                        return;
//                    }
////                    System.out.println("update ans, " + "cur:" + cur + ", topping:" + topping);
////                    System.out.println("--------------------");
//                }
//                diff = curDiff;
//                dfs(toppings, i + 1, cur + topping, target, set);
//                diff = tmp;
//            }
//        }
//    }
}
