package com.atguigu.stack;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 */
public class PolandNotation {

    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1 + ((2+3)*4) - 5 转成 1 2 3 + 4 * + 5 -
        //2.因为直接对一个字符串进行操作 不方便  因此 现将 "1 + ((2+3)*4) - 5" =>中缀的表达式对应的List
        //即 "1 + ((2+3)*4) - 5" =》 arrayList[1 ,+ ,(,(,2,+,3,),*,4,) ,- ,5]
        //3. 将得到的中缀表式对应的List  =》 后缀表达式对应的List
        //即 arrayList[1 ,+ ,(,(,2,+,3,),*,4,) ,- ,5] =》 ArrayList[1 2 3 + 4 * + 5 -]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List"+infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + parseSuffixExpressionList);

/*

        //先定义逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //说明为了方便 逆波兰表达式 的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1.先将 "3 4 + 5 * 6 -" 放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList 配合栈 完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int calculate = calculate(rpnList);
        System.out.println("计算的结果是:" + calculate);*/
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();  //符号栈
        //因为s2这个栈 在真个转换的过程中 没有pop操作 而且后面我们还需要逆序输出
        //因此比较麻烦 这里我们不用Stack<String> 直接使用List<String> 来替代
        List<String> s2 = new ArrayList<>();  //存储中间结果的栈s2

        //遍历ls
        for (String item : ls) {
            //如果是一个数 加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号  则依次弹出s1 栈顶的运算符 并压入s2 直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // 将 ( 弹出s1栈  消除小括号
            } else {
                //当item的优先级 <= 栈顶运算符的优先级(s1) 将s1栈顶的运算符弹出并加入到s2中 再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题: 我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item压入栈中
                s1.push(item);
            }

        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; // 注意因为是存放到List  因此按顺序输出就是对应的后缀表达式对应的List

    }

    //将中缀表达式转成对应的List
    // s = "1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List 存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        //一个指针 用于遍历中缀表达式字符串
        int i = 0;
        String str; //对多位数的拼接
        char c; //每遍历到一个字符 就放到c中
        do {
            //如果c是一个非数字 我需要计入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else { // 如果是数字 需要考虑多位数的问题
                str = "";//现将str 置程空串
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());

        return ls;

    }

    //将一个逆波兰表达式 一次将数据和运算符 放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = Arrays.asList(split);
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建栈 只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历 ls
        for (String item : ls) {
            //从这里使用正则表达式取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数并运算 在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类  Operation  可以返回一个运算符  对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法 返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}