package com.leetcode.exam;

import java.util.*;

public class Test0723 {
    // #Title_1 管理展厅人数
    // 有N个展厅每个展厅的报名人数记数nums,因疫情原因所有展厅总人数上限为cnt,若报名人数大于cnt则需要限制展厅入场人数为limit，需要根据输入计算
    // 出limit.限制规则如下如果总报名人数少于cnt则可全部入场返回-1; 如果报名人数大于cnt则需要设定limit超过limit的报名人数的展厅需要将入场
    // 人数限制为limit,其余未达到limit的展厅的报名人可以全部入场
    // 用例1：[1,4,2,5,5,1,6] cnt:13 输出2
    // 用例2：[1,1] cnt:1 输出0
    public int mangeTourist(int[] nums, int cnt) {
        if (nums == null || nums.length == 0) {
            return  -1;
        }
        int sum = Arrays.stream(nums).reduce(Integer::sum).getAsInt();
        if (sum < cnt) {
            return -1;
        }

        int low = 0;
        int high = Arrays.stream(nums).max().getAsInt();
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (isMatched(nums, mid, cnt)) {
                high = mid - 1;
            } else  {
                low = mid + 1;
            }
        }
        return high;
    }

    private boolean isMatched(int[] nums, int limit, int cnt) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.min(num, limit);
        }
        return sum > cnt;
    }

    // #Title2_文件传输系统
    private static class DataMachine {
        private Map<Integer, Integer> dataMachineMap = new HashMap<>();
        private int contribution = 10;
        public DataMachine() {

        }
    }

    private DataMachine[] dataMachines;
    private static final int FINAL_ID = -1;

    public void dataMachineSystem(int num) { // 初始化设备数量为num
        dataMachines = new DataMachine[num + 1];
        for (int i = 1; i <= num; i++) {
            dataMachines[i] = new DataMachine();
        }
    }

    // 设备A发生编号为dataId的数据给设备B。若设备B已有此数据则不接收并返回0.否则接受该数据设备A和B都活留存数据dataId,并返回1
    // 若发送设备没有该数据表示系统中还不存在该数据,则自己产生编号为dataId的数据再发送
    public int transferData(int machineA, int machineB, int dadaId) {
        return transferData(machineA, machineB, dadaId, true);
    }

    private int transferData(int machineA, int machineB, int dataId, boolean needCal) {
        DataMachine dataMachineA = dataMachines[machineA];
        DataMachine dataMachineB = dataMachines[machineB];

        Map<Integer, Integer> dataB = dataMachineB.dataMachineMap;
        if (dataB.containsKey(dataId)) {
            return 0;
        }
        dataB.put(dataId, machineA);
        Map<Integer, Integer> dataA = dataMachineA.dataMachineMap;
        if (needCal) {
            dataMachineA.contribution += 10;
        }
        if (!dataA.containsKey(dataId)) {
            dataA.put(dataId, FINAL_ID);
            return 1;
        }
        calContribution(machineA, dataId);
        return 1;
    }

    private void calContribution(int machine, int dataId) {
        DataMachine cur = dataMachines[machine];
        Map<Integer, Integer> curMap = cur.dataMachineMap;
        int curId = curMap.getOrDefault(dataId, FINAL_ID);
        while (curId != FINAL_ID && curMap.containsKey(dataId)) {
            DataMachine father = dataMachines[curMap.get(dataId)];
            father.contribution += 10;
            curMap = father.dataMachineMap;
            curId = father.dataMachineMap.getOrDefault(dataId, FINAL_ID);
        }

    }

    // 设备群发数据给所有设备.已经有此数据的设备不会接受,返回接受了此数据的设备数量.发送后machine和所有接受此数据的设备都会留存此数据
    // 若发送设备没有该数据表示系统中还不存在该数据,则自己产生编号为dataId的数据再发送
    public int transferDataToAll(int machine, int dataId) {
        int cnt = 0;
        for (int i = 1; i < dataMachines.length; i++) {
            if (i == machine) {
                continue;
            }

            if (transferData(machine, i, dataId, false) == 1) {
                cnt++;
            }
        }
        dataMachines[machine].contribution += 10 * cnt;
        return cnt;
    }

    // 查询设备machine的贡献度, 贡献度的计算规则如下:对于每个接受到数据的设备其发送方的贡献度增加10;注意对于群发,发送方增加贡献量为接受到此数据
    // 的设备数量*10.贡献会传递:如果发送方的数据来源于另一设备,那么该设备的贡献度也增加10;贡献继续传递直至数据的产生方
    public int queryContribution(int machine) {
        return dataMachines[machine].contribution;
    }

    // #Title_3
    /*
某社团有2*num个团队，编号为0—(2*num - 1)。有一次大型演出，需要两个团队一起出节目，两个团队出节目有一个成本。大型演出规定，社团里的所有团队都需要出演，并只能出演一次，我们取出演的所有节目总成本最高的成本，叫做昂贵成本，设计算法计算最低的昂贵成本。

用例一：

[[0,1,250],[0,3,10],[1,2,25],[1,3,80],[2,3,90]] //即一共有5个预选节目，[0,1,250]表示，社团0和社团1共同演出，成本为250。

昂贵成本为：25。

因为两种组合：

[0,1,250]和[2,3,90] 成本为250
[0,3,10],[1,2,25] 成本为25
所以为25。

思路：回溯剪枝，剪到不超时为止，剪掉社团重复的选择或者已经比当前昂贵成本高的选择，没有考虑更好的剪枝方式了
*/

    private int min = Integer.MAX_VALUE;

    public int cooperativePerformance(int num, int[][] program) {
        backtrack(num, program, new ArrayList<>(), new HashSet<>(), 0);
        return this.min;
    }

    private void backtrack(int num, int[][] program, List<Integer> money, Set<Integer> set, int index) {
        if (set.size() == 2 * num) {
            int temp = Integer.MIN_VALUE;
            for (int ele : money) {
                temp = Math.max(temp, ele);
            }
            this.min = Math.min(this.min, temp);
            return;
        }
        for (int i = index; i < program.length; i++) {
            //剪枝的都在这里了
            if (set.contains(program[i][0]) || set.contains(program[i][1]) || program[i][2] > this.min) {
                continue;
            }
            set.add(program[i][0]);
            set.add(program[i][1]);
            money.add(program[i][2]);
            backtrack(num, program, money, set, i + 1);
            set.remove(program[i][0]);
            set.remove(program[i][1]);
            money.remove(money.size() - 1);
        }
    }

/*
有2n2n2n 个社团， 表演nnn个节目，且不能重复参演。则大概就是要求将这些社团两两不重复的组合起来，同时要求组合中的单个最高成本是所有组合方案中最低的。

解题思路：

首先需要解决的问题是如何判断所有社团不重复的全部组合进来，如果使用set的话每次判断都需要遍历一遍，看某个社团是否已被选中。这里可以看下数据范围，节目数最多是8，那么社团最多有16个，某个社团是否选中的状态空间最多216=655362^{16} = 655362
16
 =65536种。于是可以使用状态压缩方法，通过位运算来快速判断当前社团是否已经被选中。具体就是开一个655366553665536大小的数组，它代表所有状态。

有了状态表达方法后，就是如何找到有效的组合。

可以先从暴力算法入手，这个题目的暴力应该就是直接DFS搜索+回溯，这种方法复杂度很高。但是在搜索过程中如果遇到重复这种非法状态，或者当前搜索状态之前出现过，并且之前的状态成本更低，这些情况都是可以及时退出的，或者先对社团进行排序，再搜索时可排除较多无用搜索，也就是剪枝。

DFS的时候，在搜索的过程中更新当前组合的最大成本，直到得到一个有n个组合的状态，这时可以更新一下完整组合的最小成本。但是DFS只会根据组合去计算成本，而不会根据状态，比如有如下社团：

0 2 10

1 3 20

0 1 20

2 3 30

前两个社团和后两个社团都可以组成 0 1 2 3的两个节目，且前两个组合的成本会低一些。后两个社团出现在后面，DFS回溯的时候很可能会出现使用该两个社团再次往后搜索更多社团的无效操作，然而它们两个组合的状态都是0 1 2 3，理想的做法应该是根据 0 1 2 3这个状态的最小成本20去搜索，而不是根据使用哪对组合的具体情况去搜索。

这时就可以考虑动态规划了，它可以使用不同组合之前的状态转移去搜索。 同时可以看到一个新的组合要加入的话，只与之前哪些社团已经参加有关，而与它们具体是怎么组合的无关，并且之前状态的成本的最优值是已确定的，当前组合加入后的最优解也是由当前状态决定的。因此该问题满足无后效性 ，是可以使用动态规划的。同时状态转移方程也比较明显了。

dp[statu∣cur]=min(dp[statu∣cur],max(dp[statu],curcost))dp[statu | cur] = min(dp[statu | cur], max(dp[statu], cur_cost))
dp[statu∣cur]=min(dp[statu∣cur],max(dp[statu],cur
c
​
 ost))

含义是当前组合加入后的状态 = 之前状态 与 当前组合成本的最大值 再和加入后状态的成本比较的最小值。

Tips: 由推导过程和代码可以看出动态规划只关注状态转移（某个社团是否被选中），而DFS需要不断尝试所有组合去搜索结果。
	#include <iostream>
	#include <cstdio>
	#include <vector>
	#include <cstring>

	using namespace std;

	int n; // 社团数量
	int dp[1 << 16];

	struct Combination {
	    int a;
	    int b;
	    int cost;
	    Combination(int t1, int t2, int t3) : a(t1), b(t2), cost(t3) {}
	};

	int solve(vector<Combination>& cb, int num_comb)
	{
	    memset(dp, 0x3f3f3f3f, sizeof dp);
	    dp[0] = 0;   // 初始无社团参加, cost为0

	    int all = (1 << 2 * num_comb) - 1;
	    for (int statu = 0; statu < all; ++statu)  // 枚举所有状态
	    {
	        if (dp[statu] == 0x3f3f3f3f)
	            continue;
	        for (const auto& com : cb)           // 枚举所有组合
	        {
	            int cur = (1 << com.a) | (1 << com.b);              // 当前组合的状态
	            if ((statu & (1 << com.a) || statu & (1 << com.b))) // 判断是否之前参加过
	                continue;
	            dp[statu | cur] = min(dp[statu | cur], max(dp[statu], com.cost)); // 更新状态
	        }
	    }

	    return dp[all];
	}

	int main()
	{
	    std::ios::sync_with_stdio(false);
	    cin >> n;
	    int a, b, value;

	    vector<Combination> cb;
	    while (cin >> a >> b >> value)
	    {
	        cb.push_back(Combination(a, b, value));
	    }

	    cout << solve(cb, n) << endl;

	    return 0;
	}
*/
}
