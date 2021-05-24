package com.leetcode.medium;

/**
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 *
 * 不要使用系统的 Math.random() 方法。
 *
 * 提示:
 *
 * rand7 已定义。
 * 传入参数: n 表示 rand10 的调用次数。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode470 {
    private int rand7() {return 0;}
    // 用拒绝采样的方法实现 Rand10()。在拒绝采样中，如果生成的随机数满足要求，那么久返回该随机数，否则会不断生成直到一个满足要求的随机数为止。
    // 若我们调用两次 Rand7()，那么可以生成 [1, 49] 之间的随机整数，我们只用到其中的 40 个，用来实现 Rand10()，而拒绝剩下的 9 个数
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }

    // 如果a > b，那么一定可以用Randa去实现Randb。其中， Randa表示等概率生成1到a的函数，Randb表示等概率生成1到b的函数。代码如下
    // int Randb(){
    //    int x = ~(1<<31); // max int
    //    while(x > b)
    //        x = Randa();
    //    return x;
    //}
    // 构造Randa2 = a * (Randa – 1) + Randa， 表示生成1到a2 随机数的函数。如果a2 仍小于b，
    // 继教构造 Randa3 = a * (Randa2 – 1) + Randa…直到ak > b，这时我们得到Randak , 我们记为RandA
    // 如果给你两个生成随机数的函数Randa和Randb， 你可以通过以下方式轻松构造Randab，生成1到a*b的随机数。
    // Randab = b * (Randa - 1) + Randb
    // Randab = a * (Randb - 1) + Randa
}
