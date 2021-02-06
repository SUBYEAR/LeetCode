import com.leetcode.easy.LeetCode724;

import java.util.List;

public class main {
    public static void main(String[] args) {
//        List<String> data = new ArrayList<>();
//        String[] tmp = {"hot","cog","dot","dog","hit","lot","log"};
//        data = Arrays.asList(tmp);
//
//        Test t = new Test();
//        List arrList = new ArrayList(data);
//        List<String> res = t.findLadders("hit", "cog", arrList);
//        System.out.println(res);
//        String a = "1200";
//        System.out.println(Integer.parseInt(a));
//        for (String s : res) {
//            System.out.println(s);
//        }
//        String str = "AAC";
//        char[] arrCh = str.toCharArray();
//        t.allPerm(arrCh,0,arrCh.length - 1);
//        int[] arr = new int[] { 1,10 };
/*        int len = arr.length;
        List<int[]> sub = new LinkedList<>();
        for (int i = len; i > 0; i--) {
            for (int j = 0; j + i <= len; j++) {
                int[] ints = Arrays.copyOfRange(arr, j, j + i);
                sub.add(ints);
            }
        }

        int sum = 0;
        for (int[] subArr : sub) {
            Arrays.sort(subArr);
            for (int num: subArr) System.out.print(num + " ");
            System.out.println();
            sum += subArr[0];
        }
        System.out.println("Sum value is: " + sum);

        int[][] sortArr = new int[][] {
                {1, 2},
                {2, 2},
                {1, 1},
        };

        Arrays.sort(sortArr, (a, b)->{
           return  a[0] == b[0] ?  a[1] - b[1] : a[0] - b[0];
        });
        for (int[] a: sortArr) {
            for (int num : a) {
                System.out.print(num + " ");
            }
            System.out.println();
        }*/

//        Interview20 test = new Interview20();
//        System.out.println(test.isNumber("-1E-16"));

//        TreeNode a1 = new TreeNode(1);
//        TreeNode a2 = new TreeNode(0);
//        TreeNode a3 = new TreeNode(1);
//        TreeNode a4 = new TreeNode(-4);
//        TreeNode a5 = new TreeNode(-3);
//        TreeNode a6 = new TreeNode(6);
//        TreeNode a7 = new TreeNode(7);
//        TreeNode a8 = new TreeNode(8);
//        TreeNode a9 = new TreeNode(9);
//        a1.left = a2;a1.right = a3;
//        a2.left = a4;a2.right = a5;
//        a3.left = a6;a3.right = a7;
//        a4.left = a8;a4.right = a9;

//        TreeNode b1 = new TreeNode(1);
//        TreeNode b2 = new TreeNode(-4);
//        TreeNode b3 = new TreeNode(9);
//        b1.left = b2;
//
//        PointAtOffer26 T = new PointAtOffer26();
//        System.out.println(T.isSubStructure(a1, b1));
//
//        a4.left = a2;
//        a4.right = a5;
//
//        a2.left = a1;
//        a2.right = a3;
//        Interview36 test = new Interview36();
//        test.treeToDoublyList(a4);
//        int[][] a = new int[][] {
//                {3, 0, 8, 4},
//                {2, 4, 5, 7},
//                {9, 2, 6, 3},
//                {0, 3, 1, 0},
//        };
//        char[][] c = new char[][] {
//                {'O', 'X', 'X', 'O'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'O'},
//        };
//        traverseArr(a);
        char[][] a = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
//        LeetCode37 test = new LeetCode37();
//        test.solveSudoku(a);
//        LeetCode1406 test = new LeetCode1406();
//        test.stoneGameIII(new int[] {1,2,3,7});
//        printArr(a);
//        printArr(a);
//        LeetCode394 t = new LeetCode394();
//        System.out.println(t.decodeString("3[a2[c]]"));
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        Node node4 = new Node(4);
//
//        node1.neighbors.add(node2);node1.neighbors.add(node4);
//        node2.neighbors.add(node1);node2.neighbors.add(node3);
//        node3.neighbors.add(node2);node3.neighbors.add(node4);
//        node4.neighbors.add(node1);node4.neighbors.add(node3);
//
//        LeetCode133 ta = new LeetCode133();
//        ta.cloneGraph(node1);
//        int[] arr = new int[] { 1,2,3,4,5,6,7,8,9,10 };
//        List<Integer> l = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        l = l.stream().map(s -> s + 1).filter(val -> val <= 10).collect(Collectors.toList());
//        LeetCode40 t = new LeetCode40();
//        System.out.println(t.combinationSum2(new int[] {2,5,2,1,2}, 5));
//        char[][] board = new char[][] {
//                {'E', 'E', 'E', 'E', 'E'},
//                {'E', 'E', 'M', 'E', 'E'},
//                {'E', 'E', 'E', 'E', 'E'},
//                {'E', 'E', 'E', 'E', 'E'},
////                {'E', 'E', 'E', 'E', 'E'},
//        };
//        t.updateBoard(board, new int[] {3, 0});
        //[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
//        List<String> s1 = Arrays.asList("JFK","KUL");
//        List<String> s2 = Arrays.asList("JFK","NRT");
//        List<String> s3 = Arrays.asList("NRT","JFK");
//        List<String> s4 = Arrays.asList("LHR", "SFO");
//        List<String> s5 = Arrays.asList("ATL", "SFO");
//
//
//
//        List<List<String>> s = new LinkedList<>();
//        s.add(s1);s.add(s2);s.add(s3);
//        s.add(s4);
//        s.add(s5);
//        t.findItinerary(s);
        LeetCode724 ta = new LeetCode724();
//        int[][] edges = new int[][]{
//                {3, 1, 2},
//                {3, 2, 3},
//                {1, 1, 3},
//                {1, 2, 4},
//                {1, 1, 2},
//                {2, 3, 4}

//        };
        int[] arr = new int[] { 1, 7, 3, 6, 5, 6};
        System.out.println(ta.pivotIndex(arr));
    }

    static void printArr(int[][] a) {
        for (int[] arr : a) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    static void printArr(List<List<Integer>> a) {
        for (List<Integer> arr : a) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

      static void printArr(char[][] a) {
          for (char[] arr : a) {
              for (char val : arr) {
                  System.out.print(val + " ");
              }
              System.out.println();
          }
      }

    static void traverseArr(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        // 斜着遍历数组
        for (int l = 1; l < row; l++) {
            for (int i = 0; i <= row - l; i++) {
                int j = l + i - 1;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

    static public int waysToStep(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        final int mod = 1000000007;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3])  % mod;
        }
        return (int)(dp[n]);
    }
}
