package com.leetcode.hard;

import com.leetcode.ListNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode[] nodes = reverse(dummy, head, k);
        while (nodes[1] != null) {
            nodes = reverse(nodes[0], nodes[1], k);
        }
        return dummy.next;
    }

    private ListNode[] reverse(ListNode pre, ListNode start, int k) {
        ListNode cur = start;
        LinkedList<ListNode> st = new LinkedList<>();
        while (cur != null && st.size() < k) {
            st.addLast(cur);
            cur = cur.next;
        }
        if (cur == null && st.size() < k) { // 最后剩余的节点保存顺序
            return new ListNode[] { pre, null};
        }
        pre.next = st.peekLast();
        ListNode next = cur;
        while (st.size() != 0) {
            cur = st.pollLast();
            cur.next = st.size() == 0 ? next : st.peekLast();
        }
        pre = cur;
        return new ListNode[] {pre, next};
    }
}
