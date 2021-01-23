package com.jerome.pattern;

/**
 * 适配器模式
 * 对于Java程序来说，如果一个类要实现一个接口，则必须要覆写此接口中的全部抽象方法。
 * 那么如果此时一个接口中定义了抽象方法过多，但是在子类中又用不到这么多抽象方法，则肯定很麻烦。
 * 所以此时就需要一个中间的过度，但是此过度类又不希望被直接使用，
 * 所以将此过度类定义成抽象类最合适，即一个接口首先被一个抽象类先实现（此抽象类通常称为适配器类），
 * 并在此抽象类中实现若干方法（方法体为空），则以后的子类直接继承此抽象类，就可以有选择的覆写所需要的方法。
 *
 * @author jerome
 * @date 2017/4/13 10:15
 */
public class AdapterTest {
    public static void main(String args[]) {
        Window win = new WindowImpl();
        win.open();
        win.close();
    }
}

interface Window {
    void open();

    void close();

    void max();

    void min();
}

abstract class WindowAdapter implements Window {
    @Override
    public void open() {
    }

    @Override
    public void close() {
    }

    @Override
    public void max() {
    }

    @Override
    public void min() {
    }

}

class WindowImpl extends WindowAdapter {
    @Override
    public void open() {
        System.out.println("窗口打开。");
    }

    @Override
    public void close() {
        System.out.println("窗口关闭。");
    }
}
