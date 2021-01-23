package com.jerome.pattern.state.state;


import com.jerome.pattern.state.context.Context;

/**
 * 定义一个电梯的接口
 *
 * @author jerome
 * @since 2017/5/30 09:04
 */
public abstract class LiftState {

    // 定义一个环境角色，也就是封装状态的变换引起的功能变化
    protected Context context;

    public void setContext(Context _context) {
        this.context = _context;
    }

    /**
     * 电梯门开启
     */
    public abstract void open();

    /**
     * 电梯门关闭
     */
    public abstract void close();

    /**
     * 电梯上/下
     */
    public abstract void run();

    /**
     * 电梯停止
     */
    public abstract void stop();
}