package com.atguigu.sort;

import java.util.Arrays;

/**
 * 选择排序
 */

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {-1, -2, 12, 9, 8};
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] < arr[i]) {
//                    int temp = arr[j];
//                    arr[j] = arr[i];
//                    arr[i] = temp;
//                }
//            }
//        }
        selectSortMethod(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSortMethod(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            //遍历
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明min不是真的最小值
                    min = arr[j];
                    minIndex = j;
                }
            }
            //判断是否需要交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

        }
    }
}
