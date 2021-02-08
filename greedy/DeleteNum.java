package kekasmai.greedy;

import java.util.Scanner;

/**
 * luogu P1106
 */
public class DeleteNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        int k = in.nextInt();

        System.out.println(minRemainingNum(n, k));
    }

    private static String minRemainingNum(String n, int k) {
        for (int i = 0; i < k; i++) {
            n = deleteOneNum(n);
        }
        // trim leading zeros
        int startIndex = 0;
        while (startIndex < n.length() && n.charAt(startIndex) == '0') startIndex++;
        return startIndex == n.length() ? "0" : n.substring(startIndex);
    }

    private static String deleteOneNum(String num) {
        int deleteIndex = -1;
        // find the first peak element and delete it to make the remaining smallest
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) >= num.charAt(i-1)) continue;
            else {
                deleteIndex = i-1;
                break;
            }
        }
        if (deleteIndex < 0) deleteIndex = num.length()-1;
        return num.substring(0,deleteIndex) + num.substring(deleteIndex+1);
    }
}

