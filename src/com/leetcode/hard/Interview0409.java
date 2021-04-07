package com.leetcode.hard;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。给定一个由不同节点组成的二叉搜索树，
 * 输出所有可能生成此树的数组。
 *
 * 示例：
 * 给定如下二叉树
 *
 *         2
 *        / \
 *       1   3
 * 返回：
 *
 * [
 *    [2,1,3],
 *    [2,3,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bst-sequences-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview0409 {
    private List<List<Integer>> res;
    public List<List<Integer>> BSTSequences(TreeNode root) {
        this.res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if(root == null) {
            this.res.add(path);
            return this.res;
        }

        path.add(root.val);
        List<TreeNode> content = new LinkedList<>();
        if(root.left != null) content.add(root.left);
        if(root.right != null) content.add(root.right);

        dfs(content, path);
        return this.res;
    }

// 首先根节点是固定的，固定了根节点后下一步就有两个选择，走左边或者右边（前提是有两个非空子节点），即对于根节点来说，
// 它的下一步的选择用一个集合来表示的话，集合的内容就是他的非空子节点。然后第二步，任意选择一个子节点作为第二步的走法，
// 那对于这个结点，它的下一步的选择内容可以这么求：还记得上面说的那个选择集合吗，在该集合中删掉刚才选的那个结点，
// 然后将该结点的非空子节点加入到集合中去，所得到的新的结合就是被选择的节点的下一步走法的选择集合！一次类推，
// 没选择一个节点就对选择结合做删除该结点并添加该结点的非空子节点的操作，直到选择集合为空，即没有选择了，
// 那么就产生了一条满足要求的路径，将它添加到路径集合中，最后该集合中就是满足题意的所有路径的总和。
    private void dfs(List<TreeNode> content, List<Integer> path) {
        if(content.isEmpty()) {
            this.res.add(new ArrayList<>(path));
            return ;
        }

        List<TreeNode> temp = new ArrayList<>(content);
        for(int i = 0; i < content.size(); i++) {
            TreeNode node = content.get(i);
            path.add(node.val);
            content.remove(i);
            if(node.left != null) content.add(node.left);
            if(node.right != null) content.add(node.right);
            dfs(content, path);
            path.remove(path.size()-1);
            content = new ArrayList<>(temp);
        }
    }
}

//     // 这两个声明成全局变量，这也回溯函数就少传点参数
//    private LinkedList<Integer> path = new LinkedList<>();
//    private List<List<Integer>> result = new LinkedList<>();
//
//    public List<List<Integer>> BSTSequences(TreeNode root) {
//        Deque<TreeNode> dq = new LinkedList<>();
//        if (root != null) {
//            dq.offer(root);
//        }
//        dfs(dq);
//        return result;
//    }
//
//    // 1.确定递归函数返回值和参数
//    public void dfs(Deque<TreeNode> dq) {
//        // 2.确定递归终止条件
//        // dq是该层剩下可选节点的候选队列，若队列为空，则说明没有候选元素了
//        // 因此可直接把当前路径添加到结果集，然后返回
//        if (dq.isEmpty()) {
//            result.add(new ArrayList<Integer>(path));
//            return;
//        }
//
//        //3.确定回溯函数的遍历过程
//
//        // 当前层可与选择的候选节点的个数
//        int size = dq.size();
//        while (size > 0) {
//            TreeNode cur = dq.pollFirst();
//            // 向路径中添加当前值
//            path.add(cur.val);
//            // 记录添加的子节点数量，等会回溯时需要用
//            int children = 0;
//            // 向候选队列中添加子节点
//            if (cur.left != null) {
//                children++;
//                dq.offerLast(cur.left);
//            }
//            if (cur.right != null) {
//                children++;
//                dq.offerLast(cur.right);
//            }
//
//            // 递归
//            dfs(dq);
//
//            // 回溯候选队列
//            while (children > 0) {
//                dq.pollLast();
//                children--;
//            }
//            dq.offerLast(cur);
//
//            // 回溯路径
//            path.removeLast();
//            // 当前节点处理完毕，数量减一
//            size--;
//        }
//    }