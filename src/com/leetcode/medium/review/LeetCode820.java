package com.leetcode.medium.review;

import java.util.Arrays;

/**
 * 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：
 *
 * words.length == indices.length
 * 助记字符串 s 以 '#' 字符结尾
 * 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
 * 给你一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/short-encoding-of-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode820 {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        int res = 0;
        Trie root = new Trie();
        StringBuilder sb = new StringBuilder(7);
        for (String word : words) {
            String s = sb.append(word).reverse().toString();
            if (!root.startsWith(s)) {
                root.insert(s);
                res += word.length() + 1;
            }
            sb.setLength(0);
        }
        return res;
    }

    class Trie {
        boolean isEnd;
        Trie[] next;

        public Trie() {
            next = new Trie[26];
            isEnd = false;
        }

        public void insert(String s) {
            Trie cur = this;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Trie();
                }
                cur = cur.next[index];
            }
            cur.isEnd = true;
        }

        public boolean startsWith(String s) {
            return searchPrefix(s) != null;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }


        private Trie searchPrefix(String pre) {
            Trie cur = this;

            for (int i = 0; i < pre.length(); i++) {
                int t = pre.charAt(i) - 'a';
                if (cur.next[t] == null) {
                    return null;
                }
                cur = cur.next[t];
            }
            return cur;
        }
    }

    // 官方解法
    // public int minimumLengthEncoding(String[] words) {
    //        TrieNode trie = new TrieNode();
    //        Map<TrieNode, Integer> nodes = new HashMap<TrieNode, Integer>();
    //
    //        for (int i = 0; i < words.length; ++i) {
    //            String word = words[i];
    //            TrieNode cur = trie;
    //            for (int j = word.length() - 1; j >= 0; --j) {
    //                cur = cur.get(word.charAt(j));
    //            }
    //            nodes.put(cur, i);
    //        }
    //
    //        int ans = 0;
    //        for (TrieNode node: nodes.keySet()) {
    //            if (node.count == 0) {
    //                ans += words[nodes.get(node)].length() + 1;
    //            }
    //        }
    //        return ans;
    //
    //    }
    //}
    //
    //class TrieNode {
    //    TrieNode[] children;
    //    int count;
    //
    //    TrieNode() {
    //        children = new TrieNode[26];
    //        count = 0;
    //    }
    //
    //    public TrieNode get(char c) {
    //        if (children[c - 'a'] == null) {
    //            children[c - 'a'] = new TrieNode();
    //            count++;
    //        }
    //        return children[c - 'a'];
    //    }
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/short-encoding-of-words/solution/dan-ci-de-ya-suo-bian-ma-by-leetcode-solution/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
