package kekasmai.prefixsum;

import java.util.Scanner;

/**
 * luogu 1115
 */
public class MaxSubArraySum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        System.out.println(MaxSubArraySum.helper(nums));
    }

    private static int helper(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        int max = Integer.MIN_VALUE;
        // dp[i] is the maximum sum of subarray nums[0,...,i]
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }
}
