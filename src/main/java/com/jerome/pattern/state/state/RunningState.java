package com.jerome.pattern.state.state;


import com.jerome.pattern.state.context.Context;

/**
 * 电梯在运行状态下能做哪些动作
 *
 * @author jerome
 * @since 2017/5/30 08:58
 */
public class RunningState extends LiftState {
    
    @Override
    public void close() {
        // do nothing
    }

    @Override
    public void open() {
        // do nothing
    }

    @Override
    public void run() {
        System.out.println("电梯上下跑...");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState);
        super.context.getLiftState().stop();
    }
}