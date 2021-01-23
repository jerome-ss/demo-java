package com.jerome.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * <p/>
 * 静态代理的缺点：
 * 每个代理类只能为一个接口服务，如果操作类很多，我们就需要提供很的代理类。
 * 而且每个方法都是我们手动的加入纪录日志的功能，这样太麻烦了。
 * <p/>
 * 我们用动态代理实现。
 * 以前是针对每种操作提供一个代理类。现在是针对每个功能提供一个代理类。
 * 比如说：我现在要纪录日志信息。我就是要实现日志纪录功能。
 * 就专门为实现日志纪录提供一个代理。
 *
 * @author jerome
 * @date 2017/4/13 10:57
 */
public class DynamicProxyDemo {

    public static void main(String[] args) {
        // Operator oper = new OperatorImpl(); // 多态
        // oper.add();
        // oper.delete();

        // 我希望在每个操作执行前后记录信息，可以使用静态代理方式
        // OperatorProxy operatorProxy = new OperatorProxy(new OperatorImpl());
        // operatorProxy.add();
        // operatorProxy.delete();

        LogOperatorProxy proxy = new LogOperatorProxy();
        Operator operator = (Operator) proxy.bind(new OperatorImpl());

        operator.add();
        operator.delete();
    }
}

/**
 * 日志处理代理类
 */
class LogOperatorProxy implements InvocationHandler {
    private Object obj;

    public Object bind(Object obj) {
        this.obj = obj;

        //Proxy类是专门完成代理的操作类，可以通过此类为一个或多个接口动态地生成实现类。
        //通过newProxyInstance()方法可以动态地生成实现类
        // 第一个参数：定义代理的类加载器
        // 第二个参数：代理类的全部接口
        // 第三个参数：指派方法调用处理程序，InvocationHandler的子类实例
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), this);
    }

    /**
     * @param proxy  被代理的对象
     * @param method 要调用的方法
     * @param args   方法调用时所需要的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object obj = null;
        System.out.println("开始记录信息...");
        obj = method.invoke(this.obj, args);
        System.out.println("结束记录信息...");
        return obj;
    }
}
/*
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
*/
