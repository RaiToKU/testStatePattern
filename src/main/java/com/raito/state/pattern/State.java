package com.raito.state.pattern;

/**
 * @author raito
 * @date 2023/6/30
 */
public abstract class State {

    protected void a2b(Context context) {
        System.out.println(context.getState().getClass().getSimpleName() + " not support this transition to B state");
    }

    protected void b2c(Context context) {

        System.out.println(context.getState().getClass().getSimpleName() + " not support this transition to C state");
    }

    protected void a2c(Context context) {
        System.out.println("当前流程：" + context.getState().getClass().getSimpleName());
        if (context.getState().getClass().getSimpleName().equals(CState.class.getSimpleName())) {
            System.out.println("当前流程是C");
        } else {
            System.out.println("current state is " + context.getState().getClass().getSimpleName() + " not support  transition");
        }
    }


}
