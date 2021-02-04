package kekasmai.dp;
/**
 * luogu P1049
 * typical 0-1 knapsack problem
 */

import java.util.Scanner;

public class Load {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();
        int n = in.nextInt();
        int[] volumes = new int[n];
        for (int i = 0; i < n; i++)
            volumes[i] = in.nextInt();

        System.out.println(smallestRemainingVolume(V, volumes));
    }

    /**
     * dp[i][j] : the minimal remaining volume when using first i items, with V = j
     * so, in order to minimize it, we should make a choice —————— whether using ith item or not
     * that is, compare dp[i-1][j] and dp[i-1][j-volumes[i]], choose smaller one
     * 【of course, we can transfer this problem into picking some items to make their total volumes as large as possible】
     * @param V : the maximal available volume
     * @param volumes: the volume array of the objects
     * @return
     */
    private static int smallestRemainingVolume(int V, int[] volumes) {
        int n = volumes.length;
        int[][] dp = new int[n+1][V+1];
        for (int j = 0; j <= V; j++)
            dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= volumes[i-1])
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-volumes[i-1]]);
            }
        }

        return dp[n][V];
    }
}
