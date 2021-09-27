package com.leetcode.hard.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由于一个漏洞，文件系统中存在许多重复文件夹。给你一个二维数组 paths，其中 paths[i] 是一个表示文件系统中第 i 个文件夹的绝对路径的数组。
 *
 * 例如，["one", "two", "three"] 表示路径 "/one/two/three" 。
 * 如果两个文件夹（不需要在同一层级）包含 非空且相同的 子文件夹 集合 并具有相同的子文件夹结构，
 * 则认为这两个文件夹是相同文件夹。相同文件夹的根层级 不 需要相同。如果存在两个（或两个以上）相同 文件夹，则需要将这些文件夹和所有它们的子文件夹 标记 为待删除。
 *
 * 例如，下面文件结构中的文件夹 "/a" 和 "/b" 相同。它们（以及它们的子文件夹）应该被 全部 标记为待删除：
 * /a
 * /a/x
 * /a/x/y
 * /a/z
 * /b
 * /b/x
 * /b/x/y
 * /b/z
 * 然而，如果文件结构中还包含路径 "/b/w" ，那么文件夹 "/a" 和 "/b" 就不相同。
 * 注意，即便添加了新的文件夹 "/b/w" ，仍然认为 "/a/x" 和 "/b/x" 相同。
 * 一旦所有的相同文件夹和它们的子文件夹都被标记为待删除，文件系统将会 删除 所有上述文件夹。
 * 文件系统只会执行一次删除操作。执行完这一次删除操作后，不会删除新出现的相同文件夹。
 *
 * 返回二维数组 ans ，该数组包含删除所有标记文件夹之后剩余文件夹的路径。路径可以按 任意顺序 返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-duplicate-folders-in-system
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1948 {
    Map<String, Integer> freq = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie root = new Trie();
        for (List<String> path : paths) {
            Trie cur = root;
            for (String node : path) {
                cur.children.putIfAbsent(node, new Trie());
                cur = cur.children.get(node);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>(); // 记录根节点到当前节点的路径
        construct(root);
        process(root, path, ans);
        return ans;
    }

    void process(Trie node, List<String> path, List<List<String>> ans) {
        if (freq.getOrDefault(node.ss, 0) > 1) {
            return;
        }
        if (!path.isEmpty()) {
            ans.add(new ArrayList<>(path));
        }

        for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
            String folder = entry.getKey();
            Trie child = entry.getValue();
            path.add(folder);
            process(child, path, ans);
            path.remove(path.size() - 1);
        }
    }

    void construct(Trie node) {
        if (node.children.isEmpty()) {
            return;
        }

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
            String folder = entry.getKey();
            Trie child = entry.getValue();
            construct(child);
            list.add(folder + "(" + child.ss + ")");
        }
        list.sort(String::compareTo);
        for (String s : list) {
            node.ss = node.ss + s;
        }
        freq.put(node.ss, freq.getOrDefault(node.ss, 0) + 1);
    }

    private class Trie {
        String ss;
        Map<String, Trie> children;
        public Trie() {
            ss = "";
            children = new HashMap<>();
        }

        void insert(List<String> words) {
            if (words == null || words.isEmpty()) {
                return;
            }
            Trie cur = this;
            for (String word : words) {
                cur.children.putIfAbsent(word, new Trie());
                cur = cur.children.get(word);
            }
        }
    }
}
