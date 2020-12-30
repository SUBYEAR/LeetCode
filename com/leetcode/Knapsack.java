/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 功能描述
 *
 * @since 2020-04-24
 */
public class Knapsack {
    private static class BagItem {
        int weight; // 物品重量

        int val; // 物品价值

        int index; // 物品序号

        Double cost; // 物品单位价值

        BagItem(int weight, int val, int index) {
            this.weight = weight;
            this.val = val;
            this.index = index;
            cost = new Double(val / weight);
        }
    }

    static class ReturnValue {
        double[] valcount; // 每种物品装包的比例

        double totalvalue; // 最大的总价值

        ReturnValue(int length) {
            valcount = new double[length];
            totalvalue = 0d;
        }
    }

    static class BagItemComparator implements Comparator<BagItem> {
        @Override
        public int compare(BagItem bag1, BagItem bag2) {
            return bag2.cost.compareTo(bag1.cost);
        }
    }

    private static ReturnValue knapsack(int[] weight, int[] val, int capacity) {
        // 初始化bagItems
        BagItem[] bagItems = new BagItem[weight.length];
        for (int i = 0; i < weight.length; i++) {
            bagItems[i] = new BagItem(weight[i], val[i], i);
        }
        // 按照物品单位价值大小进行排序,降序
        Arrays.sort(bagItems, new BagItemComparator());
        // 初始化返回值
        ReturnValue returnValue = new ReturnValue(weight.length);
        // 初始化剩余容量
        int restCapacity = capacity;
        for (BagItem i : bagItems) {
            int curWeight = i.weight;
            int curValue = i.val;
            // 背包剩余容量可以装入当前物品
            if (restCapacity - curWeight >= 0) {
                restCapacity -= curWeight;
                returnValue.totalvalue = returnValue.totalvalue + curValue;
                returnValue.valcount[i.index] = 1;
            } else { // 背包剩余容量不能装入当前物品，则进行分割，将背包装满
                double proportion = (double) restCapacity / curWeight;
                restCapacity = (int) (restCapacity - curWeight * proportion);
                returnValue.totalvalue = returnValue.totalvalue + curValue * proportion;
                returnValue.valcount[i.index] = proportion;
            }
        }
        return returnValue;
    }
}
