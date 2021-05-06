package com.leetcode.medium.review.bitwise;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 *  
 *
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 *
 * 输入：S = "12345"
 * 输出：["12345"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode784 {
    public List<String> letterCasePermutation(String S) {
        int B = 0;
        for (char c : S.toCharArray())
            if (Character.isLetter(c))
                B++;

        List<String> ans = new ArrayList();

        for (int bits = 0; bits < 1 << B; bits++) {
            int b = 0;
            StringBuilder word = new StringBuilder();
            for (char letter : S.toCharArray()) {
                if (Character.isLetter(letter)) {
                    if (((bits >> b++) & 1) == 1)
                        word.append(Character.toLowerCase(letter));
                    else
                        word.append(Character.toUpperCase(letter));
                } else {
                    word.append(letter);
                }
            }

            ans.add(word.toString());
        }

        return ans;
    }
}

//     public List<String> letterCasePermutation(String S) {
//        List<String> res = new LinkedList<>();
//        char[] array = S.toCharArray();
//        for (int i = 0; i < array.length; i++) {
//            if (Character.isLetter(array[i])) {
//                array[i] = Character.toLowerCase(array[i]);
//            }
//        }
//
//        ArrayList<StringBuilder> ret = new ArrayList<>();
//        ret.add(new StringBuilder(new String(array)));
//        for (int i = 0; i < array.length; i++) {
//            if (Character.isLowerCase(array[i])) {
//                ArrayList<StringBuilder> temp = new ArrayList<>();
//                for (StringBuilder element : ret) {
//                    temp.add(new StringBuilder(element));
//                }
//                for (StringBuilder element : temp) {
//                    element.setCharAt(i, Character.toUpperCase(array[i]));
//                }
//                ret.addAll(temp);
//            }
//        }
//        for (StringBuilder element : ret) {
//            res.add(element.toString());
//        }
//        return res;
//    }

// 官网的复制解法
//     public List<String> letterCasePermutation(String S) {
//        List<StringBuilder> ans = new ArrayList();
//        ans.add(new StringBuilder());
//
//        for (char c: S.toCharArray()) {
//            int n = ans.size();
//            if (Character.isLetter(c)) {
//                for (int i = 0; i < n; ++i) {
//                    ans.add(new StringBuilder(ans.get(i)));
//                    ans.get(i).append(Character.toLowerCase(c));
//                    ans.get(n+i).append(Character.toUpperCase(c));
//                }
//            } else {
//                for (int i = 0; i < n; ++i)
//                    ans.get(i).append(c);
//            }
//        }
//
//        List<String> finalans = new ArrayList();
//        for (StringBuilder sb: ans)
//            finalans.add(sb.toString());
//        return finalans;
//    }

// 回溯解法
// public class Solution {
//
//    public List<String> letterCasePermutation(String S) {
//        List<String> res = new ArrayList<>();
//        char[] charArray = S.toCharArray();
//        dfs(charArray, 0, res);
//        return res;
//    }
//
//    private void dfs(char[] charArray, int index, List<String> res) {
//        if (index == charArray.length) {
//            res.add(new String(charArray));
//            return;
//        }
//
//        dfs(charArray, index + 1, res);
//        if (Character.isLetter(charArray[index])) {
//            charArray[index] ^= 1 << 5;
//            dfs(charArray, index + 1, res);
//        }
//    }
//}
//
//作者：liweiwei1419
//链接：https://leetcode-cn.com/problems/letter-case-permutation/solution/shen-du-you-xian-bian-li-hui-su-suan-fa-python-dai/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。