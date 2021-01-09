package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
        System.out.println("排序前");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间是=" + dateStr);


        //ShellSort(arr);//交换式
        shellSort2(arr);//移位法

        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = format2.format(date2);
        System.out.println("排序前的时间是=" + dateStr2);
    }

    //使用逐步推到的方式来编写希尔排序
    public static void ShellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            int temp = 0;
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(arr));
    }

    //        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
    //对交换式的希尔排序进行优化 -> 移位法
    public static void shellSort2(int[] arr) {
        //增量gap，并逐步减少增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素 逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
