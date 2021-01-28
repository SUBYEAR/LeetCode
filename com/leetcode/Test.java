package com.leetcode;

import java.util.*;

public class Test {

        boolean isConnectable(String a, String b) {
            int cnt = 0;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    ++cnt;
                }
            }
            return cnt == 1;
        }

        void buildGraph(List<String> wordList, HashMap<String, List<String>> graph) {
            for (int i = 0; i < wordList.size(); ++i) {
                List<String> lists = new ArrayList<String>();
                graph.put(wordList.get(i), lists);
                for (int j = 0; j < wordList.size(); j++) {
                    if (isConnectable(wordList.get(i), wordList.get(j))) {
                        graph.get(wordList.get(i)).add(wordList.get(j));
                    }
                }
            }
        }

        boolean dfsSearch(String beginWord, String endWord, HashMap<String, List<String>> graph,
                          List<String> result, Set<String> visit) {
            if (Objects.equals(beginWord, endWord)) {
                result.add(endWord);
                return true;
            }

            result.add(beginWord);
            visit.add(beginWord);
            for (int i = 0; i < graph.get(beginWord).size(); i++) {
                if (!visit.contains(graph.get(beginWord).get(i))) {
                    if (dfsSearch(graph.get(beginWord).get(i), endWord, graph, result, visit)) {
                        return true;
                    }
                }
            }
            result.remove(beginWord);
            visit.remove(beginWord);
            return false;
        }

        public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
            LinkedList<String> result = new LinkedList<>();
            if (!wordList.contains(endWord)) {
                return result;
            }

            HashMap<String, List<String>> graph = new HashMap<String, List<String>>();
            Set<String> visit = new HashSet<String>();

            if (!wordList.contains(beginWord)) {
                wordList.add(0, beginWord);
            }

            buildGraph(wordList, graph);
            dfsSearch(beginWord, endWord, graph, result, visit);
            return result;
        }


    private void swap(char[] arr,int i,int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    boolean isSwap (char[] arr,int left,int end) {
        for (int i = left; i < end; i++) {
            if (arr[i] == arr[end]) {
                return false;
            }
        }
        return true;
    }

    public void allPerm(char[] arr, int left, int end) {
        if (arr == null || arr.length == 0) {
            // 异常情况
            return;
        }

        if (left == end) {
            // 递归到底，返回时输出结果
            for (int i = 0; i <= end; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
            return;
        }

        for (int i = left; i <= end; i++) {
            if (isSwap(arr, left, i)) {
                swap(arr, left, i);
                allPerm(arr, left + 1, end);
                //回溯至交换前的样子
                swap(arr, left, i);
            }
        }
    }

    public String swap(String s, int i0, int i1) {
        if (i0 == i1)
            return s;
        String s1 = s.substring(0, i0);
        String s2 = s.substring(i0 + 1, i1);
        String s3 = s.substring(i1 + 1);
        return s1 + s.charAt(i1) + s2 + s.charAt(i0) + s3;
    }
     

    public void  combination(char[] arr, int begin, int num, StringBuffer result) { // 调用时需要还要用一个for循环进行列举
        if (num == 0) {
                System.out.println(result + " ");
        }

        if (begin == arr.length) {
            return;
        }
        result.append(arr[begin]);
        combination(arr, begin + 1, num - 1, result);
        result.deleteCharAt(result.length() - 1);
        combination(arr, begin + 1, num, result);
    }

}

