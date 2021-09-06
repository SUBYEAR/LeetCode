package com.leetcode.medium;

import com.leetcode.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode1171 {
    // 一种效率更高的解法
    public ListNode removeZeroSumSublists_(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        Map<Integer, ListNode> map = new HashMap<>();

        // 首次遍历建立 节点处链表和<->节点 哈希表
        // 若同一和出现多次会覆盖，即记录该sum出现的最后一次节点
        int sum = 0;
        for (ListNode d = dummy; d != null; d = d.next) {
            sum += d.val;
            map.put(sum, d);
        }

        // 第二遍遍历 若当前节点处sum在下一处出现了则表明两结点之间所有节点和为0 直接删除区间所有节点
        sum = 0;
        for (ListNode d = dummy; d != null; d = d.next) {
            sum += d.val;
            d.next = map.get(sum).next;
        }

        return dummy.next;
    }

//    链接：https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/solution/java-hashmap-liang-ci-bian-li-ji-ke-by-shane-34/


    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode tmp = head; // 1,3,2,-3,-2,5,5,-5,1
        List<Integer> sum = new ArrayList<>();
        List<ListNode> sumToNode = new ArrayList<>();
        ListNode dummyHead = new ListNode(1001, head);
        sum.add(0);
        sumToNode.add(dummyHead);

        int preSum = 0;
        while (tmp != null) {
            preSum += tmp.val;
            if (sum.contains(preSum)) {
                int index = sum.indexOf(preSum);
                sumToNode.get(index).next = tmp.next;
                // 更新sum和sumToNode
                sum = sum.stream().limit(index + 1).collect(Collectors.toList());
                sumToNode = sumToNode.stream().limit(index + 1).collect(Collectors.toList());
            } else {
                sum.add(preSum);
                sumToNode.add(tmp);
            }

            tmp = tmp.next;
        }
        return dummyHead.next;
    }
}
