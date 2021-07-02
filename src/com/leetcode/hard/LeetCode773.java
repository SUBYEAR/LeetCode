package com.leetcode.hard;

import java.util.*;

/**
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 *
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 *
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode773 {
    public int slidingPuzzle(int[][] board) {
        Set<Integer> seen = new HashSet<>();
        seen.add(hash(board));
        int target = hash(new int[][] {{1,2,3}, {4,5,0}});
        Queue<int[][]> q = new LinkedList<>();
        q.add(board);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[][] cur = q.poll();
                if (hash(cur) == target) {
                    return step;
                }

                for (int[][] nei : getNeighbor(cur,seen)) {
                    int hash = hash(nei);
                    if (seen.contains(hash)) {
                        continue;
                    }
                    seen.add(hash);
                    q.add(nei);
                }
            }
            step++;
        }
        return -1;
    }

    private static final int[] dx = new int[] { 0,0,1,-1};
    private static final int[] dy = new int[] { 1,-1,0,0};

    public List<int[][]> getNeighbor(int[][] board, Set<Integer> seen) {
        int row = board.length, col = board[0].length;
        int index = 0;
        while (index < row * col) {
            if (board[index / col][index % col] == 0) {
                break;
            }
            index++;
        }

        List<int[][]> res = new ArrayList<>();
        for (int k = 0;  k < dx.length; k++) {
            int i = index / col, j = index % col;
            if ((i + dx[k]) < 0 || (i + dx[k]) >= row || (j + dy[k]) < 0 || (j +dy[k]) >= col ) {
                continue;
            }
            int[][] pos = switchPos(board, i, j, i + dx[k], j + dy[k]);
            int hash = hash(pos);
            if (!seen.contains(hash)) {
                res.add(pos);
            }
        }
        return res;
    }

    int[][] switchPos(int[][] board, int x1, int y1, int x2, int y2) {
        int row = board.length;
        int col = board[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            res[i] = Arrays.copyOf(board[i], col);
        }

        int temp = res[x1][y1];
        res[x1][y1] = res[x2][y2];
        res[x2][y2] = temp;
        return res;
    }

    public int hash(int[][] arr) {
        int hash = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                hash = (hash << 3) | (arr[i][j] & 0x7);
            }
        }
        return hash;
    }
}
