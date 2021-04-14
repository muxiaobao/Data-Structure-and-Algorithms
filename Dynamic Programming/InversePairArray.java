package kekasmai.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * luogu 2513
 */
public class InversePairArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        System.out.println(InversePairArray.helper(n,k));
    }

    /**
     *
     * @param n represent an permutation consisting of 1,2,....,n
     * @param k the number of inverse pairs
     * @return the number of arrays whose inverse pairs are equal to k
     *
     * dynamic programming: d[i][j] is the return value with input n=i, k=j
     * we can observe that for any permutation bounded by i-1, inserting i into this permutation
     * will contribute some inverse pairs because i is larger than any one in the permutation.
     * so we can conclude the state transform equation:
     * dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + dp[i-1][j-2] + ... + dp[i-1][j-i+1]
     *
     * explanation:
     * dp[i-1[j] means that inserting i does not contribute inverse pairs (i.e. inserting 4 into
     * the end of [1,2,3], becomes [1,2,3,4)
     * dp[i-1][j-p] means inserting i contribute to p inverse pairs, and this operation can
     * contribute at most i-1 inverse pairs when inserting to the beginning of original array,
     * therefore, 0 <= p <= i-1
     */
    private static int helper(int n, int k) {
        int[][] dp = new int[n+1][k+1];
        for (int i = 0; i <= k; i++)
            dp[0][i] = 0;
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;
        // O(n*k*k) time complexity
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= k; j++) {
//                for (int p = 0; p < i && j -p >= 0; p++)
//                    dp[i][j] += dp[i-1][j-p];
//            }
//        }

        // using prefix-sum to optimize the solution ~ O(n*k) time complexity
        // sum = ∑ dp[i-1][k], k = max{0,j-i+1}
        // i.e. <i = 2>
        // dp[2][0] = 1,                    (sum = dp[1][0])
        // dp[2][1] = dp[1][1] + dp[1][0],  (sum = dp[1][0] + dp[1][1]), here the left boundary is 0
        // dp[2][2] = dp[1][2] + dp[1][1],  (sum = dp[1][2] + dp[1][1] + dp[1][0] - dp[1][0])
        // ......
        // Therefore, sum is the prefix-sum, and records at most first i items' sum
        for (int i = 1; i <= n; i++) {
            int sum = 1; // because dp[i-1][0] = 1
            for (int j = 1; j <= k; j++) {
                sum += dp[i-1][j];
                if (j-i+1 > 0)
                    sum -= dp[i-1][j-i]; // left boundary forward by one, so minus its leftmost value

                dp[i][j] = sum;
            }
        }

        return dp[n][k];
    }
}
