package com.leetcode.exam;

import java.util.LinkedHashMap;

public class OJ0319 {
    // #Title_3 给定一组队列和缓存大小，只有加入缓存才能访问. 求访问完一组队列的最小操作次数(从缓存中加入数据算一次,删除不算)
    // 比如[1,2,3,1,2,3] 缓存大小2. 则最小操作数是4次
    LinkedHashMap<Integer, Boolean> map = new LinkedHashMap<>();
    public int queryDataBase(int cacheSize, int[] ids) { // cache 小于100 访问序列长度小于1000
        int result = 0;
        for (int i = 0; i < ids.length; i++) {
            int num = ids[i];
            if (map.get(num) != null) {
                continue;
            }
            if (cacheSize > map.size()) {
                map.put(num, true);
            } else {
                handle(num, ids, i);
                map.put(num, true);
            }
            result++;
        }
        return result;
    }
    private void handle(int num, int[] ids, int index) {
        LinkedHashMap<Integer, Boolean> tmp = new LinkedHashMap<>();
        for (int i = index + 1; i < ids.length; i++) {
            if (tmp.size() < map.size() - 1) {
                if (map.get(ids[i]) != null && map.get(ids[i]) == Boolean.TRUE) {
                    tmp.put(ids[i], true);
                }
                if (tmp.size() == map.size() - 1) {
                    map = new LinkedHashMap<>(tmp);
                    return;
                }
            }
        }
        map = tmp;
    }
}
