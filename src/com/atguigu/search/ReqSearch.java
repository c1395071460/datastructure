package com.atguigu.search;

public class ReqSearch {

    public static void main(String[] args) {
        int[] arr = {32,-1,56,98,222};
        int result = ReqSearch(arr,22);
        if (result == -1){
            System.out.println("没有找到对应的数字");
        } else {
            System.out.println("找到对应的下标=" + result);
        }
    }

    /**
     * 顺序查找
     * @param arr
     * @param value
     * @return
     */
    public static int ReqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
