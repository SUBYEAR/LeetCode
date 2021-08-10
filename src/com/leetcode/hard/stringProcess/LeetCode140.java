package com.leetcode.hard.stringProcess;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode140 {
    List<String> res = new LinkedList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        StringBuilder path = new StringBuilder();
        backtrack(s, wordDict, path);
        return res;
    }

    void backtrack(String s, List<String> wordDict, StringBuilder path) {
        if (s.isEmpty()) {
            res.add(path.substring(0, path.length() - 1));
            return;
        }

        for (int i = 0; i < wordDict.size(); i++) {
            String choose = wordDict.get(i);
            if (s.startsWith(choose)) {
                path.append(choose).append(" ");
                backtrack(s.substring(choose.length()), wordDict, path);
                path.delete(path.length() - choose.length() - 1, path.length());
            }
        }
    }
}
