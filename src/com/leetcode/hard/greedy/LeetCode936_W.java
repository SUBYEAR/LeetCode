package com.leetcode.hard.greedy;

import java.util.*;

/**
 * 你想要用小写字母组成一个目标字符串 target。 
 * <p>
 * 开始的时候，序列由 target.length 个 '?' 记号组成。而你有一个小写字母印章 stamp。
 * <p>
 * 在每个回合，你可以将印章放在序列上，并将序列中的每个字母替换为印章上的相应字母。你最多可以进行 10 * target.length  个回合。
 * <p>
 * 举个例子，如果初始序列为 "?????"，而你的印章 stamp 是 "abc"，那么在第一回合，你可以得到 "abc??"、"?abc?"、"??abc"。（请注意，印章必须完全包含在序列的边界内才能盖下去。）
 * <p>
 * 如果可以印出序列，那么返回一个数组，该数组由每个回合中被印下的最左边字母的索引组成。如果不能印出序列，就返回一个空数组。
 * <p>
 * 例如，如果序列是 "ababc"，印章是 "abc"，那么我们就可以返回与操作 "?????" -> "abc??" -> "ababc" 相对应的答案 [0, 2]；
 * <p>
 * 另外，如果可以印出序列，那么需要保证可以在 10 * target.length 个回合内完成。任何超过此数字的答案将不被接受。
 * 提示：
 * <p>
 * 1 <= stamp.length <= target.length <= 1000
 * stamp 和 target 只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stamping-the-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode936_W {
    // 逆推分析将target变为初始状态的序列????
    public int[] movesToStamp(String stamp, String target) {
        // 将 target 中从第 i 个字符开始的长度为 stamp.length 的字符串称为第 i 个窗口
        // 第一步时，只有和 stamp 完全匹配的窗口才能进行戳印，而被戳印的所有位置都会变成问号 ?，在后续的戳印过程中，问号可以匹配任意字符。
        // 每个窗口维护两个集合made和todo.前者表示和stamp可以匹配的位置,后者表示不可以匹配的位置（后者中只有某个位置的字符变成了问号，它才会变成可以匹配的位置）。
        // 只有当一个窗口的todo集合为空这个窗口才可以被戳印,从而把一些字符变成问号
        // 用一个队列存储所有因为戳印而变成问号的字符位置.
        int M = stamp.length();
        int N = target.length();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] done = new boolean[N];
        Stack<Integer> ans = new Stack<>();
        List<Node> A = new ArrayList<>();

        for (int i = 0; i <= N - M; ++i) {
            // For each window [i, i+M), A[i] will contain
            // info on what needs to change before we can
            // reverse stamp at this window.

            Set<Integer> made = new HashSet<>();
            Set<Integer> todo = new HashSet<>();
            for (int j = 0; j < M; ++j) {
                if (target.charAt(i + j) == stamp.charAt(j))
                    made.add(i + j);
                else
                    todo.add(i + j);
            }

            A.add(new Node(made, todo));

            // If we can reverse stamp at i immediately,
            // enqueue letters from this window.
            if (todo.isEmpty()) {
                ans.push(i);
                for (int j = i; j < i + M; ++j) {
                    if (!done[j]) {
                        queue.add(j); // 队列初始时包含所有todo集合一开始就为空的窗口对应的位置
                        done[j] = true; // done数组标记target中每一位是否标记为?
                    }
                }
            }
        }

        // For each enqueued letter (position),
        while (!queue.isEmpty()) {
            int i = queue.poll();

            // For each window that is potentially affected,
            // j: start of window
            // 当取出队列中的一个位置时遍历所有覆盖了该位置的窗口并且更新这些窗口的todo集合
            for (int j = Math.max(0, i - M + 1); j <= Math.min(N - M, i); ++j) {
                if (A.get(j).todo.contains(i)) {  // This window is affected
                    A.get(j).todo.remove(i);
                    if (A.get(j).todo.isEmpty()) { // 如果todo集合变为空说明产生了一个新的可被戳印的窗口
                        ans.push(j);
                        for (int m : A.get(j).made) { // 于是把这个窗口中所有未变成问号的字符的位置添加入队列中。
                            if (!done[m]) {
                                queue.add(m);
                                done[m] = true;
                            }
                        }
                    }
                }
            }
        }

        for (boolean b : done)
            if (!b) return new int[0];

        int[] ret = new int[ans.size()];
        int t = 0;
        while (!ans.isEmpty())
            ret[t++] = ans.pop();

        return ret;
    }

    private class Node {
        Set<Integer> made, todo;

        public Node(Set<Integer> m, Set<Integer> t) {
            made = m;
            todo = t;
        }
    }
}
