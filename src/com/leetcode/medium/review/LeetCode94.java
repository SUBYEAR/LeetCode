package com.leetcode.medium.review;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的中序 遍历。 非递归版本
 */
public class LeetCode94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> s = new Stack<>();

        while (!s.isEmpty() || root != null) {
            while (root != null) { // 一直寻找最左边的节点
                s.push(root); // 使用栈记录沿途节点
                root = root.left;
            }

            root = s.pop();
            res.add(root.val);
            root = root.right; // 遍历右边
        }
        return res;
    }
}

// Morris 中序遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
// 1. 如果 x 无左孩子，则访问 x 的右孩子，即x=x.right。
// 2. 如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），我们记为
//    predecessor。根据 predecessor 的右孩子是否为空，进行如下操作。
//       如果 predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
//       如果 predecessor 的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，我们将predecessor 的右孩子置空
//       然后访问 x 的右孩子，即 x=x.right。
// 3. 重复上述操作，直至访问完整棵树。

class Solution {
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//        TreeNode predecessor = null;
//
//        while (root != null) {
//            if (root.left != null) {
//                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
//                predecessor = root.left;
//                while (predecessor.right != null && predecessor.right != root) {
//                    predecessor = predecessor.right;
//                }
//
//                // 让 predecessor 的右指针指向 root，继续遍历左子树
//                if (predecessor.right == null) {
//                    predecessor.right = root;
//                    root = root.left;
//                } else { // 说明左子树已经访问完了，我们需要断开链接
////     这个思路的关键点是因为先找到了左子树上最后边的节点，这个节点的左子节点和右子节点肯定都是null,第一次
////     第一次到最右节点设置了它的rightmost.right = null --> rightmost.right = cur,设置了这个标志后，马上
////     随着而来的是遍历这个节点的左子树，遍历左子树的规则是先找最右节点
//                    res.add(root.val);
//                    predecessor.right = null;
//                    root = root.right;
//                }
//            } else { // 如果没有左孩子，则直接访问右孩子
//                res.add(root.val);
//                root = root.right;
//            }
//        }
//        return res;
//    }
}
