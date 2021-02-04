package kekasmai.dp;

import java.util.Scanner;

/**
 * luogu P1040
 */
public class BonusBST {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++)
            scores[i] = in.nextInt();

        int[][] root_record = new int[n][n];
        int[][] dp = new int[n][n];
        System.out.println(maxScores(scores, 0, n-1,root_record, dp));
        printPreOrder(root_record,0,n-1);
    }

    private static int maxScores(int[] scores, int lo, int hi, int[][] root_record, int[][] dp) {
        // null tree;
        if (lo > hi) return 1;
        // leaf node;
        if (lo == hi) {
            root_record[lo][hi] = lo;
            return scores[lo];
        }

        // check memo
        if (dp[lo][hi] > 0) return dp[lo][hi];

        int value = 0;
        int root = -1;
        for (int pos = lo; pos <= hi; pos++) {
            int leftScore = maxScores(scores,lo, pos-1,root_record,dp);
            int rightScore = maxScores(scores,pos+1, hi, root_record,dp);
            int score = leftScore * rightScore + scores[pos];
            if (score > value) {
                value = score;
                root = pos;
            }
        }
        root_record[lo][hi] = root;
        return value;
    }

    private static void printPreOrder(int[][] root_record, int lo, int hi) {
        if (lo > hi) return;
        int root_index = root_record[lo][hi];
        System.out.print(root_index + 1 + " ");
        printPreOrder(root_record,lo, root_index-1);
        printPreOrder(root_record,root_index+1,hi);
    }
}
