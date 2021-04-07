/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.exam;

import java.util.LinkedList;
import java.util.List;

/**
 * 功能描述
 *
 * @since 2020-07-24
 */
public class Test2 {
    String compress (String s1,  List<Integer> count) {
        StringBuilder str =  new StringBuilder();
        str.append(s1.charAt(0));
        count.add(1);
        for (int i = 1; i < s1.length(); i++) {
            if (s1.charAt(i) == s1.charAt(i - 1)) {
                int times = count.get(count.size() - 1) + 1;
                count.set(count.size() - 1, times);
                continue;
            }
            str.append(s1.charAt(i));
            count.add(1);
        }
        return str.toString();
    }

    public int numOfString(String words, String[] dic) {
        int res = 0;
        List<Integer> wordsCount = new LinkedList<>();
        String wordsComp = compress(words,wordsCount);
        for (String word : dic) {
            List<Integer> wordCount = new LinkedList<>();
            String wordComp = compress(word,wordCount);
            if (wordsComp.equals(wordComp)) {
                int i = 0;
                for (; i < wordsCount.size(); i++) {
                    if (wordsCount.get(i) < wordCount.get(i)) {
                        break;
                    }
                }
                res = i == wordsCount.size() ? res + 1 : res;
            }
        }
        return res;
    }
}
