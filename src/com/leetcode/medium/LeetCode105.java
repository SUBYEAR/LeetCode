package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode105 {
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            // pre:    1 2 4 5 3
            // inorder 4 2 5 1 3
            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int preorderVal = preorder[i]; // 注意这里pre的值其实是领先栈顶元素一个位置
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.left = new TreeNode(preorderVal);
                    stack.push(node.left); // 最左边的那条路径不断压栈
                } else { // 相等表示最左的那个节点找到了
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex++;
                    }
                    node.right = new TreeNode(preorderVal);
                    stack.push(node.right);
                }
            }
            return root;
        }
    }
}

// 递归解法
//class Solution {
//    private Map<Integer, Integer> indexMap;
//
//    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right,
//                                int inorder_left, int inorder_right) {
//        if (preorder_left > preorder_right) {
//            return null;
//        }
//
//        // 前序遍历中的第一个节点就是根节点
//        int preorder_root = preorder_left;
//        // 在中序遍历中定位根节点
//        int inorder_root = indexMap.get(preorder[preorder_root]);
//
//        // 先把根节点建立出来
//        TreeNode root = new TreeNode(preorder[preorder_root]);
//        // 得到左子树中的节点数目
//        int size_left_subtree = inorder_root - inorder_left;
//        // 递归地构造左子树，并连接到根节点
//        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
//        root.left = myBuildTree(preorder, inorder, preorder_left + 1,
//                preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
//        // 递归地构造右子树，并连接到根节点
//        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
//        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1,
//                preorder_right, inorder_root + 1, inorder_right);
//        return root;
//    }
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        int n = preorder.length;
//        // 构造哈希映射，帮助我们快速定位根节点
//        indexMap = new HashMap<Integer, Integer>();
//        for (int i = 0; i < n; i++) {
//            indexMap.put(inorder[i], i);
//        }
//        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
//    }
//}
