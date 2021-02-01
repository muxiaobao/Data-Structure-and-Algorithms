package kekasmai.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StampDesign {
    private static List<Integer> result = null;
    private static int maxRange = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // 邮票数
        int k = in.nextInt(); // 邮票种类数

        List<Integer> stamps = new ArrayList<>();
        stamps.add(1);
        int[][] y = new int[k+1][3000];
        for (int i = 0; i < y[1].length; i++)
            y[1][i] = i;
        dfs(stamps, n, k, 1, n, y);

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
        System.out.println("\nMAX=" + maxRange);
    }

    /**
     *
     * @param stamps: the temporary result during the dfs traversal
     * @param N: the total number of stamps that can be used
     * @param K: the total kinds of different stamps
     * @param cur: the stamp value chosen last time
     * @param curRange: the maximum range that can be represent so far
     * @param y: 2D array, y[i][j] is the minimum amount of stamps when using first i
     *         kinds of stamps to compose j value
     */
    private static void dfs(List<Integer> stamps, int N, int K, int cur, int curRange, int[][] y) {
        if (stamps.size() == K) {
            if (maxRange < curRange) {
                maxRange = curRange;
                result = new ArrayList<>(stamps);
            }
            return;
        }

        for (int value = cur+1; value <= curRange+1; value++){
            stamps.add(value);
            int newRange = calculate(N, stamps.size(), y, value);
            dfs(stamps,N,K,value,newRange,y);
            stamps.remove(stamps.size()-1);
        }

    }

    // calculate the upper bound when using first p kinds of stamps
    private static int calculate(int N, int p, int[][] y, int value) {
        for (int j = 1; j < y[p].length; j++) {
            y[p][j] = y[p-1][j];
            for (int t = 1; t <= N && j-t*value >= 0; t++) {
                y[p][j] = Math.min(y[p][j], t + y[p - 1][j - t * value]);
            }
        }

        int maxRange = 0;
        for (int j = 1; j < y[p].length && y[p][j] <= N; j++) {
            maxRange = j;
            if (j+1 < y[p].length && y[p][j+1] > N) break;
        }
        System.out.println("X" + p + "= " + value + ", r" + p + "= " + maxRange);
        return maxRange;
    }
}