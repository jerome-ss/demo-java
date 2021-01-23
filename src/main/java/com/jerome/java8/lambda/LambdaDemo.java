package com.jerome.java8.lambda;

import org.junit.Test;

import java.util.*;

/**
 * lambda例子
 *
 * @author jerome
 * @since 2017/5/30 09:24
 */
public class LambdaDemo {

    /**
     * 匿名类lambda使用
     */
    @Test
    public void anonymousClassTest() {
        System.out.println("使用lambda之前：");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread run.");
            }
        }).start();

        System.out.println("\n使用lambda之后：");
        // () -> System.out.println("");
        new Thread(() -> System.out.println("lambda thread run.")).start();
        // 带参数 (int even, int odd) -> even + odd
    }

    /**
     * 集合中lambda使用
     */
    @Test
    public void listTest() {

        List<String> names = Arrays.asList("Jerome", "Jelly", "Jack");

        System.out.println("使用lambda之前：");
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("\n使用lambda之后：");
        names.forEach(name -> System.out.println(name));

        System.out.println("\n用java8的方法引用由::(双冒号)操作符来完成：");
        names.forEach(System.out::println); // 调用System.out类的println方法
    }

    /**
     * map的lambda使用
     */
    @Test
    public void mapTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Jerome", 25);
        map.put("Jelly", 24);
        map.put("Jack", 32);

        System.out.println("使用lambda之前：");
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = entrySet.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }

        System.out.println("\n使用lambda之后：");
        map.forEach((key, value) -> System.out.println("key = " + key + ", value = " + value));

    }
}
