package kekasmai.search;

import java.util.Scanner;

/**
 * luogu P1203
 */
public class BrokenNecklace {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String necklace = in.next();

        System.out.println(maxNecklaceBeads(necklace));
    }

    private static int maxNecklaceBeads(String necklace) {
        int result = 0;
        // break the necklace between i and i+1 beads
        for (int i = 0; i < necklace.length(); i++) {
            int curBreak = helper(necklace,i);
            result = Math.max(result, curBreak);
        }

        return result;
    }

    private static int helper(String necklace, int pos) {
        int len = necklace.length();
        int i = pos, j = (pos + 1) % len;
        int left = 0, right = 0;
        // special case ~ starting bead is 'w'
        while (necklace.charAt(i) == 'w' && left <= len) { left++; i = (i+len-1)%len; }
        while (necklace.charAt(j) == 'w' && right <= len) { right++; j = (j + 1)%len; }
        if (left + right >= len) return len;

        char a = necklace.charAt(i);
        char b = necklace.charAt(j);
        while (necklace.charAt(i) == a || necklace.charAt(i) == 'w') {
            i = (i + len - 1) % len;
            left++;
            if (left > len) break;
        }
        while (necklace.charAt(j) == b || necklace.charAt(j) == 'w') {
            j = (j + 1) % len;
            right++;
            if (right > len) break;
        }
        
        return Math.min(len, left+right);
    }
}
