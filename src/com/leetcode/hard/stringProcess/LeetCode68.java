package com.leetcode.hard.stringProcess;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<>();
        int index = 0;
        int preIndex = 0;
        while (index < words.length) {
            int curLen = 0;
            while (index < words.length) {
                curLen += words[index].length();
                if (curLen  + index - preIndex> maxWidth) {
                    break;
                }
                index++;
            }

            StringBuilder line = new StringBuilder(maxWidth);
            int wordCnt = index - preIndex;
            if (index == words.length) { // 处理最后一行
                for (int i = preIndex; i < index; i++) {
                    line.append(words[i]);
                    if (i < index - 1) {
                        line.append(" ");
                    }
                }
                line.append(getRepeatSpace(maxWidth - curLen - wordCnt + 1));
            } else {
                curLen -= words[index].length();
                int totalSpace = maxWidth - curLen;
                if (wordCnt == 1) {
                    line.append(words[preIndex]).append(getRepeatSpace(totalSpace));
                } else {
                    List<String> spaces = getSpaceBetweenWords(totalSpace, wordCnt - 1);
                    for (int i = preIndex; i < index; i++) {
                        line.append(words[i]);
                        if (i < index - 1) {
                            line.append(spaces.get(i - preIndex));
                        }
                    }
                }

                preIndex = index;
            }
            // System.out.println(line);
            res.add(line.toString());
        }
        return res;
    }

    public String getRepeatSpace(int n) {
        // String.join("", Collections.nCopies(n, " ")));
        return n <= 0 ? "" : Stream.generate(() -> " ").limit(n).collect(Collectors.joining());
    }

    public List<String> getSpaceBetweenWords(int totalSpace, int cnt) {
        List<String> res = new LinkedList<>();
        if (cnt == 1) {
            res.add(getRepeatSpace(totalSpace));
            return res;
        }

        StringBuilder[] sb = new StringBuilder[cnt];
        for (int i = 0; i < cnt; i++) {
            sb[i] = new StringBuilder((totalSpace + cnt - 1) / cnt);
        }
        for (int i = 0; i < totalSpace; i++) {
            sb[i % cnt].append(" ");
        }
        for (StringBuilder s : sb) {
            res.add(s.toString());
        }
        return res;
    }
}
