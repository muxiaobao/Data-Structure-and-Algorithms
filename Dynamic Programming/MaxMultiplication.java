package kekasmai.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * luogu P1018
 */
public class MaxMultiplication {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        String str = in.next();

        System.out.println(maxMultiplication(str,K));
    }

    private static String maxMultiplication(String str, int k) {
        if (k == 0) return str;
        String result = "0";
        for (int i = 0; i < str.length()-1; i++) {
            String a = str.substring(0,i+1);
            String b = maxMultiplication(str.substring(i+1),k-1);
            String mul = multiply(a,b);
            if (compare(mul,result) > 0)
                result = mul;
        }
        return result;
    }

    // high precision multiplication between big integers
    private static String multiply(String a, String b) {
        int m = a.length(), n = b.length();
        int[] res = new int[m+n];
        Arrays.fill(res, 0);
        for (int i = m-1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int temp = (a.charAt(i) - '0') * (b.charAt(j) - '0') + res[i+j+1];
                res[i+j+1] = temp % 10;
                res[i+j] += temp / 10;
            }
        }
        String str = "";
        int i = 0;
        while (i < res.length && res[i] == 0) i++;
        while (i < res.length) str += res[i++];
        return str;
    }

    private static int compare(String a, String b) {
        if (a.length() > b.length()) return 1;
        if (a.length() < b.length()) return -1;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) continue;
            else if (a.charAt(i) > b.charAt(i)) return 1;
            else return -1;
        }
        return 0;
    }
}