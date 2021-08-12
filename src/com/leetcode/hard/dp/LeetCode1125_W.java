package com.leetcode.hard.dp;

import java.util.*;

/**
 * 作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个
 * 「必要团队」（ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
 *
 * 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：
 *
 * 例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。
 * 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按 任意顺序 返回答案，题目数据保证答案存在。
 * 1 <= req_skills.length <= 16
 * 1 <= req_skills[i].length <= 16
 * req_skills[i] 由小写英文字母组成
 * req_skills 中的所有字符串 互不相同
 * 1 <= people.length <= 60
 * 0 <= people[i].length <= 16
 * 1 <= people[i][j].length <= 16
 * people[i][j] 由小写英文字母组成
 * people[i] 中的所有字符串 互不相同
 * people[i] 中的每个技能是 req_skills 中的技能
 * 题目数据保证「必要团队」一定存在
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-sufficient-team
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1125_W {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size();
        Map<String, Integer> skillToId = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skillToId.put(req_skills[i], i);
        }
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            mask[i] = convert(people.get(i), skillToId);
        }
        // req_skills的长度为16，考虑用这个长度表示状态数组
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int[] preState = new int[1 << n]; // 记录前一个状态(preState)
        int[] pre = new int[1 << n]; // 两个状态转换用到的物品(pre)
        for (int i = 0; i < m; i++) {
            for (int j = (1 << n) - 1; j >= 0; j--) { // 从后往前遍历
                if (dp[j] == -1) {
                    continue;
                }
                int state = j | mask[i];
                if (dp[state] == -1 || dp[state] > dp[j] + 1) {
                    dp[state] = dp[j] + 1;
                    preState[state] = j;
                    pre[state] = i;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        int state = (1 << n) - 1;
        while (state != 0) {
            res.add(pre[state]);
            state = preState[state];
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private int convert(List<String> list, Map<String, Integer> map){
        int res = 0;
        for(String s: list){
            if(map.containsKey(s)){
                res |= 1 << map.get(s);
            }
        }
        return res;
    }

//    链接：https://leetcode-cn.com/problems/smallest-sufficient-team/solution/0-1bei-bao-wen-ti-java-by-henrylee4/
}
