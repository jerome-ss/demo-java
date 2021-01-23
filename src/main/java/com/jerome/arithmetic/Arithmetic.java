package com.jerome.arithmetic;

import java.security.NoSuchAlgorithmException;

/**
 * Java常用算法
 *
 * @author JeromeThinkPad
 * @see 可以参考：http://www.blogjava.net/todayx-org/archive/2012/01/08/368091.html
 */
public class Arithmetic {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        insertSort();
        // 快速排序
        int arrInt[] = {10, 5, 6, 56, 7, 3};
        quickSort(arrInt, 0, arrInt.length - 1);
        for (int i = 0; i < arrInt.length; i++) {
            System.out.print(arrInt[i] + ",");
        }
    }

    /**
     * 冒泡排序算法
     */
    public static void bubbleSort() {

        // 定义测试数据
        int arrInt[] = {10, 5, 6, 56, 7, 3};

        // 方式1. 拿第一个和下面的每个对比，外层每循环一次，最小的就浮上来
        int temp = 0;
        int length = arrInt.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                // 交换位置
                if (arrInt[i] > arrInt[j]) {
                    temp = arrInt[j];
                    arrInt[j] = arrInt[i];
                    arrInt[i] = temp;
                }
            }
        }

        /*
        // 方式2. 比较相邻的元素，外层每循环一次，最大的在最下面，最小的慢慢浮上来。
        // 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
        // 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。最后的元素就是最大的数。
        // 针对所有的元素重复以上的步骤，除了最后一个。
        // 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
        int temp = 0;
        int length = arrInt.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                // 交换位置
                if (arrInt[j] > arrInt[j + 1]) {
                    temp = arrInt[j];
                    arrInt[j] = arrInt[j + 1];
                    arrInt[j + 1] = temp;
                }
            }
        }*/

        // 输出排序后的数据
        for (int i : arrInt) {
            System.out.println(i + " ");
        }

    }

    /**
     * 插入排序算法
     * <p/>
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     */
    public static void insertSort() {

        // 定义测试数据
        int arrInt[] = {10, 5, 6, 56, 7, 3};

        // 排序，外层循环一次，元素总能找到适合自己插入的位置
        // 1. 使用当前元素与左边的元素两两相比，
        // 2. 如果当前元素值小，元素互换，再用左边两个元素比较
        // 3. 如果当前元素值大，结束比较
        int temp = 0;
        for (int x = 1; x < arrInt.length; x++) {
            for (int y = x; y > 0; y--) {
                // 如果前面的数字大于后面的，则把大的值赋值到后面
                if (arrInt[y - 1] > arrInt[y]) {
                    // 交换位置
                    temp = arrInt[y];
                    arrInt[y] = arrInt[y - 1];
                    arrInt[y - 1] = temp;
                } else {
                    // 如果当前的数不小于前面的数，说明不小于前面所有的数
                    // 因为前面已经是排序好了的，所以直接到下一轮循环
                    break;
                }
            }
        }

        // 输出排序后的数据
        for (int i : arrInt) {
            System.out.println(i + " ");
        }

    }

    /**
     * 快速排序 TODO 还没搞明白
     * <p/>
     * 快速排序是排序算法中效率最高的一种，它的理解也比较困难。
     * 快速排序是利用递归原理，把数组无限制的分为两个部分，直到所有数据都排序好为止。
     * <p/>
     * 快速排序是对冒泡排序的一种改进。
     * 它的基本思想是通过一趟排序将要排序的数据分割成独立的两部分，
     * 其中一部分的所有数据都比另外一部分的所有数据小，
     * 然后在按此方法对着两部分数据分别进行快速排序，
     * 整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     *
     * @param intArrs
     * @param left    区间左边开始排序的下标
     * @param right   区间右边开始排序的下标
     */
    public static void quickSort(int[] intArrs, int left, int right) {

        int i = left;
        int j = right;

        // 随便取一个数为中间数
        int middle = intArrs[left];

        int tempDate;
        do {
            // 找出左边比中间值大的数
            while (intArrs[i] < middle && i < right) {
                i++;
            }

            // 找出右边比中间值小的数
            while (intArrs[j] > middle && j > left) {
                j--;
            }

            // 将左边大的数和右边小的数进行替换
            if (i <= j) {
                tempDate = intArrs[i];
                intArrs[i] = intArrs[j];
                intArrs[j] = tempDate;
                i++;
                j--;
            }

        } while (i <= j); // 当两者交错时停止

        if (i < right) {
            quickSort(intArrs, i, right);
        }
        if (j > left) {
            quickSort(intArrs, left, j);
        }
    }

    /**
     * 快速排序
     *
     * @param strDate
     * @param left
     * @param right
     */
    /*public static void quickSort(String[] strDate, int left, int right) {
        String middle, tempDate;
        int i, j;
        i = left;
        j = right;
        middle = strDate[(i + j) / 2];
        do {
            while (strDate[i].compareTo(middle) < 0 && i < right)
                i++; // 找出左边比中间值大的数
            while (strDate[j].compareTo(middle) > 0 && j > left)
                j--; // 找出右边比中间值小的数
            if (i <= j) { // 将左边大的数和右边小的数进行替换
                tempDate = strDate[i];
                strDate[i] = strDate[j];
                strDate[j] = tempDate;
                i++;
                j--;
            }
        } while (i <= j); // 当两者交错时停止

        if (i < right) {
            quickSort(strDate, i, right);// 从
        }
        if (j > left) {
            quickSort(strDate, left, j);
        }
    }*/
}
