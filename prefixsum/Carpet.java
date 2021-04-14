package kekasmai.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * luogu 3397
 * solution: https://www.luogu.com.cn/blog/blue/solution-p3397
 */
public class Carpet {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // Scanner in = new Scanner(System.in);
        String[] input = in.readLine().split("\\s+");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[][] flag = new int[n+1][n+1];
        // O(m*n) time complexity
        for (int i = 0; i < m; i++) {
            String[] indices = in.readLine().split("\\s+");
            int x1 = Integer.parseInt(indices[0]) -1;
            int y1 = Integer.parseInt(indices[1]) -1;
            int x2 = Integer.parseInt(indices[2]) -1;
            int y2 = Integer.parseInt(indices[3]) -1;

            // add flags ~ O(N) complexity
            for (int j = x1; j <= x2; j++) {
                flag[j][y1]++;
                flag[j][y2+1]--;
            }
        }

        Carpet.helper(flag, n);
    }

    private static void helper(int[][] flag, int n) {
        int sum = 0;
        // O(n*n) complexity
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += flag[i][j];
                System.out.print(sum + " ");
            }
            sum += flag[i][n];
            System.out.println();
        }

    }
}