package com.ceco.algorithms.problems.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 25-Sep-2016
 */
public class CubeSummation {

    private static class Point3D {
        long x;
        long y;
        long z;
        long value;

        Point3D(long x, long y, long z, long value) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        List<Long> sums = new ArrayList<>();

        for (long i = 0; i < testCases; i++) {
            int cubeDim = scanner.nextInt() + 1;
            int queryCount = scanner.nextInt();
            scanner.nextLine();

//            long[][][] cube = new long[cubeDim][cubeDim][cubeDim];
            List<Point3D> polongs = new ArrayList<>();

            for (long j = 0; j < queryCount; j++) {
                String queryType = scanner.next();
                switch (queryType) {
                    case "UPDATE":
                        long x = scanner.nextLong();
                        long y = scanner.nextLong();
                        long z = scanner.nextLong();
                        long value = scanner.nextLong();
//                        cube[x][y][z] = value;
                        polongs.add(new Point3D(x, y, z, value));

                        break;
                    case "QUERY":
                        long x1 = scanner.nextLong();
                        long y1 = scanner.nextLong();
                        long z1 = scanner.nextLong();
                        long x2 = scanner.nextLong();
                        long y2 = scanner.nextLong();
                        long z2 = scanner.nextLong();
                        long sum = 0;

                        for (Point3D p : polongs) {
                            boolean isInRangeX = p.x >= x1 && p.x <= x2;
                            boolean isInRangeY = p.y >= y1 && p.y <= y2;
                            boolean isInRangeZ = p.z >= z1 && p.z <= z2;
                            if (isInRangeX && isInRangeY && isInRangeZ) {
                                sum += p.value;
                            }
                        }
//                        for (long k = x1 ; k <= x2 ; k++) {
//                            for (long l = y1; l <= y2; l++) {
//                                for (long m = z1; m <= z2 ; m++) {
//                                    sum += cube[k][l][m];
//                                }
//                            }
//                        }
                        sums.add(sum);
                        break;
                }
            }
        }

        sums.forEach(System.out::println);
    }
}
