package com.atguigu.linkedlist;


import javax.xml.soap.Node;
import java.util.Stack;

@SuppressWarnings("ALL")
public class SingleLinkedListDemo {

    public static void main(String[] args) {
//        进行测试
//        先创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林4", "豹子头");

        HeroNode hero5 = new HeroNode(5, "林5", "豹子头");
        HeroNode hero6 = new HeroNode(6, "林6", "豹子头");



//        创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
//        加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList2.addByOrder(hero5);
        singleLinkedList2.addByOrder(hero6);

        System.out.println("打印链表2");
        singleLinkedList2.list();


        heBing(singleLinkedList2.getHead(),singleLinkedList);

        System.out.println("合并后也有序的链表");
        singleLinkedList.list();





//        显示
        System.out.println("显示打印的链表");
        singleLinkedList.list();


        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();

//        删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后的链表情况");
        singleLinkedList.list();

//        测试一下 求单链表总有效节点的个数
        System.out.println("链表的个数为");
        System.out.println(getLength(singleLinkedList.getHead()));

//        测试一下看看是否得到了倒数第k个节点
        System.out.println("倒数第一个节点的HeroNode为:"+findLastIndexNode(singleLinkedList.getHead(),3));

//        测试将链表反转
        System.out.println("将链表反转~~");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("测试逆序打印单链表,没有改变链表的结构");
        reversePrint(singleLinkedList.getHead());



    }

    //    方法：获取到单链表的节点的个数(如果是带头节点的链表，需求不统计头节点)
    public static int getLength(HeroNode head) {

        if (head.next == null) {
            return 0;
        }

        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //    查找单链表中的倒数第k个节点 【新浪面试题】
//    思路
//    1.编写一个方法，接收head节点，同时接收一个index
//    2.index表示的是倒数第index个节点
//    3.先把链表从头到尾遍历，得到链表的总的长度  getLength
//    4.得到size后 我们从链表的第一个开始遍历(size - index)个，就可以得到了
//    5.如果找到 返回该节点 否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }

//        第一次遍历 得到链表的长度
        int size = getLength(head);
//        第二次遍历 size - index 位置  就是我们倒数的第k个节点
//        先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
//        定义一个辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }

        return cur;
    }


//      将单链表反转
    public static void reversetList(HeroNode head) {
//        如果当前链表为空 或者只有一个节点，无序反转 直接返回
        if(head.next == null || head.next.next == null) {
            return;
        }

//        定义一个辅助的指针(变量) 帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //指向当前节点[cur]的下一个节点
        HeroNode next = null;
//        新建一个链表
        HeroNode reverseHead = new HeroNode(0, "", "");
//        遍历原来的链表 每遍历一个节点  就将其取出  并放在心的链表reverseHead 的最前端
        while (cur != null) {
            next = cur.next;//先暂存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next; //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将cur连接到新的链表上
            cur = next;//让cur后移
        }
//      将head.next指向reverseHead.next  实现链表的反转
        head.next = reverseHead.next;


    }

//    使用栈的方式 实现 逆序打印单链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表 无法打印
        }
//        创建一个栈  将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
//        将链表的所有节点压入栈中
        while ( cur != null) {
            stack.push(cur);
            cur = cur.next; // 后移 这样就可以压入下一个
        }
//        将栈中的节点打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }

//    合并两个有序的单链表  合并之后的链表依然有序
    public static void heBing(HeroNode head,SingleLinkedList singleLinkedList) {
        if (head.next == null) {
            return;
        }

//        要添加的链表的临时节点
        HeroNode cur = head.next;
        HeroNode next = null;
        while (cur != null) {

            next = cur.next;
            HeroNode temp = singleLinkedList.getHead().next;
            boolean flag = false;//标志添加的节点是否存在，默认false
            while (true) {
                if (temp.next == null) { //说明已经到了链表的最后
                    break;
                }
                if (temp.next.no > cur.no) { // 位置找到，就在temp后面插入
                    break;
                } else if (temp.next.no == cur.no) { // 说明希望添加的heroNode的编号已经存在
                    flag = true; // 说明编号已经存在
                    break;
                }

                temp = temp.next; // 后移 遍历当前链表
            }
            //判断flag的值
            if (flag) {
                System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", cur.no);
            } else {
                //插入到链表中，temp的后面
                cur.next = temp.next;
                temp.next = cur;
            }



            cur = next;
        }

    }


}

//定义SingleLinekdList 管理我们的英雄
class SingleLinkedList {
    //    先初始化一个头节点，头结点不要动
    private HeroNode head = new HeroNode(0, "", "");

    //    返回头节点
    public HeroNode getHead() {
        return head;
    }

    //    添加头节点到单向链表
//    思路： 当不考虑编号的顺序时
//    1.找到当前链表的最后节点
//    2.将最后这个节点的next  指向新的节点
    public void add(HeroNode heroNode) {
//        因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
//        遍历链表，找到最后
        while (true) {
//            找到列表的最后
            if (temp.next == null) {
                break;
            }
//            如果没有找到最后，将temp后移
            temp = temp.next;
        }
//        当退出while循环时 temp就指向了链表的最后
//        将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点 否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标志添加的节点是否存在，默认false
        while (true) {
            if (temp.next == null) { //说明已经到了链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到，就在temp后面插入
                break;
            } else if (temp.next.no == heroNode.no) { // 说明希望添加的heroNode的编号已经存在
                flag = true; // 说明编号已经存在
                break;
            }

            temp = temp.next; // 后移 遍历当前链表
        }
        //判断flag的值
        if (flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //    修改节点的信息，根据no编号俩修改，即no编号不能改
//    说明
//    根据newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
//        判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//        找到需要修改的节点 根据no编写
//        定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点\n", newHeroNode.no);
        }
    }

    //    删除节点
//    思路
//    1.  head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
//    2.  说明我们在比较时，是temp.next.no 和需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//已经到链表的最后了
                System.out.println("链表为空");
                return;
            }

            if (temp.next.no == no) {
                flag = true;
                break;
            }

            temp = temp.next; //temp后移 遍历

        }
//        判断flag
        if (flag) {
//            可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("链表中no为%d的节点不存在\n", no);
        }

    }


    //    显示链表 【遍历】
    public void list() {
//        判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//        因为头节点 不能动  因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
//            判断是否到链表的最后
            if (temp == null) {
                break;
            }
//            输出节点的信息
            System.out.println(temp);
//            将next后移
            temp = temp.next;

        }
    }

}


//定义HeroNode 每个HeroNode  对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  // 指向下一个节点

    //构造器
    public HeroNode(int hNo, String hName, String hNickName) {
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}