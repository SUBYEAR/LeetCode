package com.leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    }

    private static final int[] dx = new int[] { 0,0,1,-1};
    private static final int[] dy = new int[] { 1,-1,0,0};
    public void getNeighbor(int[][] board) {
        int row = board.length, col = board[0].length;
        int i = 0, j = 0;
        for (;i < row; i++) {
            for (;j < col; j++) {
                if (board[i][j] == 0) {
                    break;
                }
            }
        }


        for (int k = 0;  k < dx.length; k++) {
            if ((i + dx[k]) < 0 || (i + dx[k]) >= row || (j + dy[k]) < 0 || (j +dy[k]) >= col ) {
                continue;
            }

        }
    }

    int[][] switchPos(int[][] board, int x1, int y1, int x2, int y2) {

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
