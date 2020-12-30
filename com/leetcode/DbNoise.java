/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 功能描述
 *
 * @since 2020-04-26
 */
public class DbNoise {
    private class GridPoint {
        int x;

        int y;

        GridPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int spreadNoise(int n, int m, int[][] noise) {
        int length = noise.length;
        if (n == 0 || m == 0 || length == 0) {
            return 0;
        }
        int[][] noiseMap = new int[n][m];
        Arrays.sort(noise, (o1, o2) -> o2[2] - o1[2]);
        int maxDb = noise[0][2];
        int noiseIndex = 0;
        Queue<GridPoint> noiseQueue = new LinkedList();
        noiseIndex = getIndexForDb(noiseIndex, noiseMap, noiseQueue, noise, maxDb);
        while (!noiseQueue.isEmpty()) {
            printMatrix(noiseMap);

            maxDb--;
            if (maxDb == 0) {
                break;
            }
            int size = noiseQueue.size();
            for (int i = 0; i < size; i++) {
                GridPoint curGridPoint = noiseQueue.poll();
                addOthePoint(curGridPoint, noiseMap, noiseQueue, maxDb);
            }
            noiseIndex = getIndexForDb(noiseIndex, noiseMap, noiseQueue, noise, maxDb);
        }
        return getResult(n, m, noiseMap);
    }

    int getResult(int n, int m, int[][] noiseMap) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result += noiseMap[i][j];
            }
        }
        return result;
    }

    int getIndexForDb(int noiseIndex, int[][] noiseMap, Queue noiseQueue, int[][] noise, int maxDb) {
        int length = noise.length;
        for (int i = noiseIndex; i < length; i++) {
            if (noise[i][2] != maxDb) {
                noiseIndex = i;
                return noiseIndex;
            }
            if (noiseMap[noise[i][0]][noise[i][1]] == 0) {
                noiseQueue.add(new GridPoint(noise[i][0], noise[i][1]));
                noiseMap[noise[i][0]][noise[i][1]] = maxDb;
            }
        }
        return length;
    }

    void addOthePoint(GridPoint curGridPoint, int[][] noiseMap, Queue noiseQueue, int maxDb) {
        int n = noiseMap.length;
        int m = noiseMap[0].length;
        int curx = curGridPoint.x;
        int cury = curGridPoint.y;
        int[][] bounds = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}, {0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int[] bound : bounds) {
            int x = curx + bound[0];
            int y = cury + bound[1];
            if (x < 0 || x >= n) {
                continue;
            }
            if (y < 0 || y >= m) {
                continue;
            }
            if (noiseMap[x][y] != 0) {
                continue;
            }
            noiseMap[x][y] = maxDb;
            noiseQueue.offer(new GridPoint(x, y));
        }
    }

    void printMatrix(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println("###########################");
    }
}
