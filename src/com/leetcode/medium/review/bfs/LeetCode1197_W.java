package com.leetcode.medium.review.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上，你的 骑士 驻扎在坐标为 [0, 0] 的方格里。
 *
 * 骑士的走法和中国象棋中的马相似，走 “日” 字：即先向左（或右）走 1 格，再向上（或下）走 2 格；
 * 或先向左（或右）走 2 格，再向上（或下）走 1 格。
 *
 * 每次移动，他都可以按图示八个方向之一前进。现在，骑士需要前去征服坐标为 [x, y] 的部落，请你为他规划路线。
 *
 * 最后返回所需的最小移动次数即可。本题确保答案是一定存在的
 *
 * 提示：|x| + |y| <= 300
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-knight-moves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1197_W {
    // 八个方向
    int[][] dirs = new int[][]{
            {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}
    };

    public int minKnightMoves(int x, int y) {
        int dist = 0;
        int abs = getMhdDist(0, 0, x, y);   // (0,0)到(x,y)的距离（横坐标之差加纵坐标之差）
        // 目标最远不超过 |x| + |y| <= 300
        // 666 表示把 [-333, 333] 映射为 [0, 666]
        boolean[][] mark = new boolean[666][666];   // 用于标记已走过的位置
        Queue<Node> queue = new LinkedList<>();
        Node curNode = new Node(0, 0, 0);   // 起点，已走步数为 0
        Node newNode = null;
        mark[333][333] = true;  // 映射后的 (0,0)坐标
        queue.add(curNode);
        while (!queue.isEmpty()) {
            curNode = queue.remove();
            int curX = curNode.x;
            int curY = curNode.y;
            int curDist = curNode.dist; // 从 (0,0) 到 (curX,curY) 的已走步数
            if (curX == x && curY == y) {
                // 当前点已在终点，返回已走步数
                return curDist;
            }
            int mhdist = getMhdDist(curX, curY, x, y);  // 剩余距离
            for (int[] dir : dirs) {    // 往八个方向走
                int newX = curX + dir[0];
                int newY = curY + dir[1];
                int nextDist = curDist + 1; // 新的步数等于已走步数加一
                if (mark[newX + 333][newY + 333]) { // 注意因为棋盘是无穷大所以这里没有边界判断
                    continue;
                }
                // 下一步走的方向一定是往目的地靠近
                // 即 getMhdDist(newX, newY, x, y) < (curX, curY, x, y)
                // 而不是八个方向都走一遍
                if (mhdist > getMhdDist(newX, newY, x, y) || abs < 4) { // 在添加新节点时不仅用到标记法还用到了距离判断
                    newNode = new Node(newX, newY, nextDist); // 注意这里绝对值小于4的条件是因为以点位中心距离为2的半径的边界上可以通过走多次日达到目标点
                    queue.add(newNode);
                    mark[newX + 333][newY + 333] = true;
                }
            }
        }
        return -1;
    }

    private int getMhdDist(int i, int j, int x, int y) {
        return Math.abs(i - x) + Math.abs(j - y);
    }

    class Node {
        int x;
        int y;
        public int dist;    // (0,0)走到(x,y)的最少移动次数

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
