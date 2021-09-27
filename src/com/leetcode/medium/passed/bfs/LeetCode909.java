package com.leetcode.medium.passed.bfs;

import java.util.*;

/**
 * N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。例如，一块 6 x 6 大小的棋盘，编号如下：
 * <p>
 * 36 35 34 33 32 31
 * <p>
 * 25 26 27 28 29 30
 * <p>
 * 24 23 22 21 20 19
 * <p>
 * 13 14 15 16 17 18
 * <p>
 * 12 11 10 09 08 07
 * <p>
 * 01 02 03 04 05 06
 * <p>
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
 * <p>
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 * <p>
 * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
 * <p>
 * 选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
 * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。
 * 传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。 
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
 * <p>
 * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/snakes-and-ladders
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode909 {
    public int snakesAndLadders(int[][] board) {
        int res = 0;
        int row = board.length;
        int col = board[0].length;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        q.add(1);
        seen.add(1);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Integer cur = q.poll();
                List<Integer> next = getNext(board, cur);
                if (next.contains(row * col)) {
                    return res + 1;
                }
                for (Integer n : next) {
                    if (seen.contains(n)) {
                        continue;
                    }
                    seen.add(n);
                    q.add(n);
                }

            }
            ++res;
        }
        return res;
    }

    public List<Integer> getNext(int[][] board, int x) {
        int row = board.length;
        int col = board[0].length;

        int xRow = row - 1 - (x - 1) / col;
        boolean dir = isIncreased(row, xRow);
        int rowStart = dir ? (row - 1 - xRow) * col + 1 : (row - xRow) * col;
        int xCol = dir ? x - rowStart : rowStart - x;

        List<Integer> res = new LinkedList<>();
        while (res.size() < 6) {
            xCol += isIncreased(row, xRow) ? 1 : -1;
            if (xCol == -1 || xCol == col) {
                xRow -= 1;
                if (xRow < 0) break;
                continue;
            }

            ++x;
            int next = board[xRow][xCol] == -1 ? x : board[xRow][xCol];
            if (next > row * col) {
                break;
            }
            res.add(next);
        }
        return res;
    }

    private boolean isIncreased(int row, int curRow) {
        return (row - 1 - curRow) % 2 == 0; // true 表示从左到右递增编号；否则是从左到右递减编号
    }
}
