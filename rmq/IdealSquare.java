package kekasmai.rmq;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Date 2021-1-27
 * luogu-P2216
 */
public class IdealSquare {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();

        int[][] matrix = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++)
                matrix[i][j] = in.nextInt();
        }

        // first, find the n-interval max and min values of each row of the matrix
        int[][] maxRows = new int[a][];
        int[][] minRows = new int[a][];
        for (int i = 0; i < a; i++) {
            maxRows[i] = findMax(matrix[i], n);
            minRows[i] = findMin(matrix[i], n);
        }

        // based on the maxRows and minRows, find the n-interval max and min values of
        // each column of maxRows and minRows
        int[][] max = new int[b-n+1][];
        int[][] min = new int[b-n+1][];

        int[] temp_max = new int[a];
        int[] temp_min = new int[a];
        for (int i = 0; i < b-n+1; i++) {
            for (int j = 0; j < a; j++) {
                temp_max[j] = maxRows[j][i];
                temp_min[j] = minRows[j][i];
            }
            max[i] = findMax(temp_max,n);
            min[i] = findMin(temp_min,n);
        }

        // traverse max and min matrix, find the minimum difference between the two
        int difference = Integer.MAX_VALUE;
        for (int i = 0; i < max.length; i++) {
            for (int j = 0; j < max[0].length; j++) {
                difference = Math.min(difference, max[i][j]-min[i][j]);
            }
        }

        System.out.println(difference);
    }

    private static int[] findMax(int[] nums, int m) {
        int[] res = new int[nums.length-m+1];
        int k = 0;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && i - deque.getFirst() >= m)
                deque.pollFirst();
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()])
                deque.pollLast();
            deque.offerLast(i);
            if (i >= m-1)
                res[k++] = nums[deque.getFirst()];
        }
        return res;
    }

    private static int[] findMin(int[] nums, int m) {
        int[] res = new int[nums.length-m+1];
        int k = 0;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && i - deque.getFirst() >= m)
                deque.pollFirst();
            while (!deque.isEmpty() && nums[i] < nums[deque.getLast()])
                deque.pollLast();
            deque.offerLast(i);
            if (i >= m-1)
                res[k++] = nums[deque.getFirst()];
        }
        return res;
    }
}
