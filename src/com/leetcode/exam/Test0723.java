package com.leetcode.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
}
