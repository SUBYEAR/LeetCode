package com.leetcode.medium.passed.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode430 {
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    void dfs(Node node) {
        List<Node> childNodes = findChildren(node);
        for (Node childNode : childNodes) {
            dfs(childNode.child);
            insert(childNode);
        }
    }

    private List<Node> findChildren(Node node) {
        List<Node> ans = new ArrayList<>();
        Node tmp = node;
        while (tmp != null) {
            if (tmp.child != null) {
                ans.add(tmp);
            }
            tmp = tmp.next;
        }
        return ans;
    }

    private void insert(Node cur) {
        Node insert = cur.child;
        cur.child = null;
        Node next = cur.next;
        // 调整链表
        insert.prev = cur;
        cur.next = insert;
        if (next != null) {
            Node insertEnd = insert;
            while (insertEnd.next != null) {
                insertEnd = insertEnd.next;
            }
            next.prev = insertEnd;
            insertEnd.next = next;
        }
    }

    private class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
}
