package com.leetcode;

import com.leetcode.hard.dp.LeetCode956_W;


import java.util.*;

public class Main {
    // while (h < N / 3) { h = 3 * h + 1;} // (3^h - 1) / 2

    static void customClass() {
        // write your code here
        Person[] p = new Person[3];
        p[0] = new Person();
        p[1] = new Person();
        p[2] = new Person();

        p[0].setAge(2);
        p[0].setName("Sam");

        p[1].setAge(3);
        p[1].setName("Smh");

        p[2].setAge(5);
        p[2].setName("Same");

        List<Person> list = Arrays.asList(p); // Collections.addAll(list, p);
        Collections.sort(list); // 内部排序安装名字字典序
        System.out.println(list);

        list.sort((o1, o2) -> o1.getAge() - o2.getAge());
        // Collections.sort(list, (o1, o2) -> o1.getAge() - o2.getAge()); // 外部排序使用匿名类
        System.out.println(list);
    }

    static void sortArray() {
        int[][] a = {{0, 1}, {2, 5}, {0, 2},};
        Arrays.sort(a, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for (int[] arr : a) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
        }
    }

    public static int labelChecker(int[] labels) {
        if (labels == null || labels.length == 0) {
            return 0;
        }

        int[] temp = Arrays.copyOf(labels, labels.length);
        Arrays.sort(temp);

        int index = 0;
        int result = 0;
        for (int number : temp) {
            if (number != labels[index++]) {
                ++result;
            }
        }

        return result;
    }

    private static void practiceMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Bod", 12);
        map.put("Alice", 15);
        map.put("Curry", 18);
        map.put("Martin", 30);

        System.out.println("EntrySet iterate:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) { // 使用EntrySet遍历
            String mapKey = entry.getKey();
            Integer mapValue = entry.getValue();
            System.out.println(mapKey + ":" + mapValue);
        }

        System.out.println("KeySet iterate:");
        for (String key : map.keySet()) { // 还有获得value试图的方法map.values()
            Integer value = map.get(key);
            System.out.println(key + ":" + value);
        }

        // Set中的迭代器遍历 Iterator<String> iterator = map.keySet().iterator();
        Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> item = entryIterator.next();
            String mapKey = item.getKey();
            Integer mapValue = item.getValue();
            System.out.println(mapKey + ":" + mapValue);
        }
    }

    public static void main(String[] args) {
        // Integer[] boxNums = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        // List<Integer> set = Stream.of(boxNums).collect(Collectors.toList()); toSet()
        // Brace test = new Brace();
        // DbNoise test1 = new DbNoise();
        // int[][] noise = new int[][] {{4, 4, 3}, {2, 2, 8}};
        // System.out.println(test.spreadNoise(20, 20, noise));
        // System.out.println(test1.spreadNoise(20, 20, noise));
        // LeetCode8 test = new LeetCode8();
        // System.out.println(test.myAtoi("2147483648"));
        // String s = null;
        // int a = 1;
        // s = a == 1 ? "hello" : null;
        // System.out.println(s);
        // System.out.println(Optional.ofNullable(s).orElse(s));
        // ListNode node1 = new ListNode(2);
        // ListNode node2 = new ListNode(4);
        // ListNode node3 = new ListNode(3);
        // ListNode node4 = new ListNode(1);
        // node1.next = node2;
        // node2.next = node3;
        // node3.next = node4;
        // LeetCode147 test = new LeetCode147();
        // ListNode.print(test.insertionSortList(node1));
        // LeetCode13 test = new LeetCode13();
        // System.out.println(test.romanToInt("IXL"));
        // int[] arr = new int[] {31, 3, 34};
        // LeetCode12 test = new LeetCode12();
        // System.out.println(test.intToRoman(110));
        // Permutation p = new Permutation();
        // String s = "ABC";
        // p.combination(s);
        //
        // p.getPermutations(s.toCharArray(), 0, new LinkedList<>());
        // for (String ss : p.res) {
        //     System.out.println(ss);
        // }
        // p.combination(s);
        // LeetCode1371 test = new LeetCode1371();
        // System.out.println(test.isNumber("+100"));
        // System.out.println(test.isNumber("5e2"));
        // System.out.println(test.isNumber("-123"));
        // System.out.println(test.isNumber("3.14"));
        // System.out.println(test.isNumber("0123"));
        // System.out.println(test.isNumber("12e"));
        // System.out.println(test.isNumber("1a3.14"));
        // System.out.println(test.isNumber("1.2.3"));
        // System.out.println(test.isNumber("+-5"));
        // System.out.println(test.isNumber("-5E-16"));
        // System.out.println(test.isNumber("12e+5.4"));
        // LCP03 test = new LCP03();
        // System.out.println(test.robot("RUUR", obstacles, 7856, 9033));

        // List<String> arrStr = new ArrayList<>();
        // for (String s : arrStr) {
        // System.out.println(s);
        // }
        // LinkedHashMap<String, String> accessOrderTrue = new LinkedHashMap<>(2, 0.75f, true);
        // accessOrderTrue.put("1", "1");
        // accessOrderTrue.put("2", "2");
        // accessOrderTrue.put("3", "3");
        // accessOrderTrue.put("4", "4");
        // System.out.println("put后的数据：" + accessOrderTrue);
        // accessOrderTrue.get("2");
        // accessOrderTrue.get("3");
        // System.out.println("get后的数据" + accessOrderTrue);
        // accessOrderTrue.put("1", "5");
        // LeetCode15 test = new LeetCode15();
        // test.threeSum(new int[] {-1, 0, 1, 2, -1, -4});
        // TreeNode n1 = new TreeNode(1);
        // TreeNode n2 = new TreeNode(2);
        // TreeNode n3 = new TreeNode(3);
        // TreeNode n4 = new TreeNode(4);
        // TreeNode n5 = new TreeNode(5);
        // TreeNode n6 = new TreeNode(6);
        // TreeNode n7 = new TreeNode(7);
        // TreeNode n8 = new TreeNode(8);
        // n1.left = n2;
        // n1.right = n3;
        // n2.left = n4;
        // n2.right = n5;
        // n3.left = n6;
        // n5.left = n7;
        // n5.right = n8;
        //
         TreeNode nn1 = new TreeNode(1), nn2 = new TreeNode(2), nn3 = new TreeNode(3);
         TreeNode nn4 = new TreeNode(4), nn5 = new TreeNode(2), nn6 = new TreeNode(4);
         TreeNode nn7 = new TreeNode(4), nn8 = new TreeNode(7), nn9 = new TreeNode(4);
         nn1.left = nn2; nn1.right = nn3;
         nn2.left = nn4;
         nn3.left = nn5; nn3.right = nn6;
         nn5.left = nn7;
        // nn5.right = nn7;
        //Test2 test = new Test2();
        //int[]  arr1 = new int[] {2,3,1,3,2,4,6,7,9,2,19};
//        int[]  arr2 = new int[] {1, 2, 3};
//        Test3 test = new Test3();
        //String[] dic = new String[] {"bet", "beter", "brete", "beteer", "ber","beteeer"};
        //System.out.println(test.numOfString("beeettteerr", dic));
//        Permutation t  = new Permutation();
//        List<Integer> l = new LinkedList<>();
//        List<List<Integer>> result = new LinkedList<>();
//        t.getSubArr(arr2, 0, 2, l, result);
//        System.out.println(result.size());
        //System.out.println(test.minOperations(arr2, 2));
        //  LeetCode1488 test = new LeetCode1488();
        //  for (int val : test.avoidFlood(new int[] {2,3,0,0,3,1,0,1,0,2,2}))
        //      System.out.print(val + " ");
        // String s = "aaa/*  *  *";
        // System.out.println(s.matches(".*/\\*(?:\\*|\\s)*"));

        // Node node1 = new Node(1);
        // Node node2 = new Node(2);
        // Node node3 = new Node(3);
        // Node node4 = new Node(4);
        //
        // node1.neighbors.add(node2);node1.neighbors.add(node4);
        // node2.neighbors.add(node1);node2.neighbors.add(node3);
        // node3.neighbors.add(node2);node3.neighbors.add(node4);
        // node4.neighbors.add(node1);node4.neighbors.add(node3);
        int[][] heights = new int[4][];
        heights[0] = new int[] {1,2};
        heights[1] = new int[] {3};
        heights[2] = new int[] {3};
//        Arrays.sort(heights, (o1, o2) -> o2[1] - o1[1]);
        int[] arr2 = new int[] {1,2,3,6};

        int[] arr1 = new int[]{20,48,33,16,19,44,14,31,42,34,38,32,27,7,22,22,48,18,48,39};

        int[][] matrix = new int[][] {
                {1,50},
//                {3,7,15},{5,12,12},{15,20,10},{19,24,8}
        };
        String[] dic = new String[] { "me", "time",};
        LeetCode956_W t = new LeetCode956_W();
        System.out.println(t.tallestBillboard(arr2));
    }

    static private long gcd(long a,long b){
        return b == 0? a:gcd(b,a % b);
    }

    static void get(int k) {
//        String str = Stream.generate(() -> "0").limit(k).collect(Collectors.joining());
//        StringBuilder s = new StringBuilder(str);
//        for (int i = 0; i < k; i++) {
//
//        }
        int mask = k;
        while (k != 0) {
            System.out.println(Integer.toBinaryString(k));
            k = (k - 1) & mask;
        }

    }

    static public boolean isCovered(int[][] ranges, int left, int right) {
        Set<Integer> target = new HashSet<>();
        for (int i = left; i <= right; i++) {
            target.add(i);
        }

        Set<Integer> rangesSet = new HashSet<>();
        for (int[] arr : ranges) {
            for (int i = arr[0]; i <= arr[1]; i++) {
                rangesSet.add(i);
            }
        }

        return rangesSet.containsAll(target);
    }

    static public int majorityElement(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            int cnt = freq.getOrDefault(num, 0) + 1;
            if (cnt > n / 2) {
                return num;
            }
            freq.put(num, cnt);
        }
        return -1;
    }

    public static void build(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            System.out.println("from:" + arr[i - 1] + ", to:" + arr[i]);
        }
        System.out.println("from:" + arr[len - 1] + ", to:" + arr[0]);
    }

    public static void printIndent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("    ");
        }
    }

    static int longestOnes(int[] A, int K) {
        int res = 0, i = 0, j = 0;
        for (; i < A.length; i++) {
            if (A[i] == 0) {
                if (K > 0) {
                    K--;
                } else {
                    res = Math.max(res, i - j);
                    while (A[j++] == 1) {
                        System.out.println(A[j]);
                    }
                }
            }
        }
        return Math.max(res, i - j);
    }

    public static void printArr(int[][] A) {
        if (A == null) {
            return;
        }

        for (int[] a : A) {
            for (int v : a) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

        //斜着打印数组
//        for (int len = 1; len < A.length; len++) {
//            for (int i = 0; i < A.length - len; i ++) {
//                int j = len + i - 1;
//                System.out.println(A[i][j]);
//            }
//        }
    }
}
