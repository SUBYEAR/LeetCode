package com.leetcode;/*
让我们一起来玩扫雷游戏！

给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，
'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。

现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：

如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
如果在此次点击中，若无更多方块可被揭露，则返回面板。
 */

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode529 {
    int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        boolean[][] visit = new boolean[board.length][board[0].length];
        visit[click[0]][click[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            char mineCount1 = getMineCount(board, cur[0], cur[1]);
            if (mineCount1 > '0') {
                board[cur[0]][cur[1]] = mineCount1;
            } else {
                board[cur[0]][cur[1]] = 'B';
                for (int i = 0; i < dx.length; i++) {
                    int x = cur[0] + dx[i];
                    int y = cur[1] + dy[i];
                    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visit[x][y]) {
                        continue;
                    }
                    visit[x][y] = true;
                    q.add(new int[] {x, y});
                }
            }
        }

        return board;
    }

    char getMineCount(char[][] board, int row , int col) {
        char res = '0';
        for (int i = 0; i < dx.length; i++) {
            int x = row + dx[i];
            int y = col + dy[i];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                continue;
            }
            if (board[x][y] == 'M') ++res;
        }
        return res;
    }
}
