/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 功能描述
 *
 * @since 2020-06-30
 */
public class FillColor {

    boolean visited[][];

    void init(int row, int col) {
        visited = new boolean[row][col];
    }

    int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int origColor = image[sr][sc];
        fill(image, sr, sc, origColor, newColor);
        return image;
    }

    int fill(int[][] image, int x, int y, int origColor, int newColor) {
        // 出界：超出数组边界
        if (!inArea(image, x, y)) {
            return 0;
        }

        // 已探索过的 origColor 区域
        if (visited[x][y]) {
            return 1; // 注意这里的顺序不能与下面的if语句换，因为先判断image[x][y]的color会因为被其他点探索到值变成newColor导致计算surround出错
        }

        // 碰壁：遇到其他颜色，超出 origColor 区域
        if (image[x][y] != origColor) { // 值不是originColor可能是0 或者是newColor所以这个判断要在后面
            return 0;
        }

        visited[x][y] = true;

        int surround = fill(image, x - 1, y, origColor, newColor) + fill(image, x + 1, y, origColor, newColor)
            + fill(image, x, y - 1, origColor, newColor) + fill(image, x, y + 1, origColor, newColor);

        if (surround < 4) {
            image[x][y] = newColor;
        }

        return 1;
    }

    boolean inArea(int[][] image, int x, int y) {
        return x >= 0 && x < image.length && y >= 0 && y < image[0].length;
    }
}
