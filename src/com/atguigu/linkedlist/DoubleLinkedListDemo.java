package com.atguigu.linkedlist;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
//      双向链表的测试
//        先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林4", "豹子头");

//        创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.list();

//        修改
        HeroNode2 hero5 = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(hero5);

        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

//        删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }
}

//首先创建一个双向链表的类
class DoubleLinkedList {

    //    先创建一个头节点 头结点不要动 不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //    返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //  添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
//        因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode2 temp = head;
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
//        形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //    按照顺序将节点添加到双向链表中
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("新加入的节点重复");
        } else {
            if (temp.next != null) {
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;
            heroNode.pre = temp;

        }

    }

    //    修改一个节点的内容 可以看到双向链表的节点内容修改和单向链表的几乎一样
//    只是  节点类型改成 HeroNode2
    public void update(HeroNode2 newHeroNode) {
//        判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//        找到需要修改的节点 根据no编写
//        定义一个辅助变量
        HeroNode2 temp = head.next;
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

    //    从双向链表中删除一个节点
//    对于双向链表  我们可以直接找到要删除的这个节点
//    找到后 自我删除即可
    public void del(int no) {
//        判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空 无法删除");
            return;
        }

        HeroNode2 temp = head; //辅助变量(指针)
        boolean flag = false; //标志是否能找到待删除节点
        while (true) {
            if (temp == null) {//已经到链表的最后了
                System.out.println("链表为空");
                return;
            }

            if (temp.no == no) {
                flag = true;
                break;
            }

            temp = temp.next; //temp后移 遍历

        }
//        判断flag
        if (flag) {
//            可以删除
//            temp.next = temp.next.next;  单向链表的删除方式
            temp.pre.next = temp.next;
//            这里代码有问题  如果是最后一个节点  就不需要执行下面这句话 否则会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("链表中no为%d的节点不存在\n", no);
        }

    }

    //    遍历双向链表的方法
//    显示链表 【遍历】
    public void list() {
//        判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//        因为头节点 不能动  因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;  // 指向下一个节点  默认为null
    public HeroNode2 pre; // 指向前一个节点   默认为null

    //构造器
    public HeroNode2(int hNo, String hName, String hNickName) {
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