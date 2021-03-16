package com.leetcode.medium.review;

import java.util.PriorityQueue;

/**
 * 给你一个 R 行 C 列的整数矩阵 A。矩阵上的路径从 [0,0] 开始，在 [R-1,C-1] 结束。
 *
 * 路径沿四个基本方向（上、下、左、右）展开，从一个已访问单元格移动到任一相邻的未访问单元格。
 *
 * 路径的得分是该路径上的 最小 值。例如，路径 8 →  4 →  5 →  9 的值为 4 。
 *
 * 找出所有路径中得分 最高 的那条路径，返回其 得分。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-maximum-minimum-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1102 {
    class Point {
        int x;
        int y;
        int pointValue;
        int currentMinValue;

        Point(int x, int y, int pointValue) {
            this.x = x;
            this.y = y;
            this.pointValue = pointValue;
        }
    }

    public int maximumMinimumPath(int[][] A) {
        //优先队列，同时记录达到这个节点的最小值，到达该节点的路径中的最小值越大越优先，
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>((p1, p2) -> p2.currentMinValue - p1.currentMinValue);
        int rows = A.length;
        int cols = A[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Point startPoint = new Point(0, 0, A[0][0]);
        startPoint.currentMinValue = A[0][0];
        priorityQueue.add(startPoint);

        int[][] directors = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!priorityQueue.isEmpty()) {
            Point currentPoint = priorityQueue.poll();
            if (currentPoint.x == rows - 1 && currentPoint.y == cols - 1) {
                return currentPoint.currentMinValue;
            }
            visited[currentPoint.x][currentPoint.y] = true;
            for (int i = 0; i < directors.length; i++) {
                int nextX = currentPoint.x + directors[i][0];
                int nextY = currentPoint.y + directors[i][1];
                //在范围内，且没有访问过，加入队列
                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && !visited[nextX][nextY]) {
                    int nextPointValue = A[nextX][nextY];
                    Point nextPoint = new Point(nextX, nextY, nextPointValue);
                    //目前的最小值与当前节点比较
                    nextPoint.currentMinValue = Math.min(currentPoint.currentMinValue, nextPoint.pointValue);
                    priorityQueue.offer(nextPoint);
                }
            }
        }
        return -1;
    }
}

// 并查集
// public int maximumMinimumPath(int[][] A) {
//        int rows = A.length;
//        int cols = A[0].length;
//        int result = Math.min(A[0][0],A[rows-1][cols-1]);
//        //找最大值，按降序排序
//        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1,o2)->o2.value-o1.value);
//        // 跟彼此熟识的最早时间类似、看A[0][0]和A[rows-1][cols-1]联通的最大值。
//        // 尽量先取最大值
//        for(int i=0;i<rows;i++)
//        {
//            for (int j=0;j<cols;j++)
//            {
//                priorityQueue.add(new Node(i,j,A[i][j]));
//            }
//        }
//
//        int[][] directors = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
//        //只要他俩还没在一个圈里面，则继续执行
//        //每次执选择可能的值最大的节点。
//        boolean[][] used = new boolean[rows][cols];
//        DSU dsu = new DSU(rows*cols);
//        //不断的判断第一个和最后一个是不是共祖先。一旦第一个节点和最后一个节点共祖先，那已经遍历过的节点就已经满足结果集了。
//        while (dsu.findRoot(0)!=dsu.findRoot(rows*cols-1))
//        {
//            Node node = priorityQueue.poll();
//            result = Math.min(result,node.value);
//            used[node.xIndex][node.yIndex]=true;
//            for(int i=0;i<directors.length;i++)
//            {
//                int nextX = node.xIndex+directors[i][0];
//                int nextY = node.yIndex+directors[i][1];
//                //超出数字越界，或未被染色
//                if(nextX<0||nextX>=rows||nextY<0||nextY>=cols ||!used[nextX][nextY])
//                {
//                    continue;
//                }
//                dsu.union(nextX*cols+nextY,node.xIndex*cols+node.yIndex);
//            }
//        }
//        return result;
//    }
//    class DSU
//    {
//        int [] parent;
//        int[] rank;
//        public DSU(int size)
//        {
//            parent = new int[size];
//            rank = new int[size];
//            for(int i=0;i<parent.length;i++)
//            {
//                parent[i] = i;
//            }
//        }
//
//        /**
//         * 此处注意：是要把即将被染色的节点(newNode)的父节点 赋值为  已经染色的节点（currentNode）值。
//         * 然后从newNode开始不断递归染色父节点的父节点，直到节点是currentNode
//         *
//         * @param currentNode  已经染色过的节点
//         * @param newNode  即将染色的节点
//         */
//        public void union(int currentNode,int newNode)
//        {
//            int currentRoot = findRoot(currentNode);
//            int newRoot = findRoot(newNode);
//            if(currentRoot == newRoot)
//            {
//                return;
//            }
//            // 此处很重要，不仅仅是把newNode的父节点赋值为currentNode。
//            // 要一直递归到currentNode节点为止，所有的父节点都更新为currentNode
//            while(currentNode!=newNode)
//            {
//                int temp =parent[newNode];
//                parent[newNode]=currentNode;
//                newNode = temp;
//            }
//        }
//        public int findRoot(int x)
//        {
//            if(x==parent[x])
//            {
//                return x;
//            }
//            parent[x] = findRoot(parent[x]);
//
//            return parent[x];
//        }
//
//    }
//    class Node{
//
//        int xIndex;
//        int yIndex;
//        int value;
//        public Node(int xIndex,int yIndex,int value)
//        {
//            this.value = value;
//            this.xIndex = xIndex;
//            this.yIndex = yIndex;
//        }
//    }
//

// dfs
// private int rows;
//    private int cols;
//    public int maximumMinimumPath(int[][] A) {
//        rows = A.length;
//        cols = A[0].length;
//        int low = 1;
//        int high = Math.min(A[0][0],A[rows-1][cols-1]);
//
//        int result = 0;
//        while (low<=high)
//        {
//            int mid = (low+high)/2;
//            boolean[][] visited = new boolean[rows][cols];
//            if(isValidByDfs(A,mid,visited,new Node(0,0)))
//            {
//                result=mid;
//                low = mid+1;
//            }else {
//                high=mid-1;
//            }
//        }
//        return result;
//
//    }
//    class Node{
//        private int x;
//        private int y;
//        public Node(int x,int y)
//        {
//            this.x=x;
//            this.y=y;
//        }
//
//    }
//
//    private boolean isValidByDfs(int[][] A,int target,boolean[][] visited,Node startNode)
//    {
//        if(startNode.x==rows-1 && startNode.y==cols-1)
//        {
//            return true;
//        }
//        int[][] directors = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
//        for(int i=0;i<directors.length;i++)
//        {
//            int nextX = startNode.x+directors[i][0];
//            int nextY = startNode.y+directors[i][1];
//            if(nextX>=0&&nextX<rows&nextY>=0&nextY<cols&&visited[nextX][nextY]==false&&A[nextX][nextY]>=target)
//            {
//                visited[nextX][nextY]=true;
//                if(isValidByDfs(A,target,visited,new Node(nextX,nextY)))
//                {
//                    return true;
//                }
//            }
//
//        }
//        return false;
//    }
//