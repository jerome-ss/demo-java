package com.jerome.pattern;

/**
 * 模版方法模式
 * 定义一个算法的执行骨架，将具体的算法实现延迟到子类完成。
 * 比如：要求用户做一个打印程序，规定必须打印表头，正文，表尾3部分。
 * 这些就可以做成模版的形式，如下例子
 *
 * @author jerome
 * @date 2017/4/12 19:48
 */
public class TemplateTest {
    public static void main(String[] args) {
        Report r = new ReportImpl();
        r.print();

        // 接着，我要使用另外的表头，正文等信息
        r = new ReportImpl2();
        r.print();

        // 这个时候，如果你继续有新的要求，那么，你就继续添加类来实现Report这个打印功能即可

    }
}

abstract class Report {
    public void print() {
        // 可以有一些公共的逻辑处理
        System.out.println("do something.");
        printTitle();
        printBody();
        printTail();
    }

    /**
     * 打印标题
     */
    public abstract void printTitle();

    /**
     * 打印正文
     */
    public abstract void printBody();

    /**
     * 打印表尾
     */
    public abstract void printTail();
}

class ReportImpl extends Report {

    @Override
    public void printTitle() {
        System.out.println("打印表头");
    }

    @Override
    public void printBody() {
        System.out.println("打印正文");
    }

    @Override
    public void printTail() {
        System.out.println("打印表尾");
    }
}

class ReportImpl2 extends Report {

    @Override
    public void printTitle() {
        System.out.println("使用另外的方式打印表头");
    }

    @Override
    public void printBody() {
        System.out.println("使用另外的方式打印正文");
    }

    @Override
    public void printTail() {
        System.out.println("使用另外的方式打印表尾");
    }
}

