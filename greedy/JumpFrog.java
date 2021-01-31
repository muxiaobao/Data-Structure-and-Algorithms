package kekasmai.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * luogu P4995
 */
public class JumpFrog {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++)
            heights[i] = in.nextInt();

        System.out.println(new Solution().maxJumpingSum(heights));
    }
}

class Solution {
    public int maxJumpingSum (int[] heights) {
        Arrays.sort(heights);
        int lo = 0, hi = heights.length-1;
        // jump to the stone with maximal height
        int maxSum = heights[hi] * heights[hi];
        while (lo < hi) {
            // jump from the stone with maximal height to the stone with minimal height
            // among the remaining stones
            maxSum += (heights[hi] - heights[lo]) * (heights[hi] - heights[lo]);
            hi--;
            // jump from the stone with minimal height to .....
            maxSum += (heights[hi] - heights[lo]) * (heights[hi] - heights[lo]);
            lo++;
        }
        return maxSum;
    }
}
/**
 * 1    3   7   10  ----> 1 + 4 + 16 + 9 = 30
 * 10   1   7   3   ----> 100 + 81 + 36 + 16
 * 10   7   1   3   ----> 100 + 9 + 36 + 4
 */
