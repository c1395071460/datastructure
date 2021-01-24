package com.atguigu.search;

public class InsertValueSearch {

    public static void main(String[] args) {
        //1~100的数组
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

//        System.out.println(Arrays.toString(arr));

        int result = insertValueSearch(arr, 0, arr.length - 1, 100);

        System.out.println(result);

    }

    //编写差值查找算法

    /**
     * 差值查找算法 也要求数组是有序的*****
     *
     * @param arr     查找算法
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 查找值
     * @return 如果找到  返回对应的下标  如果未找到  返回 -1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 这两个判断必须有
        //否则得到的mid  可能会数组越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1])
            return -1;
        //*****和二分查找的区别   这里使用了公式*****
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//向右查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}