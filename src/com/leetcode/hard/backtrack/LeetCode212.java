package com.leetcode.hard.backtrack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode212 {
    // 官方解法使用了前缀树加回溯
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;
        public TrieNode() {}
    }
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }


    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public List<String> findWords_(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                chars.add(board[i][j]);
            }
        }

        Set<String> res = new HashSet<>();
        for (String word : words) {
            Set<Character> wordSet = new HashSet<>();
            for (char ch : word.toCharArray()) {
                wordSet.add(ch);
            }

            if (!chars.containsAll(wordSet)) {
                continue;
            }
            process(board, m, n, res, word);

        }
        return new ArrayList<>(res);
    }

    private void process(char[][] board, int m, int n, Set<String> res, String word) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res.contains(word)) {
                    break;
                }

                if (board[i][j] != word.charAt(0)) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                boolean[][] visit = new boolean[m][n];
                visit[i][j] = true;
                if (search(board, word, i, j, sb.append(board[i][j]), visit)) {
                    res.add(word);
                }
            }
        }
    }


    boolean search(char[][] board, String word, int x, int y, StringBuilder path, boolean[][] visit) {
        if (path.length() == word.length()) {
            return word.equals(path.toString());
        }
        List<int[]> nextChar = getNextChar(board, x, y);
        for (int[] pos : nextChar) {
            int nextX = pos[0];
            int nextY = pos[1];
            if (visit[nextX][nextY] || board[nextX][nextY] != word.charAt(path.length())) {
                continue;
            }

            path.append(board[nextX][nextY]);
            visit[nextX][nextY] = true;
            if (search(board, word, nextX, nextY, path, visit)) {
                return true;
            }
            visit[nextX][nextY] = false;
            path.deleteCharAt(path.length() - 1);
        }
        return false;
    }

    List<int[]> getNextChar(char[][] board, int x, int y) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (isOutBoard(board.length, board[0].length, nextX, nextY)) {
                continue;
            }
            res.add(new int[] { nextX, nextY});
        }

        return res;
    }

    boolean isOutBoard(int m, int n, int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}
