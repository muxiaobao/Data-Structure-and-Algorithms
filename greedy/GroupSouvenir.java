package kekasmai.greedy;
/**
 * luogu P1094
 */

import java.util.Arrays;
import java.util.Scanner;

public class GroupSouvenir {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();
        int n = in.nextInt();
        int[] souvenirs = new int[n];
        for (int i = 0; i < n; i++)
            souvenirs[i] = in.nextInt();

        System.out.println(minGroups(souvenirs,V));
    }

    private static int minGroups (int[] souvenirs, int volume) {
        int group = 0;
        Arrays.sort(souvenirs);
        System.out.println(Arrays.toString(souvenirs));
        int i = 0, j = souvenirs.length-1;
        while (i <= j) {
            group++;
            if (souvenirs[i] + souvenirs[j] <= volume) {
                i++; j--;
            }
            else
                j--;
        }

        return group;
    }
}
