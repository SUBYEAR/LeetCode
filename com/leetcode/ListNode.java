/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 功能描述
 *
 * @since 2020-04-30
 */
// Definition for singly-linked list.
public class ListNode {
    int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void print(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println();
    }
}
