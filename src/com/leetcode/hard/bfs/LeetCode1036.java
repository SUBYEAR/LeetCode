package com.leetcode.hard.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 在一个 106 x 106 的网格中，每个网格上方格的坐标为 (x, y) 。
 *
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。
 * 数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 *
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
 *
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/escape-a-large-maze
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1036 {
    static int dirs[][] = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
    static int limit = (int)1e6;
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blocks = new HashSet<>();
        for(int block[] : blocked)
            blocks.add(block[0] + ":" + block[1]);
        return bfs(source, target, blocks) && bfs(target, source, blocks);
        // 如果没有后面与的条件那么当终点被4个阻塞点包围住的时候，第一个条件会返回true不符合预期
    }
    public boolean bfs(int[] source, int[] target, Set<String> blocks) {
        Set<String> seen = new HashSet<>();
        seen.add(source[0] + ":" + source[1]);
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(source);

        while (!bfs.isEmpty()) {
            int cur[] = bfs.poll();
            for (int dir[] : dirs) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];
                if (nextX < 0 || nextY < 0 || nextX >= limit || nextY >= limit) continue;
                String key = nextX + ":" + nextY;
                if (seen.contains(key) || blocks.contains(key)) continue;
                if (nextX == target[0] && nextY == target[1]) return true;
                bfs.offer(new int[]{nextX, nextY});
                seen.add(key);
            }
            // 因为 blocked 的 length 是 200
            // 如果使用这 200 个 block 可以围成最大的区域是 19900，如下：
            /*
                0th      _________________________
                        |O O O O O O O X
                        |O O O O O O X
                        |O O O O O X
                        |O O O O X
                        .O O O X
                        .O O X
                        .O X
                200th   |X
            从上面可以计算出 block（即 X）可以围城的最大区域(是一个角的三角形)，大小计算如下：
            1 + 2 + 3 + 4 + ... + 199 = (1 + 199) * 199 / 2 = 19900
            这里我们向上取整为 20000。
            */
            // 也就是说，如果迭代了 20000 步还能继续走的话，那么是肯定可以到达 target 的
            if (seen.size() == 20000) return true;
        }
        return false;
    }

    // https://leetcode-cn.com/problems/escape-a-large-maze/solution/biao-zhun-bfs-ti-jie-by-tangweiqun-4/
}
