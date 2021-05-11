/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 功能描述 bfs中在选择列表中更新队列的时候有三种判断条件：(1)访问标记法；(2)距离比较法；(3)源数组给定值可访问性约束法
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
        Arrays.sort(noise, (o1, o2) -> o2[2] - o1[2]); // 噪音值降序排列了
        int maxDb = noise[0][2];
        int noiseIndex = 0;
        Queue<GridPoint> noiseQueue = new LinkedList();
        // 第一次get时已经把最大的噪音值加入了队列，返回值的index是1
        noiseIndex = getIndexForDb(noiseIndex, noiseMap, noiseQueue, noise, maxDb);
        while (!noiseQueue.isEmpty()) {
            maxDb--;
            if (maxDb == 0) {
                break;
            }
            int size = noiseQueue.size();
            for (int i = 0; i < size; i++) {
                GridPoint curGridPoint = noiseQueue.poll();
                addOthePoint(curGridPoint, noiseMap, noiseQueue, maxDb);
            }
            // 噪音一直在传播，返回的index可能是2，如果在maxDb递减的时候和index为1的噪音值相等了。
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
            if (noise[i][2] != maxDb) { // 噪音数组已经是按照降序排列了，如果不等肯定是下一个最大的噪音值，关键是因为maxDb是以减1的方式在减小
                noiseIndex = i;
                return noiseIndex;
            }
            if (noiseMap[noise[i][0]][noise[i][1]] == 0) { // 最大的噪音值有多个加入到队列中
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

    public int[][] updateMatrix(int[][] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length];
        boolean[][] visit = new boolean[matrix.length][matrix[0].length];
        Queue<Integer[]> q = new LinkedList<>();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    continue;
                }
                res[i][j] = 0;
                visit[i][j] = true;
                q.offer(new Integer[] {i, j});
            }
        }

        while (!q.isEmpty()) {
            Integer[] pos = q.poll();
            for (int i = 0; i < dx.length; i++) {
                int x = pos[0] + dx[i];
                int y = pos[1] + dy[i];
                if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visit[x][y]) {
                    visit[x][y] = true;
                    res[x][y] = res[pos[0]][pos[1]] + 1;
                    q.offer(new Integer[] {x, y});
                }
            }
        }

        return res;
    }

    public int spreadNoise_2(int n, int m, int[][] noise) {
        if (noise == null || noise.length == 0) {
            return 0;
        }

        int[][] matrix = new int[n][m];
        int sum = 0;

        for (int[] input : noise) {
            spread(matrix, input[0], input[1], input[2]);
            for (int[] arr : matrix) {
                for (int value : arr) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------");
        }

        for (int[] arr : matrix) {
            for (int value : arr) {
                sum += value;
            }
        }
        return sum;
    }

    private void spread(int[][] matrix, int row, int col, int value) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return;
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {row, col});
        matrix[row][col] = Math.max(value, matrix[row][col]);
        while (!que.isEmpty()) {
            int[] pos = que.remove();

            for (int index = 0; index < dx.length; index++) {
                int xNew = pos[0] + dx[index];
                int yNew = pos[1] + dy[index];
                if (xNew >= 0 && xNew < matrix.length && yNew >= 0 && yNew < matrix[0].length) {
                    if (matrix[xNew][yNew] == 0) {
                        matrix[xNew][yNew] = matrix[pos[0]][pos[1]] - 1;
                        if (matrix[xNew][yNew] > 1) {
                            que.add(new int[] {xNew, yNew});
                        }
                    } else {
                        if (matrix[pos[0]][pos[1]] - 1 > matrix[xNew][yNew]) {
                            que.add(new int[] {xNew, yNew});
                        }
                        matrix[xNew][yNew] = Math.max(matrix[xNew][yNew], matrix[pos[0]][pos[1]] - 1);
                    }
                }
            }
        }
    }
}
