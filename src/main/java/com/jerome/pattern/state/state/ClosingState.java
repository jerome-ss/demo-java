package com.jerome.pattern.state.state;


import com.jerome.pattern.state.context.Context;

/**
 * 电梯门关闭以后，电梯可以做哪些事情
 *
 * @author jerome
 * @since 2017/5/30 08:56
 */
public class ClosingState extends LiftState {

    @Override
    public void close() {
        System.out.println("电梯门关闭...");
    }

    @Override
    public void open() {
        // 置为门敞状态
        super.context.setLiftState(Context.openningState);
        super.context.getLiftState().open();
    }

    @Override
    public void run() {
        // 设置为运行状态；
        super.context.setLiftState(Context.runningState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        // 设置为停止状态；
        super.context.setLiftState(Context.stoppingState);
        super.context.getLiftState().stop();
    }
}