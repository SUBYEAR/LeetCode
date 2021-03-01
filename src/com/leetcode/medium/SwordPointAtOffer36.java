package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 二叉树转双向链表
 * 思路：中序遍历然后转换，或者直接在中序遍历的时候把指针位置调整好。此时需要两个辅助变量：head和pre
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 */
public class SwordPointAtOffer36 {
    public TreeNode treeToDoublyList(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        midOrder(root, list);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).right = i == list.size() - 1 ? list.get(0) : list.get(i + 1);
            list.get(i).left = i == 0 ? list.get(list.size() - 1) : list.get(i -1);
        }

        return list.get(0);
    }

    void midOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }

        midOrder(root.left, list);
        list.add(root);
        midOrder(root.right, list);
    }
}

//     Node head, pre;
//     public Node treeToDoublyList(Node root) {
//         if(root==null) return null;
//         dfs(root);
//
//         pre.right = head;
//         head.left =pre;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
//
//         return head;
//
//     }
//
//     public void dfs(Node cur){
//         if(cur==null) return;
//         dfs(cur.left);
//
//         //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
//         if(pre==null) head = cur;
//         //反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
//         else pre.right = cur;
//
//         cur.left = pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。
//
//         pre = cur;//pre指向当前的cur
//         dfs(cur.right);//全部迭代完成后，pre指向双向链表中的尾节点
//     }
