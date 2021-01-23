package com.jerome.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合之流式操作
 *
 * @author jerome
 * @since 2017/5/30 10:48
 */
public class StreamDemo {

    /**
     * 串行流和并行流比较
     */
    @Test
    public void sequentialAndParallelTest() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            double d = Math.random() * 1000;
            list.add(d + "");
        }

        long startSeqTime = System.currentTimeMillis();
        list.stream().sequential().sorted().count();
        System.out.println("使用串行流排序时间：" + (System.currentTimeMillis() - startSeqTime));

        long startParTime = System.currentTimeMillis();
        list.stream().parallel().sorted().count();
        System.out.println("使用并行流排序时间：" + (System.currentTimeMillis() - startParTime));
    }

    /**
     * 对元素进行过滤
     */
    @Test
    public void filterTest() {

        List<String> names = Arrays.asList("Jerome", "Jelly", "Nemo");
        // @formatter:off
        names.stream()
             .filter((name) -> name.startsWith("J"))
             .forEach(System.out::println);
        // @formatter:on

        /*
        终止操作
        该操作必须是流的最后一个操作，一旦被调用，Stream 就到了一个终止状态，而且不能再使用了。常见的终止操作有：
        forEach()：对每个元素做处理；
        toArray()：把元素导出到数组；
        findFirst()：返回第一个匹配的元素；
        anyMatch()：是否有匹配的元素等。
        count()：统计个数
        collect():生成一个新的List
        ...
        */

        // 2. 创建一个长度大于4个字符的字符串List
        List<String> filtered = names
                .stream()
                .filter(x -> x.length() > 4)
                .collect(Collectors.toList());

        filtered.forEach(System.out::println);

    }

    /**
     * 对元素进行处理
     */
    @Test
    public void mapTest() {

        // 1. Map Demo
        // Without lambda expressions:
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        /*
        for (Integer cost : costBeforeTax) {
            double price = cost + 0.12*cost;
            System.out.println(price);
        }
        */

        // With Lambda expression:
        costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).forEach(System.out::println);

        // 2. Map Reduce Demo
        // Old way:
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax2) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // New way:
        double bill = costBeforeTax2
                .stream()
                .map((cost) -> cost + .12 * cost)
                .reduce((sum, cost) -> sum + cost)
                .get();

        System.out.println("Lambda Total : " + bill);

        // 3. 处理List每个元素并返回List
        List<String> names = Arrays.asList("Jerome", "Jelly", "Nemo");

        String name = names
                .stream()
                .map(x -> x.toLowerCase())
                .collect(Collectors.joining(", ")); // 使用,号分割

        System.out.println(name);

        // 常用函数
        // .distinct() 过滤相同值

    }

    /**
     * 不能在lambda代码块里控制forEach的loop流程
     */
    @Test
    public void forEachLoopTest() {
        String[] args = {"jelly", "jerome", "nemo"};
        Arrays.asList(args).forEach(s -> {
            if (s.length() > 4) {
                // 这个return语句并不能终止loop循环
                return;
            }
            System.out.println(s);
        });
    }

    /**
     * 计算List中元素的最大，最小，和以及平均值
     * 在Stream类中像IntStream, LongStream and DoubleStream有一个非常有用的方法summaryStattics()，
     * 返回IntSummaryStatistics, LongSummaryStatistics or DoubleSummaryStatistics其描述了这个流中元素的统计数据。
     */
    @Test
    public void summaryStatticsTest() {
        List<Integer> ints = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = ints
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

}
