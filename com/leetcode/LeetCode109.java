package com.leetcode;

/*
给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 */
public class LeetCode109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode mid = findMiddleItem(head);
        TreeNode root = new TreeNode(mid.val);
        if (mid == head) {
            return root;
        }

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    private ListNode findMiddleItem(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (pre != null) {
            pre.next = null;
        }

        return slow;
    }

}
