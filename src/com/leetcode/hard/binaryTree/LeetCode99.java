package com.leetcode.hard.binaryTree;

import com.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode99 {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.val < pred.val) { // 注意判断逻辑放在root 和 pre更新之前，而且还需要是在左子树找完以后
                // 比如1 2 3 因为位置错误被更换为3 2 1. 那么第一次可以找到x指向3 y指向2 第二个下坡可以继续更新y指向1
                y = root; // 而y是可以不断更新直到找到正确位置
                if (x == null) {
                    x = pred; // 注意这里x只被赋值了一次所以是找到一个错误的位置
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    private class Solution {
// Morris 中序遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
// 1. 如果 x 无左孩子，则访问 x 的右孩子，即x=x.right。
// 2. 如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），我们记为
//    predecessor。根据 predecessor 的右孩子是否为空，进行如下操作。
//       如果 predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
//       如果 predecessor 的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，我们将predecessor 的右孩子置空
//       然后访问 x 的右孩子，即 x=x.right。
// 3. 重复上述操作，直至访问完整棵树。
        public void recoverTree(TreeNode root) {
            TreeNode x = null, y = null, pred = null, predecessor = null;

            while (root != null) {
                if (root.left != null) {
                    // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }

                    // 让 predecessor 的右指针指向 root，继续遍历左子树
                    if (predecessor.right == null) {
                        predecessor.right = root;
                        root = root.left;
                    }
                    // 说明左子树已经访问完了，我们需要断开链接
                    else {
                        if (pred != null && root.val < pred.val) {
                            y = root;
                            if (x == null) {
                                x = pred;
                            }
                        }
                        pred = root;

                        predecessor.right = null;
                        root = root.right;
                    }
                }
                // 如果没有左孩子，则直接访问右孩子
                else {
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;
                    root = root.right;
                }
            }
            swap(x, y);
        }

        public void swap(TreeNode x, TreeNode y) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }
}


