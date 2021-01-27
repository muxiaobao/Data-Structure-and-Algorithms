package kekasmai.divideconquer;

import java.util.*;

/**
 * @Date:2021-1-6
 * 参考：https://oi-wiki.org/geometry/nearest-points/
 *      https://blog.csdn.net/Jin_Kwok/article/details/82350019
 */
public class ClosestPointPiar {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        Point[] points = new Point[m];
        for (int pos = 0; pos < m; pos++) {
            points[pos] = new Point();
            points[pos].x = in.nextDouble();
            points[pos].y = in.nextDouble();
        }
        // 预处理，基于x坐标排序
        Arrays.sort(points, new Comparator<Point>(){
            @Override
            public int compare(Point p, Point q) {
                return (p.x > q.x) ? 1 : (p.x == q.x) ? 0 : -1;
            }
        });

        ClosestPointPiar solution = new ClosestPointPiar();
        System.out.printf("%.4f",solution.minDistance(0, m-1, points));
    }

    public double minDistance(int left, int right, Point[] points) {
        double curMinDis = 1e20;
        if (left == right) return curMinDis;
        if (left + 1 == right) return distance(points[left], points[right]);

        // 划分子问题
        int mid = left + (right - left) / 2;
        double leftMin = minDistance(left, mid, points);
        double rightMin = minDistance(mid+1, right, points);
        curMinDis = (leftMin < rightMin) ? leftMin : rightMin;

        // 合并，考虑跨中垂线的点对间的距离
        // x轴搜索范围[middle-curMinDis, middle+curMinDis], 记录搜索区间内的点的索引，便于进一步计算最小距离
        List<Integer> list = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - points[mid].x) <= curMinDis)
                list.add(i);
        }
        // 根据points的y坐标对list排序，这样在遍历时只需要遍历紧邻的几个点即可
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                return (points[i].y > points[j].y) ? 1 : (points[i].y == points[j].y) ? 0 : -1;
            }
        });

        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size() && points[j].y-points[i].y < curMinDis; j++) {
                double v = distance(points[i], points[j]);
                curMinDis = (v < curMinDis) ? v : curMinDis;
            }
        }

        return curMinDis;
    }

    // 计算两点间的距离
    private double distance(Point p, Point q) {
        return Math.sqrt((p.x - q.x)*(p.x-q.x) + (p.y-q.y)*(p.y-q.y));
    }

}

class Point {
    public double x;
    public double y;
}