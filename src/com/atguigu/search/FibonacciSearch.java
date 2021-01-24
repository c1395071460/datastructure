package com.atguigu.search;

import java.util.Arrays;

/**
 * 斐波那切查找算法
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr,1234));
    }

    //因为后面我们mid=low+F(k-1) - 1,需要使用到斐波那切数列，因此我们需要先获取到一个斐波那切数列
    //使用非递归的方式
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那切查找算法

    /**
     * 使用非递归的方式来编写代码
     *
     * @param arr 查找的数组
     * @param key 我们需要查找的关键码（值）
     * @return
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //表示斐波那切分割数值的下标
        int mid = 0;//存放mid的值
        int f[] = fib();

        //获取到斐波那切分割数值的下标
        while (high > f[k] - 1)
            k++;
        //因为f[k]的值可能大于a的长度 因此我们需要使用Arrays类 构造一个新的数组 并指向arr[]
        //不足部分会用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环来处理  找到我们的数key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//说明继续向数组的前面查找（左边）
                high = mid - 1;
                //为什么是k--
                //1.全部元素 =  前面的元素 + 后面的元素
                //2.f[k] = f[k-1] + f[k-2]
                //3.因为前面f[k - 1] 个元素 所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //4.即在f[k-1]的前面继续查找 k--
                //5.即下次 mid = f[k-1-1] - 1
                k--;
            } else if (key > temp[mid]) {//说明继续向数组的后面查找（右边）
                low = mid + 1;
                //为什么是k-2
                //1.全部元素 =  前面的元素 + 后面的元素
                //2.f[k] = f[k-1] + f[k-2]
                //3.后面我们有f[k-2]  所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4.即在f[k-2]的前面可以继续查找 k-=2
                //5 即下次循环mid= f[k-1-2] - 1
                k-=2;
            } else {//找到
                if(mid <= high)
                    return mid;
                else
                    return high;
            }
        }

        return -1;
    }


}
