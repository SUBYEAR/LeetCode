/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.stringprocessing;

import java.util.*;

/**
 * 如果出现下述两种情况，交易 可能无效：
 * 交易金额超过 ¥1000
 * 或者，它和另一个城市中同名的另一笔交易相隔不超过 60 分钟（包含 60 分钟整）
 * 每个交易字符串 transactions[i] 由一些用逗号分隔的值组成，这些值分别表示交易的名称，时间（以分钟计），金额以及城市。
 * 给你一份交易清单 transactions，返回可能无效的交易列表。你可以按任何顺序返回答案。
 *
 * @since 2020-06-11
 */
public class LeetCode1169 {
    public List<String> invalidTransactions(String[] transactions) {
        int length = transactions.length;
        List<String[]> storage = new ArrayList<>();
        List<String> result = new ArrayList<>();
        boolean[] alreadyPut = new boolean[length];
        String name = "";
        String city = "";
        int time = 0;

        for (int i = 0; i < length; i++) {
            String[] afterSplit = transactions[i].split(",");
            storage.add(afterSplit);
            alreadyPut[i] = false;
        }

        for (int i = 0; i < length; i++) {
            int j = i + 1;
            String[] itemp = storage.get(i);
            name = itemp[0];
            city = itemp[3];
            time = Integer.parseInt(itemp[1]);

            if (Integer.parseInt(itemp[2]) > 1000 && !alreadyPut[i]) { // 交易金额超过1000
                result.add(transactions[i]);
                alreadyPut[i] = true;
            }

            for (; j < length; j++) {
                String[] jtemp = storage.get(j);
                if (name.equals(jtemp[0]) && Math.abs(time - Integer.parseInt(jtemp[1])) <= 60
                    && !city.equals(jtemp[3])) {
                    if (!alreadyPut[j]) {
                        result.add(transactions[j]);
                        alreadyPut[j] = true;
                    }

                    if (!alreadyPut[i]) {
                        result.add(transactions[i]);
                        alreadyPut[i] = true;
                    }
                }
            }
        }
        return result;
    }

    public List<String> invalidTransactions2(String[] transactions) {
        Transaction[] trans = new Transaction[transactions.length];
        int index = 0;
        for (String tran : transactions) {
            String[] strings = tran.split(",");
            trans[index++] =
                new Transaction(strings[0], Integer.parseInt(strings[1]), Integer.parseInt(strings[2]), strings[3]);
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < trans.length; i++) {
            if (trans[i].money > 1000) {
                list.add(transactions[i]);
            } else {
                for (int j = 0; j < trans.length; j++) {
                    if (i != j && trans[j].name.equals(trans[i].name) && !trans[j].city.equals(trans[i].city)
                        && Math.abs(trans[j].time - trans[i].time) <= 60) {
                        list.add(transactions[i]);
                        break;
                    }
                }
            }
        }

        return list;
    }

    public List<String> invalidTransactions3(String[] transactions) {
        HashMap<String, List<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (String transaction : transactions) {
            String[] split = transaction.split(",");
            String key = split[0];
            if (map.containsKey(key)) {
                List<String> strings = map.get(key);
                strings.add(transaction);
            } else {
                List<String> strings = new ArrayList<>();
                strings.add(transaction);
                map.put(key, strings);
            }
        }
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            List<String> strings = map.get(s);
            for (int i = 0; i < strings.size(); i++) {
                String stringI = strings.get(i);
                String[] splitI = stringI.split(",");

                if (Integer.parseInt(splitI[2]) > 1000) {
                    set.add(stringI);
                }
                for (int j = i + 1; j < strings.size(); j++) {
                    String stringJ = strings.get(j);
                    String[] splitJ = stringJ.split(",");

                    if (Math.abs(Integer.parseInt(splitI[1]) - Integer.parseInt(splitJ[1])) <= 60
                        && !splitI[3].equals(splitJ[3])) {
                        set.add(stringI);
                        set.add(stringJ);
                    }
                }
            }
        }
        ArrayList<String> list = new ArrayList<>();
        for (String s : set) {
            list.add(s);
        }
        return list;
    }

    class Transaction {
        String name;

        Integer time;

        Integer money;

        String city;

        public Transaction(String name, Integer time, Integer money, String city) {
            this.name = name;
            this.time = time;
            this.money = money;
            this.city = city;
        }
    }
}
