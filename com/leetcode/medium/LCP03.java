/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

/**
 * LCP 03. 机器人大冒险
 *
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 *
 * https://leetcode-cn.com/problems/programmable-robot/
 *
 * @since 2020-05-20
 */
public class LCP03 {
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        // 指令集
        char[] commands = command.toCharArray();
        // 记录第一轮走过的所有点
        int[][] path = new int[commands.length][2];
        int xStep = 0, yStep = 0;
        for (int i = 0; i < commands.length; i++) {
            char c = commands[i];
            switch (c) {
                case ('U'):
                    // 获取指令集对应的坐标
                    yStep += +1;
                    break;
                case ('R'):
                    // 获取指令集对应的坐标
                    xStep += 1;
                    break;
                default:
                    break;
            }
            path[i][1] = yStep;
            path[i][0] = xStep;
        }

        // 判断是否会撞墙
        for (int[] obstacle : obstacles) {
            if (obstacle[0] >= x && obstacle[1] >= y) {
                continue;
            }
            if (checkPoint(path, obstacle[0], obstacle[1], xStep, yStep)) {
                return false;
            }
        }
        // 判断是否会到达终点
        return checkPoint(path, x, y, xStep, yStep);

    }

    public boolean checkPoint(int[][] paths, int x, int y, int xStep, int yStep) {
        // 其后每一轮的的路径都与第一轮路径相同，所以只要后面的路径中
        // x,y坐标值与第一轮所有点的路径上对应点的坐标有关
        // (x-x1)%xStep=0 (y-y1)%yStep = 0
        for (int[] path : paths) {
            if ((x - path[0]) % xStep == 0 && (y - path[1]) % yStep == 0
                && (x - path[0]) / xStep == (y - path[1]) / yStep) {
                return true;
            }
        }
        return false;
    }
}
