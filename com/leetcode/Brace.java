
/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 功能描述
 *
 * @since 2020-04-09
 */
public class Brace {

    void dfsParenthesis(List<String> res, String str, int left, int right, int n) {
        if (left > n || right > n || right > left) {
            return;
        }

        if (left == n && right == n) {
            res.add(str);
            return;
        }
        dfsParenthesis(res, str + '(', left + 1, right, n);
        dfsParenthesis(res, str + ')', left, right + 1, n);
    }

    public List<String> generateParenthesis(int n) {
        String str = new String();
        List<String> res = new LinkedList<>();
        dfsParenthesis(res, str, 0, 0, n);
        return res;
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

    boolean isInterval(Integer[] a, Integer[] b) {
        if (a[0] > b[1] || a[1] < b[0]) {
            return false;
        }
        return true;
    }

    public int spreadNoise(int n, int m, int[][] noise) {
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
