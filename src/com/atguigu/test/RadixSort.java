package com.atguigu.test;


import java.util.Arrays;

public class RadixSort {

    //测试
    public static void main(String[] args) {
        int[] arr = {1,22,4,7,33,23};
        int[] result = {};
        try {
            result = sort(arr);
            System.out.println(Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        int maxDigit = getMaxDigit(arr);
        return radixSort(arr,maxDigit);
    }

    /**
     * 获得最高位数
     */
    public static int getMaxDigit(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return (max + "").length();
    }


    /**
     * 桶排序
     */
    public static int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10; // 考虑负数的情况
        int dev = 1;
        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            //考虑负数的情况，这里扩展一倍队列数，其中[0-9]对应负数，[10-19]对应正数
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppand(counter[bucket], arr[j]);
            }
            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     */
    public static int[] arrayAppand(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }


}
