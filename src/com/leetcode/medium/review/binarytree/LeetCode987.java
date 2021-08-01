package com.leetcode.medium.review.binarytree;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 *
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 *
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 *
 * 返回二叉树的 垂序遍历 序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode987 {
    List<int[]> info = new ArrayList<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        info.sort((o1, o2) -> o1[2] == o2[2] ? (o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]) : o1[2] - o2[2]);
        List<List<Integer>> res = new LinkedList<>();
        int col = info.get(0)[2];
        List<Integer> colList = new LinkedList<>();
        for (int i = 0; i < info.size(); i++) {
            if (col != info.get(i)[2]) {
                res.add(new ArrayList<>(colList));
                col = info.get(i)[2];
                colList.clear();
            }
            colList.add(info.get(i)[0]);
        }
        res.add(new ArrayList<>(colList));
        return res;
    }

    void dfs(TreeNode root, int col, int row) {
        if (root != null) {
            info.add(new int[] {root.val, row, col});
            // System.out.println("TreeNode: " + root.val + ", row: " + row + ", col: " + col);
            dfs(root.left, col - 1, row + 1);
            dfs(root.right, col + 1, row + 1);
        }
    }
}
