package com.atguigu.sort;

public class RadixSort {

    //基数排序方法
    public static void radixSort(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();


        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一位数组(桶)，大小定为arr.length
        //3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录每个桶的每次放入的数据个数
        //可以这样理解
        //比如：bucketElementCounts[0],记录的就是 bucket[0]桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循环将代码处理
        for (int i = 0, n=1;i< maxLength;i++){

        }

    }



}
