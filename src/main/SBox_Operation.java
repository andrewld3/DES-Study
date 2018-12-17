package main;

import java.util.BitSet;

public class SBox_Operation {

    private final int[][] BOX1 =
            {
                    {14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7},
                    {0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8},
                    {4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0},
                    {15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13}
            };

    private final int[][] BOX2 =
            {
                    {15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10},
                    {3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5},
                    {0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15},
                    {13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9}
            };

    private final int[][] BOX3 =
            {
                    {10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8},
                    {13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1},
                    {13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7},
                    {1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12}
            };

    private final int[][] BOX4 =
            {
                    {7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15},
                    {13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9},
                    {10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4},
                    {3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14}
            };

    private final int[][] BOX5 =
            {
                    {2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9},
                    {14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6},
                    {4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14},
                    {11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3}
            };

    private final int[][] BOX6 =
            {
                    {12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11},
                    {10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8},
                    {9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6},
                    {4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13}
            };

    private final int[][] BOX7 =
            {
                    {4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1},
                    {13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6},
                    {1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2},
                    {6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12}
            };

    private final int[][] BOX8 =
            {
                    {13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7},
                    {1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2},
                    {7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8},
                    {2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11}
            };

    public BitSet retrieveOutput(BitSet input, int boxNum) {
        BitSet output = null;
        int[] location;
        int row, col;
        int outputInt = 0;

        location = findLocation(input);

        row = location[0];
        col = location[1];

        switch(boxNum) {
            case 1:
                outputInt = BOX1[row][col];
                break;
            case 2:
                outputInt = BOX2[row][col];
                break;
            case 3:
                outputInt = BOX3[row][col];
                break;
            case 4:
                outputInt = BOX4[row][col];
                break;
            case 5:
                outputInt = BOX5[row][col];
                break;
            case 6:
                outputInt = BOX6[row][col];
                break;
            case 7:
                outputInt = BOX7[row][col];
                break;
            case 8:
                outputInt = BOX8[row][col];
                break;
        }

        output = convertBinary(outputInt);
        return output;
    }

    private int[] findLocation(BitSet in) {
        int p1, p2, p3, p4, p5, p6;
        int row = 0, col= 0;
        int[] locationInfo = new int[2];

        p1 = in.get(0) ? 1 : 0;
        p2 = in.get(1) ? 1 : 0;
        p3 = in.get(2) ? 1 : 0;
        p4 = in.get(3) ? 1 : 0;
        p5 = in.get(4) ? 1 : 0;
        p6 = in.get(5) ? 1 : 0;

        if(p1 == 1 && p6 == 1) {
            row = 3;
        } else if(p1 == 1 && p6 == 0) {
            row = 2;
        } else if(p1 == 0 && p6 == 1) {
            row = 1;
        } else if(p1 == 0 && p6 == 0) {
            row = 0;
        }

        if(p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1) {
            col = 15;
        } else if(p2 == 1 && p3 == 1 && p4 == 1 && p5 == 0) {
            col = 14;
        } else if(p2 == 1 && p3 == 1 && p4 == 0 && p5 == 1) {
            col = 13;
        } else if(p2 == 1 && p3 == 1 && p4 == 0 && p5 == 0) {
            col = 12;
        } else if(p2 == 1 && p3 == 0 && p4 == 1 && p5 == 1) {
            col = 11;
        } else if(p2 == 1 && p3 == 0 && p4 == 1 && p5 == 0) {
            col = 10;
        } else if(p2 == 1 && p3 == 0 && p4 == 0 && p5 == 1) {
            col = 9;
        } else if(p2 == 1 && p3 == 0 && p4 == 0 && p5 == 0) {
            col = 8;
        } else if(p2 == 0 && p3 == 1 && p4 == 1 && p5 == 1) {
            col = 7;
        } else if(p2 == 0 && p3 == 1 && p4 == 1 && p5 == 0) {
            col = 6;
        } else if(p2 == 0 && p3 == 1 && p4 == 0 && p5 == 1) {
            col = 5;
        } else if(p2 == 0 && p3 == 1 && p4 == 0 && p5 == 0) {
            col = 4;
        } else if(p2 == 0 && p3 == 0 && p4 == 1 && p5 == 1) {
            col = 3;
        } else if(p2 == 0 && p3 == 0 && p4 == 1 && p5 == 0) {
            col = 2;
        } else if(p2 == 0 && p3 == 0 && p4 == 0 && p5 == 1) {
            col = 1;
        } else if(p2 == 0 && p3 == 0 && p4 == 0 && p5 == 0) {
            col = 0;
        }

        locationInfo[0] = row;
        locationInfo[1] = col;
        return locationInfo;
    }

    private BitSet convertBinary(int num) {
        BitSet output = new BitSet(4);
        String binary = String.format("%04d", Integer.valueOf(Integer.toBinaryString(num)));
        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                output.set(i);
            }
        }
        return output;
    }

}

