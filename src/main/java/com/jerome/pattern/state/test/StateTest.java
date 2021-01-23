package com.jerome.pattern.state.test;


import com.jerome.pattern.state.context.Context;
import com.jerome.pattern.state.state.ClosingState;

/**
 * 模拟电梯的动作
 *
 * @author jerome
 * @since 2017/5/30 08:54
 */
public class StateTest {

    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
