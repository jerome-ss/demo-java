package com.jerome.pattern;

/**
 * 装饰模式
 * 就是指对xxx进行包装，其实就是给对象添加新的功能
 *
 * @author jerome
 * @date 2017/4/13 17:25
 */
public class DecorateDemo {

    public static void main(String[] args) {
        // 最基本的手机类
        Phone p = new PhoneImpl();
        p.call();
        System.out.println();

        // 我要装饰手机可以播放彩铃
        PhoneDecorate pd = new ColorPhoneDecorate(p);
        pd.call();
        System.out.println();

        // 我要装饰手机可以听音乐，可以打电话听音乐
        pd = new MusicPhoneDecorate(p);
        pd.call();
        System.out.println();

        // 我想要手机既可以播放彩铃又可以听音乐
        // 可以相互嵌套用，使用继承耦合很强，使用装饰模式好多了
        pd = new MusicPhoneDecorate(new ColorPhoneDecorate(p));
        pd.call();

    }
}

interface Phone {
    void call();
}

class PhoneImpl implements Phone {
    @Override
    public void call() {
        System.out.println("手机可以打电话！");
    }
}

abstract class PhoneDecorate implements Phone {

    private Phone phone;

    public PhoneDecorate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void call() { // 对手机进行装饰
        this.phone.call(); // 具体传递谁就调用谁
    }

}

class ColorPhoneDecorate extends PhoneDecorate {

    public ColorPhoneDecorate(Phone phone) {
        super(phone);
    }

    @Override
    public void call() {
        // 接听前播放彩铃
        System.out.println("手机可以播放彩铃！");
        super.call();
    }
}

class MusicPhoneDecorate extends PhoneDecorate {

    public MusicPhoneDecorate(Phone phone) {
        super(phone);
    }

    @Override
    public void call() {
        super.call();
        // 打完电话后，可以听音乐
        System.out.println("手机可以听音乐！");
    }

}
