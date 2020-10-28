package com.atguigu.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {7, 202, 9, 5};
        for (int i = 1; i < arr.length - 1; i++) {
            //定义待插入的数
            int insertArr = arr[i];
            int insertIndex = i - 1;
            //给insertVal 找到插入的位置
            //说明
            //1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            //2.insertVal < arr[insertIndex] 待插入的数 还没有找到插入位置
            //3.就需要将arr[insertVal] 后移
            while (insertArr - 1 > 0 && insertArr < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;

            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1ss
            arr[insertIndex + 1] = insertArr;
        }
        System.out.println(Arrays.toString(arr));
    }
}
