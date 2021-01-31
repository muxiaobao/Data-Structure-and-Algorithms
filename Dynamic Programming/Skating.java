package kekasmai.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * luogu P1434
 */
public class Skating {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(), C = in.nextInt();
        int[][] grid = new int[R][C];
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                grid[i][j] = in.nextInt();

        System.out.println(new Solution().longestPath(grid));
    }
}

class Solution {
    public int longestPath(int[][] grid) {
        Map<String, Integer> memo = new HashMap<>();
        int longest = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                longest = Math.max(longest, helper(grid, memo, i, j));
        return longest;
    }

    private int helper (int[][] grid, Map<String, Integer> memo, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return -1;

        if (memo.containsKey(i+":"+j)) return memo.get(i+":"+j);

        int up = (i > 0 && grid[i][j] > grid[i-1][j]) ? helper(grid, memo, i-1, j) : 0;
        int down = (i < grid.length-1 && grid[i][j] > grid[i+1][j]) ? helper(grid, memo, i+1, j) : 0;
        int left = (j > 0 && grid[i][j] > grid[i][j-1]) ? helper(grid, memo, i, j-1) : 0;
        int right = (j < grid[0].length-1 && grid[i][j] > grid[i][j+1]) ? helper(grid, memo, i, j+1) : 0;

        int bestChoice = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        memo.put(i+""+j, bestChoice);
        return bestChoice;
    }
}
