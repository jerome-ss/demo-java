package com.jerome.pattern.observer;

import java.util.HashSet;

/**
 * 观察者模式
 *
 * @author jerome
 * @date 2017/3/7 22:44
 *
 * 观察者模式（Observer）又称监听者模式，将观察者和被观察的对象分开。
 * 观察者模式在模块之间划定了清晰的界限，提高了应用程序的可维护性和重用性。
 * 应该通过一个注册和回调的形式来实现观察者模式，一旦发生变化则通过多态回调观察者的接口方法，从而达到低耦合的目的。
 * 以下是一个观察者关注图书价格变化的例子
 */
public class ObserverPatternTest {
    public static void main(String[] args) {

        Product product = new Product(23.5D, "《必然》");

        // 声明观察者
        Observer observer1 = new WebObserver();
        Observer observer2 = new MailObserver();

        // 注册观察者
        product.addObserver(observer1);
        product.addObserver(observer2);

        // 修改价格
        System.out.println("第一次修改价格");
        product.setPrice(10D);

        // 取消一个观察者
        observer1.unreg(product);

        // 再修改价格
        System.out.println("\n第二次修改价格");
        product.setPrice(22D);

    }
}

/**
 * 产品类
 */
class Product {
    private Double price;
    private String name;
    // 保存所有观察者
    private HashSet<Observer> observers;

    public Product(Double price, String name) {
        this.price = price;
        this.name = name;
        this.observers = new HashSet<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 通知监听者执行update()方法
     */
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        // 价格有修改通知观察者
       // notifyObserver();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        // 价格有修改通知观察者
        notifyObserver();
    }

    public HashSet<Observer> getObservers() {
        return observers;
    }

    public void setObservers(HashSet<Observer> observers) {
        this.observers = observers;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 观察者接口
 */
interface Observer {
    /**
     * 修改价格
     *
     * @param product
     */
    void update(Product product);

    /**
     * 撤销注册
     *
     * @param product
     */
    void unreg(Product product);
}

/**
 * Web观察者
 */
class WebObserver implements Observer {

    @Override
    public void update(Product product) {
        System.out.println("Web收到价格更新, product = " + product);
    }

    @Override
    public void unreg(Product product) {
        product.getObservers().remove(this);
    }
}

/**
 * email观察者
 */
class MailObserver implements Observer {

    @Override
    public void update(Product product) {
        System.out.println("Email收到价格更新, product = " + product);
    }

    @Override
    public void unreg(Product product) {
        product.getObservers().remove(this);
    }
}