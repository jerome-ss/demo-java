package com.jerome.pattern;

/**
 * 静态代理
 * 所谓的代理设计就是指由一个代理主题来操作真实主题，
 * 真实主题执行具体的业务操作，而代理主题负责其他相关业务的处理，
 *
 * @author jerome
 * @date 2017/4/13 10:42
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        // Operator oper = new OperatorImpl(); // 多态
        // oper.add();
        // oper.delete();

        // 我希望在每个操作执行前后记录信息，可以使用静态代理方式
        OperatorProxy operatorProxy = new OperatorProxy(new OperatorImpl());
        operatorProxy.add();
        operatorProxy.delete();
    }
}

interface Operator {
    void add();

    void delete();
}

class OperatorImpl implements Operator {

    @Override
    public void add() {
        System.out.println("增加功能");
    }

    @Override
    public void delete() {
        System.out.println("删除功能");
    }

}

class OperatorProxy implements Operator {

    private OperatorImpl operatorImpl;

    public OperatorProxy(OperatorImpl operatorImpl) {
        this.operatorImpl = operatorImpl;
    }

    @Override
    public void add() {
        System.out.println("纪录日志开始...");
        this.operatorImpl.add();
        System.out.println("纪录日志结束...");
    }

    @Override
    public void delete() {
        System.out.println("纪录日志开始...");
        this.operatorImpl.delete();
        System.out.println("纪录日志结束...");
    }
}
