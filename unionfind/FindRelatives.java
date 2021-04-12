package kekasmai.unionfind;

import java.util.Arrays;
import java.util.Scanner;

/**
 * luogu P1551
 */
public class FindRelatives {
    public static void main(String[] args) {
        // cope with output
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int[][] relations = new int[m][2];
        for (int i = 0; i < m; i++) {
            relations[i][0] = in.nextInt();
            relations[i][1] = in.nextInt();
        }
        int[][] test = new int[p][2];
        for (int i = 0; i < p; i++) {
            test[i][0] = in.nextInt();
            test[i][1] = in.nextInt();
        }

        FindRelatives obj = new FindRelatives();

        // build disjoint set
        int[] set = obj.buildDisjointSet(relations,n);

        // judge
        for (int i = 0; i < p; i++) {
            boolean isRelative = obj.find(set,test[i][0]) == obj.find(set,test[i][1]);
            String result = isRelative ? "Yes" : "No";
            System.out.println(result);
        }

    }

    public int[] buildDisjointSet(int[][] relations, int n) {
        int[] set = new int[n+1];
        int[] ranks = new int[n+1];
        Arrays.fill(ranks, 0);
        for (int i = 0; i <= n; i++) {
            set[i] = i; // initially, node's parent is itself
        }

        for (int i = 0; i < relations.length; i++) {
            int x = relations[i][0];
            int y = relations[i][1];
            union(set,x,y,ranks);
        }

        return set;
    }

    private int find(int[] set, int x) {
        if (set[x] != x)
            set[x] = find(set, set[x]);
        return set[x];
    }

    private void union(int[] set, int x, int y, int[] ranks) {
        int px = find(set, x);
        int py = find(set, y);

        if (px == py) return; // same set

        if (ranks[px] > ranks[py]) {
            set[py] = px;
        } else {
            set[px] = py;
            if (ranks[px] == ranks[py]) {
                ranks[py]++;
            }
        }
    }
}
