package com.algorithm.array.sparsearray;

/**
 * @ClassName SparseArray
 * @Description 稀疏数组
 * @Author yanlu.myl
 * @Date 2021-03-28 10:26
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11*11
        // 0: 无子 1:黑子 2:蓝子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        int sum  = printChessArray(chessArray);

        /*将二维数组转为稀疏数组*/
        int sparseArrayRowCnt = sum+1;
        int sparseArrayLineCnt = 3;
        int sparseArr[][] = new int[sparseArrayRowCnt][sparseArrayLineCnt];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int seq = 0 ;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    seq ++;
                    sparseArr[seq][0] = i;
                    sparseArr[seq][1] = j;
                    sparseArr[seq][2] = chessArray[i][j];
                }
            }
        }

        /*得到的稀疏数组为:*/
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

        //将稀疏数组恢复为原始的二维数组
        int origin_row = sparseArr[0][0];
        int origin_line = sparseArr[0][1];
        int[][] chessOrign = new int[origin_row][origin_line];

        // 二维数组的length代表行数
        for (int i = 1; i < sparseArr.length; i++) {
            chessOrign[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        printChessArray(chessOrign);

    }

    private static int printChessArray(int[][] chessArray) {
        int sum = 0;
        for (int[] ints : chessArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
                if (anInt > 0) {
                    sum++;
                }
            }
            System.out.println();
        }
        return sum;
    }
}
