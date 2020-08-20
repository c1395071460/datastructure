package com.atguigu.linkedlist;

/**
 * 约瑟夫问题
 */

public class Josephu {


    public static void main(String[] args) {
        //测试一把看看构建环形链表 和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(25); // 加入五个小孩节点
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,25);
    }

}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //    创建一个first节点 当前没有编号
    private Boy first = null;

    //    添加小孩节点 构建成一个环形的链表
    public void addBoy(int nums) {
//        nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针 帮助构建环形链表
        //    使用一个for循环来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
//            如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环状
                curBoy = first; // 让first指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
//        判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动  因此我们仍然使用一个辅助指针 完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

//    根据用户的输入 计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初由多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
//        先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入错误，请重新输入");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //需求创建一个辅助指针(变量)helper,事先应该指向幻想链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后小孩节点
                break;
            }

            helper = helper.getNext();
        }
//        小孩报数前  先让first 和 helper 移动 k - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

//        当小孩报数时，让first和helper指针同时的移动 m - 1 次。然后出圈
//        这里是一个循环操作  直到圈中只有一个节点
        while (true) {

            if (helper == first) { // 说明圈中只有一个节点
                break;
            }

            //        让first 和helper指针同时移动 countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
//        这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d 出圈\n", first.getNo());

//        这时first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.printf("最后出圈的小孩%d 出圈\n", first.getNo());

    }


}


//创建一个Boy类  表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}