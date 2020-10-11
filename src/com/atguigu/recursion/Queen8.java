package com.atguigu.recursion;

/**
 * 八皇后问题
 */
public class Queen8 {

    //定义一个max 表示共有多少个皇后
    int max = 8;
    //定义数组array 保存皇后放置位置的结果  如： arr[8] = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        //测试
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共有" + count + "种解法");
    }

    //编写一个方法 放置第n个皇后
    //特别注意：check 是每一次递归时 进入到check中都有  for (int i = 0; i < max; i++),因此会有回溯
    private void check(int n) {
        if (n == max) { // 8个皇后已经放好
            print();
            count ++;
            return;
        }

        //依次放入皇后 并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前的这个皇后 放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) { //不冲突
                //接着放n + 1 个皇后 即开始递归
                check(n +1);
            }

            //如果冲突 就继续执行 array[n] = i; 即将第n个皇后 放置在本行的后移的一个位置

        }
    }

    //查看放我们防止第n个皇后时 就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n  表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 表示判断 第n个皇后是否和前面的n - 1 和皇后在同一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示 在同一斜线上
            // 原理 |y2 - y1| = |x2 - x1| 两点之间 x轴差值和y轴差值的绝对值相同
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法,可以将皇后摆放的位置输出
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
