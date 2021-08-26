package com.leetcode.exam;

import java.util.PriorityQueue;

public class Test0813 {
    // OJ #Title_3
    /*
2021/08/13 Fri C语言专业级科目一 OJ

公司给员工发中秋节福利，由一份小礼物（goods）和一份水果（fruit）组成，备选的 goods 和 fruit 有很多种，价格各不相同
输入首先分别列出了备选 goods 和 fruit 的所有类型的价格
随后输入 num，代表公司需要准备 num 种不同的搭配（goods 和 fruit 任一不同即为不同搭配）
公司想花费最少的钱，所以请输出总价格最低的前 num 种搭配

INPUT
5
100 150 125 130 110
4
50 60 65 45
3

OUTPUT
100 45
100 50
110 45
*/

    // 思路：构建一个最大堆，遍历两个数组，将数字插入到优先队列中（当优先队列堆顶比插入数据大并且堆内元素个数大于k个元素时，堆顶弹出，然后插入新元素）。
// 待实现函数，在此函数中填入答题代码
    private static int[][] getTopTPack(int[] goodsPrice, int[] fruitsPrice, int num) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o2[0] + o2[1] == o1[0] + o1[1]) {
                return o2[0] - o1[0];
            } else {
                return o2[0] + o2[1] - o1[0] - o1[1];
            }
        }));
        addQueue(goodsPrice, fruitsPrice, num, queue);
        int[][] res = new int[num][2];
        for (int i = num - 1; i >= 0; i--) {
            res[i] = queue.poll();
        }
        return res;
    }



    public static void addQueue(int[] goodsPrice, int[] fruitsPrice, int num, PriorityQueue<int[]> queue) {
        for (int i = 0; i < goodsPrice.length; i++) {
            for (int j = 0; j < fruitsPrice.length; j++) {
                if (queue.size() >= num) {
                    int max = queue.peek()[0] + queue.peek()[1];
                    if (goodsPrice[i] + fruitsPrice[j] < max || (goodsPrice[i] + fruitsPrice[j] == max
                            && goodsPrice[i] < queue.peek()[0])) {
                        queue.poll();
                    } else {
                        continue;
                    }
                }
                queue.add(new int[] {goodsPrice[i], fruitsPrice[j]});
            }
        }
    }
}
