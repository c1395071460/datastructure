package com.atguigu.hashtab;

import java.util.Scanner;

/**
 * hash表 谷歌上机题
 */
public class HashTabDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id：");
                    int id = scanner.nextInt();
                    System.out.println("输入名字：");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.deleteByEmpId(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab  管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //不要忘记分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id 得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有链表 遍历HashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id  查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findByEmpId(id);
        if (emp == null) {
            System.out.printf("在hash表中没有找到该雇员");
        }
        System.out.printf("在第%d条链表中没有找到该雇员id=%d\n", (empLinkedListNo + 1), id);
    }
    //根据输入的id 删除雇员
    public void deleteByEmpId(int id) {
        int empLinkedListNo = hashFun(id);
        empLinkedListArray[empLinkedListNo].deleteByEmpId(id);
    }

    //编写一个散列函数
    public int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;//next  默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp 因此我们这个链表的head 是直接指向第一个Emp
    private Emp head;//默认null

    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员 则使用一个辅助指针 帮助其定位到最后
        Emp temp = head;
        while (true) {
            if (head.next == null) {//说明到链表最后
                break;
            }
            temp = temp.next;//后移
        }
        //退出时直接将emp 加入链表
        temp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) { //证明链表为空
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表的信息为：");
        Emp curEmp = head;//辅助指针
        //遍历输出
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到  就返回Emp  如果没有找到  就返回null
    public Emp findByEmpId(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;//这时 curEmp 就指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//在该条链表没有找到雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据id删除雇员信息
    public void deleteByEmpId(int id) {
        if (head == null) {
            System.out.println("链表中没有要删除的数据");
        }
        //辅助指针
        Emp curEmp = head;
        if (head.id == id){
            head = head.next;
            return;
        }
        while (true) {
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
                System.out.println();
            }
            if (curEmp.next == null) {
                System.out.println("链表中没有要删除的数据");
                break;
            }
        }
    }

}
