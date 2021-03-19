package com.leetcode.medium;

import com.leetcode.ListNode;

import java.util.Stack;

/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，
 * 返回 反转后的链表 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null || m == n) return head;
        Stack<Integer> s = new Stack<>();
        ListNode next = head;
        ListNode pre = null;
        int index = 1;
        while (index <= n) {
            if (index >= m) {
                s.push(next.val);
            }
            pre = index == m - 1 ? next : pre;
            ++index;
            next = next.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode t = dummy;
        while (!s.isEmpty()) {
            t.next = new ListNode(s.pop());
            t = t.next;
        }
        t.next = next;
        if (m == 1) return dummy.next;
        pre.next = dummy.next;
        return head;
    }

    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;

            ListNode pre = dummyNode;
            // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
            // 建议写在 for 循环里，语义清晰
            for (int i = 0; i < left - 1; i++) {
                pre = pre.next;
            }

            // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
            ListNode rightNode = pre;
            for (int i = 0; i < right - left + 1; i++) {
                rightNode = rightNode.next;
            }

            // 第 3 步：切断出一个子链表（截取链表）
            ListNode leftNode = pre.next;
            ListNode curr = rightNode.next;

            // 注意：切断链接
            pre.next = null;
            rightNode.next = null;

            // 第 4 步：同第 206 题，反转链表的子区间
            reverseLinkedList(leftNode);

            // 第 5 步：接回到原来的链表中
            pre.next = rightNode;
            leftNode.next = curr;
            return dummyNode.next;
        }

        private void reverseLinkedList(ListNode head) {
            // 也可以使用递归反转一个链表
            ListNode pre = null;
            ListNode cur = head;

            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
        }

        public ListNode reverseBetween2(ListNode head, int left, int right) {
            // 设置 dummyNode 是这一类问题的一般做法
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            ListNode pre = dummyNode;
            for (int i = 0; i < left - 1; i++) {
                pre = pre.next;
            }
            // curr：指向待反转区域的第一个节点 left；
            //next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
            //pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。。
            ListNode cur = pre.next;
            ListNode next;
            for (int i = 0; i < right - left; i++) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            return dummyNode.next;
        }
    }
}
