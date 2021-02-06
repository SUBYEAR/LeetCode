package com.leetcode;

public class ListNode {
      public int val;
      public ListNode next;
      public ListNode(int x) { val = x; }

    public static  void  printList(ListNode node) {
          ListNode tmp = node;
          while (tmp != null) {
              System.out.println(tmp.val + "--->");
              tmp = tmp.next;
          }
    }
}
