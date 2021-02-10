package com.leetcode;

public class ReverseLsit {
    ListNode reverse(ListNode head) { // 这里链表的长度至少是1
        if (head.next == null) { // baseCase 也就是递归的终止条件
            return head;
        }
        ListNode last =  reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
