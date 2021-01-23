#阻塞队列（BlockingQueue）例子

BlockingQueue 接口是java collections框架的一部分，它主要用于实现生产者-消费者问题。

##BlockingQueue的特性
* 当队列是空的时，从队列中获取或删除元素的操作将会被阻塞。
* 当队列是满时，往队列里添加元素的操作会被阻塞。
* 阻塞队列不接受空值，当你尝试向队列中添加空值的时候，它会抛NullPointerException。
* 阻塞队列的实现都是线程安全的，所有的查询方法都是原子的并且使用了内部锁或者其他形式的并发控制。

参考：[Java BlockingQueue Example - JournalDev](http://www.journaldev.com/1034/java-blockingqueue-example) 
