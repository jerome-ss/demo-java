package com.jerome.common.util;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 常用的集合方法整理
 *
 * @author jerome
 */
public class CollectionUtils {
    public static void main(String[] args) {
    }

    /**
     * Null-safe check if the specified collection is empty.
     * <p>
     * Null returns true.
     *
     * @param coll  the collection to check, may be null
     * @return true if empty or null
     * @since Commons Collections 3.2
     */
    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * Null-safe check if the specified collection is not empty.
     * <p>
     * Null returns false.
     *
     * @param coll  the collection to check, may be null
     * @return true if non-null and non-empty
     * @since Commons Collections 3.2
     */
    public static boolean isNotEmpty(Collection coll) {
        return !CollectionUtils.isEmpty(coll);
    }

    /**
     * 集合排序
     *
     * @param list
     */
    public static void sortList(List<ObjectName> list) {
        // Collections.sort(重写toString()进行排序区分)
        // 或者根据自己的业务字段进行排序o1.getAge().compareTo(o2.getAge());
        Collections.sort(list, new Comparator<ObjectName>() {
            public int compare(ObjectName o1, ObjectName o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
    }


    /**
     * 对List进行分页
     *
     * @param pageNo   当前页码
     * @param pageSize 页数
     * @param list     所有集合
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "unused"})
    private List<String> getListPage(int pageNo, int pageSize, List<String> list) {
        List<String> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            int allCount = list.size();
            int pageCount = (allCount + pageSize - 1) / pageSize;
            if (pageNo >= pageCount) {
                pageNo = pageCount;
            }
            int start = (pageNo - 1) * pageSize;
            int end = pageNo * pageSize;
            if (end >= allCount) {
                end = allCount;
            }
            for (int i = start; i < end; i++) {
                result.add(list.get(i));
            }
        }
        return (List<String>) ((CollectionUtils.isNotEmpty(list)) ? result : Collections.emptyList());
    }

    /**
     * 分批list
     *
     * @param sourceList 要分批的list
     * @param batchCount 每批list的个数
     * @return List<List<Object>>
     */
    public static List<List<?>> batchList(List<?> sourceList, int batchCount) {
        List<List<?>> returnList = new ArrayList<>();
        int startIndex = 0; // 从第0个下标开始
        while (startIndex < sourceList.size()) {
            int endIndex = 0;
            if (sourceList.size() - batchCount < startIndex) {
                endIndex = sourceList.size();
            } else {
                endIndex = startIndex + batchCount;
            }
            returnList.add(sourceList.subList(startIndex, endIndex));
            startIndex = startIndex + batchCount; // 下一批
        }
        return returnList;
    }
}
