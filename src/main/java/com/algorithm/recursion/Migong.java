package com.algorithm.recursion;

/**
 * @Author: AngelMa
 * @Description: 走迷宫
 * @Date: Created on 2021/4/10 11:16 上午
 * @Modified By:
 */
public class Migong {
    public static void main(String[] args) {
        /*一共是 8 行 7 列*/
        int[][] map = new int[8][7];
        setWall(map);
        showMigong(map);
        setWay2(map, 1, 1);
        showMigong(map);
    }

    /**
     * 走迷宫，最终的目的地坐标是 [6][5] 走之前需要确认走迷宫的策略： 下-右-上-左
     * <pre>
     * 0: 代表尚未走过
     * 1. 代表有墙
     * 2. 代表可走
     * 3. 代表当前的路是被堵死的
     * </pre>
     *
     * @param map 迷宫
     * @param i   出发横坐标
     * @param j   出发纵坐标
     */
    private static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        }

        if (map[i][j] == 0) {
            //往下走
            map[i][j] = 2;//初始认为该点是通的
            if (setWay(map, i + 1, j)) {
                return true;
            }
            //往右走
            if (setWay(map, i, j + 1)) {
                return true;
            }
            //往上走
            if (setWay(map, i - 1, j)) {
                return true;
            }
            //往左走
            if (setWay(map, i, j - 1)) {
                return true;
            }

            //若四个方向都走不通，说明当前的路被堵死的
            map[i][j] = 3;
            return false;
        }
        //其余情况：1：墙， 3：被堵死
        return false;
    }

    private static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        }

        if (map[i][j] == 0) {
            map[i][j] = 2;//初始认为该点是通的
            //往上走
            if (setWay2(map, i - 1, j)) {
                return true;
            }
            //往右走
            if (setWay2(map, i, j + 1)) {
                return true;
            }
            //往下走
            if (setWay2(map, i + 1, j)) {
                return true;
            }
            //往左走
            if (setWay2(map, i, j - 1)) {
                return true;
            }

            //若四个方向都走不通，说明当前的路被堵死的
            map[i][j] = 3;
            return false;
        }
        //其余情况：1：墙， 3：被堵死
        return false;
    }

    private static void showMigong(int[][] map) {
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.printf("%d ", anInt);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void setWall(int[][] map) {
        /*将收尾行设置为墙*/
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1; //第 1 行是墙
            map[7][i] = 1; //第 8 行是墙
        }
        /*将收尾列设置为墙*/
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        /*将固定点位设置为墙*/
        map[3][1] = 1;
        map[3][2] = 1;
    }

}
