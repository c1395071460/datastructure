package com.atguigu.test;

public class BinarySearch {


    public static void main(String[] args) {
        int[] arr = {1,2,7,12,56,231,982};
        int result = binarySearch(arr, 0, arr.length - 1, 982);
        System.out.println(result);
    }

    /**
     * 二分查找
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal < arr[mid]) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            return binarySearch(arr, mid + 1, arr.length - 1, findVal);
        } else {
            return mid;
        }
    }
}
