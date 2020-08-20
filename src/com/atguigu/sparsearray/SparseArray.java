package com.atguigu.sparsearray;

import java.io.*;

/**
 * 稀疏数组和二维数组的转换
 * <p>
 * 二维数组 转 稀疏数组的思路
 * 1.遍历 原始的二维数组， 得到有效数据的个数sum
 * 2.根据sum就可以创建稀疏数组 sparseArr int[sum+1][3]
 * 3.将二维数组的有效数据存入到稀疏数组
 * <p>
 * 稀疏数组 转原始的二维数组的思路
 * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如下面的chessArr2 = int[11][11]
 * 2.在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可。
 */

public class SparseArray {

    public static void main(String[] args) throws Exception {
        //创建原始的二维数组 11*11
        //0表示没有棋子，1表示 黑子 2 表示蓝子
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;


        for (int[] is : chessArr) {
            for (int i : is) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组
        //得到有效数据的个数 sum
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum值为：" + sum);

        int sparseArr[][] = new int[sum + 1][3];
        //得到稀疏数组的第一列数据
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //得到稀疏数组的其他列数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArr[count + 1][0] = i;
                    sparseArr[count + 1][1] = j;
                    sparseArr[count + 1][2] = chessArr[i][j];
                    count++;
                }
            }
        }
        //打印得出的稀疏数组
        System.out.println("二维数组转为稀疏数组为:");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        write(sparseArr);
        int[][] read = read();

        //将稀疏数组 --> 二维数组


        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //先把数组中的数据全设置为0
        for (int i = 0; i < sparseArr[0][0]; i++) {
            for (int j = 0; j < sparseArr[0][1]; j++) {
                chessArr[i][j] = 0;
            }
        }
        //再将不为0的位置赋值
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //打印转化后的二维数组
        System.out.println("稀疏数组转为二维数组后为:");
        for (int i = 0; i < sparseArr[0][0]; i++) {
            for (int j = 0; j < sparseArr[0][1]; j++) {
                System.out.printf("%d\t", chessArr2[i][j]);
            }
            System.out.println();
        }


    }

    public static void write(int[][] arr) throws Exception {
        //1创建字节缓冲流
        OutputStream os = new FileOutputStream("D:\\idea\\projects\\DataStructure\\map.txt");
        PrintStream ps = new PrintStream(os);
        //2写入
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 3; j++) {
                ps.print(arr[i][j] + ",");
            }
            ps.println();
        }
        ps.close();
        os.close();


    }

    //从文件中读取稀疏数组
    public static int[][] read() throws Exception {

        String[] arr = new String[1024];
        BufferedReader bd = new BufferedReader(new FileReader("D:\\idea\\projects\\DataStructure\\map.txt"));
        String line = null;
        int number = 0;
        while ((line = bd.readLine()) != null) {
            arr[number] = line;
            number++;
        }


        System.out.println("从文件中读取的数组。。。");
        // 输出从文件读取的数组
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i]);
            }
        }

        //将数组进行转化
        String[] arr1 = arr[0].split(",");
        int a[] = new int[3];
        for (int i = 0; i < arr1.length; i++) {
            a[i] = Integer.parseInt(arr1[i]);
        }
        int chessArr4 [][] = new int[a[0]][a[1]];
        chessArr4[0][0] = a[0];
        chessArr4[0][1] = a[1];
        chessArr4[0][2] = a[2];

        for (int i = 1; i <= a[2]; i++) {
            String b[] = arr[i].split(",");
            int c[] = new int[b.length];
            for (int j = 0; j < b.length; j++) {
                c[j] = Integer.parseInt(b[j]);
                chessArr4[i][j] = c[j];
            }
        }

        System.out.println("从文件中读取到的稀疏数组");
        for (int i = 0; i <= a[2]; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d\t",chessArr4[i][j]);
            }
            System.out.println();
        }



        return chessArr4;
    }

}


