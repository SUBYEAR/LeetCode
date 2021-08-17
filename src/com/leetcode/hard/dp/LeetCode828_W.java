package com.leetcode.hard.dp;

import java.util.*;

/**
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 *
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 *
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。注意，某些子字符串可能是重复的，但你
 * 统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 *
 * 由于答案可能非常大，请将结果 mod 10 ^ 9 + 7 后再返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-unique-characters-of-all-substrings-of-a-given-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode828_W {
    Map<Character, List<Integer>> index;
    int[] peek;
    int N;

    public int uniqueLetterString(String s) {
    // U(S)表示字符串S中独特字符的个数, U_c(S)表示字符c在字符串S中是否为独立字符是为1，否则为0. 那么可以将 U(S) 分解为若干个 U_c(S)的和
    // 那么对于某个字符A, S中有多少个字串只包含A？举例如果S中位置10, 14, 20是字符A，其他位置均不为A. 那么以S[8]开始且只包含一个A的字串有4个
    // 分别以S[10],S[12],S[11],S[13]结尾. 以此类推,对于每一个开始位置S[i], 枚举字母表中的每个字符计算出只包含一个该字符的字串的数量
    // 再进行累加就可以得到所有以S[i]开始的子串U(S)值之和. 最后对所有的U(S)进行累加即为最终答案
        index = new HashMap<>();
        peek = new int[26];
        N = s.length();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            index.computeIfAbsent(c, x -> new ArrayList<>()).add(i);
        }

        long cur = 0, ans = 0;
        for (char c : index.keySet()) {
            index.get(c).add(N);
            index.get(c).add(N);
            cur += get(c);
        }

        for (char c : s.toCharArray()) {
            ans += cur;
            long oldv = get(c);
            peek[c - 'A']++;
            cur += get(c) - oldv;
        }
        return (int) ans % 1_000_000_007;
    }

    public long get(char c) {
        List<Integer> indexes = index.get(c);
        int i = peek[c - 'A'];
        return indexes.get(i + 1) - indexes.get(i);
    }

    // DP
    public int uniqueLetterString_DP(String s) {
        int len = s.length();
        int[] dp = new int[27]; // dp记录字母的有效次数
        long cnt = 0 , ret = 0; // cnt为以当前字母结尾的子串中，所有的有效结果
        int[] t = new int[26]; // t 记录当前字母的上一个位置
        Arrays.fill(t , -1);
        int mod = 1000000007;
        for(int i = 0 ; i < len ;i++){
            int cur = s.charAt(i)-'A';
            int lastIdx = t[cur];
            // 其他字母不会受影响只有当前的字母的值需要更新
            cnt += (i - lastIdx - dp[cur]); // 当前字母结尾的有效结果要先减去旧的值dp[cur]再加上新的值i-lastidx
            cnt %= mod;

            ret += cnt;
            ret %= mod;

            dp[cur] = i - lastIdx;
            t[cur] = i;
        }
        return (int)ret;
    }
//    链接：https://leetcode-cn.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/solution/java-dp-by-ctysss-k750/

}
