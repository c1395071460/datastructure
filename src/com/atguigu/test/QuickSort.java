package com.atguigu.test;

import java.util.Arrays;

/**
 * https://www.runoob.com/w3cnote/quick-sort-2.html
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70,78};
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] quickSort(int[] arr,int left,int right){
        if (left < right){
            int partition = partition(arr, left, right);
            quickSort(arr,left,partition - 1);
            quickSort(arr,partition + 1,right);
        }
        return arr;
    }


    public static int partition(int[] arr,int left,int right){
        //设置基准值
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]){
                swap(arr,i,index);
                index++;
            }
        }
        swap(arr,pivot,index - 1);
        return index - 1;
    }



    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
