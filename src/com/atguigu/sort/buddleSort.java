package com.atguigu.sort;

import java.util.Arrays;

public class buddleSort {

    public static void main(String[] args) {
        int[] arr = {-1, -2, 3, 6, 8};
        buddleSortMethod(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));
    }

    public static void buddleSortMethod(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//          优化
            if (!flag) {
                break;
            } else {
                flag = true;
            }
        }
    }
}
