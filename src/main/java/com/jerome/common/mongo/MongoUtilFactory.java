package com.jerome.common.mongo;

/**
 * MongoDB工具工厂类
 *
 * @author jerome
 */
public class MongoUtilFactory {

    /**
     * MongoDB数据源1
     *
     * @return
     * @author：jerome
     */
    public static MongoUtilI getMongoUtil1() {
        return MongoUtil1.getInstance();
    }

    /**
     * MongoDB数据源2
     *
     * @return
     * @author：jerome
     */
    public static MongoUtilI getMongoUtil2() {
        return MongoUtil2.getInstance();
    }
}
